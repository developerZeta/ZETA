package net.minecraft.src;

public class BlockRadiator extends Block{
    public BlockRadiator(int var1, int var2){
        super(var1,var2,Material.iron);

    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(4, 13);
    }
    public int getRenderType() {
        return 0;
    }

    public boolean renderAsNormalBlock() {
        return true;
    }

    public int getLightValue(IBlockAccess var1, int var2, int var3, int var4) {
        return this.getRedstonePowerLevel(var1, var2, var3, var4);
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        super.onBlockAdded(var1, var2, var3, var4);
        this.updateLightLevel(var1, var2, var3, var4);
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        this.updateLightLevel(var1, var2, var3, var4);
    }

    private void updateLightLevel(World world, int x, int y, int z) {
        if(world.multiplayerWorld) {
            return;
        }

        world.markBlocksDirty(x, y, z, x, y, z);
        world.scheduleLightingUpdate(EnumSkyBlock.Block, x, y, z, x, y, z);
    }

    private int getRedstonePowerLevel(IBlockAccess world, int x, int y, int z) {
        int powerLevel = 0;
        powerLevel = Math.max(powerLevel, this.getPowerFromSide(world, x, y - 1, z, 0));
        powerLevel = Math.max(powerLevel, this.getPowerFromSide(world, x, y + 1, z, 1));
        powerLevel = Math.max(powerLevel, this.getPowerFromSide(world, x, y, z - 1, 2));
        powerLevel = Math.max(powerLevel, this.getPowerFromSide(world, x, y, z + 1, 3));
        powerLevel = Math.max(powerLevel, this.getPowerFromSide(world, x - 1, y, z, 4));
        powerLevel = Math.max(powerLevel, this.getPowerFromSide(world, x + 1, y, z, 5));
        return powerLevel;
    }

    private int getPowerFromSide(IBlockAccess world, int x, int y, int z, int side) {
        int blockId = world.getBlockId(x, y, z);
        if(blockId == 0) {
            return 0;
        }

        if(blockId == Block.redstoneWire.blockID) {
            return world.getBlockMetadata(x, y, z) & 15;
        }

        Block block = Block.blocksList[blockId];
        if(block == null || !block.canProvidePower()) {
            return 0;
        }

        if(!(world instanceof World)) {
            return 0;
        }

        World worldObj = (World)world;
        int strongPower = block.isProvidingStrongPower(worldObj, x, y, z, side);
        int weakPower = block.isProvidingWeakPower(worldObj, x, y, z, side);
        return Math.max(strongPower, weakPower);
    }
}
