package net.minecraft.src;

public class BlockGarnet extends Block {

    public BlockGarnet(int var1) {
        super(var1,Material.rock);
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(10, 2);
    }

    public int getRenderColor(int var1) {
        return 0x3366FF;
    }

    public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
        return 0x3366FF;
    }

}
