package net.minecraft.src;

public class BlockBlueBricks extends Block{
    public BlockBlueBricks(int var1){
        super(var1,Material.rock);
    }
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(7, 1);
    }

}
