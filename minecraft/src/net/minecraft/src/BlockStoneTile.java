package net.minecraft.src;

public class BlockStoneTile extends Block{
    public BlockStoneTile(int var1){
        super(var1,Material.rock);
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(5, 0);
    }
}
