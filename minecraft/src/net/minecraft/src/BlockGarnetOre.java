package net.minecraft.src;

public class BlockGarnetOre extends Block{
    public BlockGarnetOre(int var1){
        super(var1, Material.rock);
    }
    public int idDropped(int var1, java.util.Random var2) {
        return Item.garnet.shiftedIndex;
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(13,13);
    }
}
