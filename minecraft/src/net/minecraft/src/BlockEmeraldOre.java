package net.minecraft.src;

import java.util.Random;

public class BlockEmeraldOre extends Block{
    public BlockEmeraldOre(int var1){
        super(var1,Material.rock);
    }
    public int idDropped(int var1, Random var2) {
        return Item.emerald.shiftedIndex;
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(15, 11);
    }
    public int quantityDropped(Random var1) {
        return 1;
    }
}
