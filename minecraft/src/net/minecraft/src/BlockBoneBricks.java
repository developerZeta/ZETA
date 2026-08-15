package net.minecraft.src;

public class BlockBoneBricks extends Block{
    public BlockBoneBricks(int var1){
        super(var1,Material.rock);
    }
    public int getBlockTextureFromSide(int side) {

        return ModBlocksRenderer.getTextureIndex(6, 1);
    }
}
