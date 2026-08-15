package net.minecraft.src;

import java.util.Random;

public class BlockTitaniumOre extends Block{
    public BlockTitaniumOre(int var1){
        super(var1, Material.rock);
    }
    public int idDropped() {
        return Block.oreTitanium.blockID;
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(14,12);
    }
}
