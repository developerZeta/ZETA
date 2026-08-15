package net.minecraft.src;

import java.util.Random;

public class BlockNetherIronOre extends Block{
    public BlockNetherIronOre(int var1,int var2) {
        super(var1, var2, Material.rock);
    }

    public int idDropped(int var1, Random var2) {
        return Block.netherIron.blockID;
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(15, 13);
    }
    public int quantityDropped(Random var1) {
        return 1;
    }




}
