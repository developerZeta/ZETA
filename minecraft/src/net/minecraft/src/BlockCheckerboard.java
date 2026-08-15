package net.minecraft.src;

public class BlockCheckerboard extends Block{
    public BlockCheckerboard(int var1){
        super(var1,Material.rock);
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(6, 3);
    }
}
