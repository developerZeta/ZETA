package net.minecraft.src;

import java.util.Random;

public class BlockKrollReactor extends BlockContainer{
    private static final int FRONT_IDLE_TEXTURE = ModBlocksRenderer.getTextureIndex(8, 4);
    private static final int FRONT_ACTIVE_TEXTURE = ModBlocksRenderer.getTextureIndex(8, 3);
    private static final int BOTTOM_SIDE_TEXTURE = ModBlocksRenderer.getTextureIndex(10, 3);
    private static final int TOP_TEXTURE = ModBlocksRenderer.getTextureIndex(10, 4);
    private static final int BACK_TEXTURE = ModBlocksRenderer.getTextureIndex(4, 13);

    private final boolean isActive;
    private static boolean keepKrollReactorInventory = false;
    private Random random = new Random();

    public BlockKrollReactor(int id,boolean active){
        super(id,Material.iron);
        this.isActive=active;
    }

    public boolean isOpaqueCube() {
        return true;
    }
    public boolean renderAsNormalBlock() {
        return true;
    }
    public int getRenderType() {
        return 0;
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }
    private void setDefaultDirection(World world, int x, int y, int z) {
        if(!world.multiplayerWorld) {
            int blockBehind = world.getBlockId(x, y, z - 1);
            int blockFront = world.getBlockId(x, y, z + 1);
            int blockLeft = world.getBlockId(x - 1, y, z);
            int blockRight = world.getBlockId(x + 1, y, z);

            byte direction = 3; // Default: facing south

            if(Block.opaqueCubeLookup[blockBehind] && !Block.opaqueCubeLookup[blockFront]) {
                direction = 3; // Face south
            }
            if(Block.opaqueCubeLookup[blockFront] && !Block.opaqueCubeLookup[blockBehind]) {
                direction = 2; // Face north
            }
            if(Block.opaqueCubeLookup[blockLeft] && !Block.opaqueCubeLookup[blockRight]) {
                direction = 5; // Face east
            }
            if(Block.opaqueCubeLookup[blockRight] && !Block.opaqueCubeLookup[blockLeft]) {
                direction = 4; // Face west
            }

            world.setBlockMetadataWithNotify(x, y, z, direction);
        }
    }

    public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
        if(side == 1) {
            return TOP_TEXTURE;
        }
        if(side == 0 ) {
            return BOTTOM_SIDE_TEXTURE;
        }


        int facing = world.getBlockMetadata(x, y, z);
        if(side == facing) {
            return this.isActive ? FRONT_ACTIVE_TEXTURE : FRONT_IDLE_TEXTURE;
        }

        return BOTTOM_SIDE_TEXTURE;
    }

    public int getBlockTextureFromSide(int side) {
        if(side == 1) {
            return TOP_TEXTURE;
        }

        return side == 3 ? (this.isActive ? FRONT_ACTIVE_TEXTURE : FRONT_IDLE_TEXTURE) : BOTTOM_SIDE_TEXTURE;
    }

    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if(world.multiplayerWorld) {
            return true; // Client-side, nothing to do
        } else {
            try {
                TileEntityKrollReactor forge = (TileEntityKrollReactor) world.getBlockTileEntity(x, y, z);
                if(forge != null) {
                    player.displayGUIKrollReactor(forge);
                }
            } catch(Exception e) {
                System.out.println("Kroll Reactor GUI error: " + e.getMessage());
                // Remove corrupted tile entity to prevent future crashes
                world.setBlockTileEntity(x, y, z, null);
            }
            // Play lava sound when player clicks the Kroll Reactor
            world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "liquid.lava", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
            return true;
        }
    }

    public static void updateKrollReactorBlockState(boolean isActive, World world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        keepKrollReactorInventory = true;

        if(isActive) {
            // Switch to active state (block ID 180)
            world.setBlockWithNotify(x, y, z, Block.krollReactorActive.blockID);
        } else {
            // Switch to inactive state (block ID 179)
            world.setBlockWithNotify(x, y, z, Block.krollReactor.blockID);
        }

        keepKrollReactorInventory = false;

        // Restore metadata and tile entity
        world.setBlockMetadataWithNotify(x, y, z, metadata);
        tileEntity.func_31004_j();
        world.setBlockTileEntity(x, y, z, tileEntity);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving placer) {
        int direction = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if(direction == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2);
        }

        if(direction == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5);
        }

        if(direction == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3);
        }

        if(direction == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4);
        }
    }
    /// !!!
    protected TileEntity getBlockEntity() {
        return new TileEntityKrollReactor();
    }

    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if(this.isActive) {
            try {
                int metadata = world.getBlockMetadata(x, y, z);

                float centerX = (float)x + 0.5F;
                float centerY = (float)y + random.nextFloat() * 6.0F / 16.0F;
                float centerZ = (float)z + 0.5F;

                float offset = 0.52F;
                float randomOffset = random.nextFloat() * 0.6F - 0.3F;

                // Spawn particles on the facing side
                if(metadata == 4) {
                    // Facing west
                    world.spawnParticle("smoke", centerX - offset, centerY, centerZ + randomOffset, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", centerX - offset, centerY, centerZ + randomOffset, 0.0D, 0.0D, 0.0D);
                } else if(metadata == 5) {
                    // Facing east
                    world.spawnParticle("smoke", centerX + offset, centerY, centerZ + randomOffset, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", centerX + offset, centerY, centerZ + randomOffset, 0.0D, 0.0D, 0.0D);
                } else if(metadata == 2) {
                    // Facing north
                    world.spawnParticle("smoke", centerX + randomOffset, centerY, centerZ - offset, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", centerX + randomOffset, centerY, centerZ - offset, 0.0D, 0.0D, 0.0D);
                } else if(metadata == 3) {
                    // Facing south
                    world.spawnParticle("smoke", centerX + randomOffset, centerY, centerZ + offset, 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", centerX + randomOffset, centerY, centerZ + offset, 0.0D, 0.0D, 0.0D);
                }
            } catch(Exception e) {
                // Silently ignore rendering errors
            }
        }
    }

    public int idDropped(int metadata, Random random) {
        return Block.krollReactor.blockID;
    }

    public void onBlockRemoval(World world, int x, int y, int z) {
        if(!keepKrollReactorInventory) {
            try {
                TileEntityKrollReactor forge = (TileEntityKrollReactor) world.getBlockTileEntity(x, y, z);

                if(forge != null) {
                    for(int slot = 0; slot < forge.getSizeInventory(); ++slot) {
                        ItemStack stack = forge.getStackInSlot(slot);

                        // Only drop valid items (prevents crashes from corrupted data)
                        if(stack != null && stack.getItem() != null) {
                            float offsetX = this.random.nextFloat() * 0.8F + 0.1F;
                            float offsetY = this.random.nextFloat() * 0.8F + 0.1F;
                            float offsetZ = this.random.nextFloat() * 0.8F + 0.1F;

                            // Split large stacks into smaller drops
                            while(stack.stackSize > 0) {
                                int dropSize = this.random.nextInt(21) + 10;

                                if(dropSize > stack.stackSize) {
                                    dropSize = stack.stackSize;
                                }

                                stack.stackSize -= dropSize;

                                EntityItem entityItem = new EntityItem(
                                        world,
                                        (double)((float)x + offsetX),
                                        (double)((float)y + offsetY),
                                        (double)((float)z + offsetZ),
                                        new ItemStack(stack.itemID, dropSize, stack.getItemDamage())
                                );

                                float velocity = 0.05F;
                                entityItem.motionX = (double)((float)this.random.nextGaussian() * velocity);
                                entityItem.motionY = (double)((float)this.random.nextGaussian() * velocity + 0.2F);
                                entityItem.motionZ = (double)((float)this.random.nextGaussian() * velocity);

                                world.entityJoinedWorld(entityItem);
                            }
                        }
                    }
                }
            } catch(Exception e) {
                System.out.println("Steel Forge removal error: " + e.getMessage());
                // Remove corrupted tile entity to prevent future crashes
                world.setBlockTileEntity(x, y, z, null);
            }
        }

        super.onBlockRemoval(world, x, y, z);
    }
}
