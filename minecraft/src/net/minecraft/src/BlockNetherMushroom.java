package net.minecraft.src;

public class BlockNetherMushroom extends BlockFlower {
    public BlockNetherMushroom(int var1) {
        super(var1, 0);
        float var2 = 0.2F;
        this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var2 * 2.0F, 0.5F + var2);
    }

    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(13, 0);
    }

    protected boolean canThisPlantGrowOnThisBlockID(int var1) {
        return var1 == Block.netherSoil.blockID;
    }

    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
        return super.canPlaceBlockAt(var1, var2, var3, var4) && var1.worldProvider.isNether;
    }

    public boolean canBlockStay(World var1, int var2, int var3, int var4) {
        return var1.worldProvider.isNether && var3 >= 0 && var3 < 128 && this.canThisPlantGrowOnThisBlockID(var1.getBlockId(var2, var3 - 1, var4));
    }
}
