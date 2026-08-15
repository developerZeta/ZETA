package net.minecraft.src;

public class BlockRubyOre extends Block{
    public BlockRubyOre(int var1){
        super(var1, Material.rock);
    }
    public int idDropped(int var1, java.util.Random var2) {
        return Item.ruby.shiftedIndex;
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(13,12);
    }
}
