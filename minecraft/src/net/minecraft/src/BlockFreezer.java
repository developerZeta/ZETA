package net.minecraft.src;

import java.util.Random;

public class BlockFreezer extends Block{

    public BlockFreezer(int var1){
        super(var1,Material.iron);
    }

    // Check if a steel fan is adjacent to the freezer
    private boolean hasAdjacentFan(World var1, int var2, int var3, int var4) {
        for(int x = var2 - 1; x <= var2 + 1; ++x) {
            for(int y = var3 - 1; y <= var3 + 1; ++y) {
                for(int z = var4 - 1; z <= var4 + 1; ++z) {
                    if(x == var2 && y == var3 && z == var4) {
                        continue;
                    }
                    if(Math.abs(x - var2) + Math.abs(y - var3) + Math.abs(z - var4) > 1) {
                        continue;
                    }
                    if(var1.getBlockId(x, y, z) == Block.steelFan.blockID) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getBlockTextureFromSide(int side) {
        if(side == 3){
        return ModBlocksRenderer.getTextureIndex(11, 9);}
        return ModBlocksRenderer.getTextureIndex(10, 9);
    }

    private void spawnFreezeParticles(World world, int x, int y, int z) {
        for(int i = 0; i < 20; ++i) {
            double motionX = world.rand.nextGaussian() * 0.02D;
            double motionY = world.rand.nextGaussian() * 0.02D;
            double motionZ = world.rand.nextGaussian() * 0.02D;
            world.spawnParticle("explode",
                (double)x + 0.5D + (world.rand.nextDouble() - 0.5D) * 0.8D,
                (double)y + 0.5D + (world.rand.nextDouble() - 0.5D) * 0.8D,
                (double)z + 0.5D + (world.rand.nextDouble() - 0.5D) * 0.8D,
                motionX, motionY, motionZ);
        }
    }

    public boolean blockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5) {
        if (var1.multiplayerWorld) {
            return true;
        } else {
            // Check for adjacent steel fan
            if(!hasAdjacentFan(var1, var2, var3, var4)) {
                // No fan nearby - show message or sound
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 1.0F, 1.0F);
                return true;
            }

            ItemStack var6 = var5.getCurrentEquippedItem();
            if(var6 != null && var6.itemID == Block.slowSand.blockID) {
                if(Block.dirt != null) {
                    ItemStack var7 = new ItemStack(Block.dirt, 1);

                    if(!var5.inventory.addItemStackToInventory(var7)) {

                        var5.dropPlayerItem(var7);
                    } else {
                        System.out.println("empty");
                    }
                } else {
                    System.out.println("Millstone: NULL!");
                }
                var6.stackSize--;
                if(var6.stackSize <= 0) {
                    var5.inventory.mainInventory[var5.inventory.currentItem] = null;
                }
                this.spawnFreezeParticles(var1, var2, var3, var4);
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.fizz", 1.0F, var1.rand.nextFloat() * 0.4F + 0.8F);
                return true;
            }

            if(var6 != null && var6.itemID == Item.bucketWater.shiftedIndex) {
                // Give ice
                if(Block.ice != null) {
                    ItemStack iceStack = new ItemStack(Block.ice, 1);
                    if(!var5.inventory.addItemStackToInventory(iceStack)) {
                        var5.dropPlayerItem(iceStack);
                    }
                }
                // Give empty bucket
                if(Item.bucketEmpty != null) {
                    ItemStack bucketStack = new ItemStack(Item.bucketEmpty, 1);
                    if(!var5.inventory.addItemStackToInventory(bucketStack)) {
                        var5.dropPlayerItem(bucketStack);
                    }
                }
                // Consume water bucket
                var6.stackSize--;
                if(var6.stackSize <= 0) {
                    var5.inventory.mainInventory[var5.inventory.currentItem] = null;
                }
                this.spawnFreezeParticles(var1, var2, var3, var4);
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.fizz", 1.0F, var1.rand.nextFloat() * 0.4F + 0.8F);
                return true;
            }

            if(var6 != null && var6.itemID == Item.bucketLava.shiftedIndex) {
                if(Block.obsidian != null) {
                    ItemStack iceStack = new ItemStack(Block.obsidian, 1);
                    if(!var5.inventory.addItemStackToInventory(iceStack)) {
                        var5.dropPlayerItem(iceStack);
                    }
                }
                if(Item.bucketEmpty != null) {
                    ItemStack bucketStack = new ItemStack(Item.bucketEmpty, 1);
                    if(!var5.inventory.addItemStackToInventory(bucketStack)) {
                        var5.dropPlayerItem(bucketStack);
                    }
                }
                var6.stackSize--;
                if(var6.stackSize <= 0) {
                    var5.inventory.mainInventory[var5.inventory.currentItem] = null;
                }
                this.spawnFreezeParticles(var1, var2, var3, var4);
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.fizz", 1.0F, var1.rand.nextFloat() * 0.4F + 0.8F);
                return true;
            }

        }
            return  true;
    }
    public int idDropped(int var1, Random var2) {
        return Block.freezer.blockID;
    }

}
