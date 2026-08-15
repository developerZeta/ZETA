package net.minecraft.src;

public class BlockNetherMycelium extends Block{
    public BlockNetherMycelium(int var1){
        super(var1, Material.sand);
    }
    private static final int SIDE_TEXTURE = ModBlocksRenderer.getTextureIndex(6, 6);
    private static final int TOP_TEXTURE = ModBlocksRenderer.getTextureIndex(6, 7);


    public int getBlockTextureFromSide(int side) {
        if(side == 1) {
            return TOP_TEXTURE;
        }

        return SIDE_TEXTURE;
    }

}
