package net.minecraft.src;

public class BlockPillar extends Block{
    public BlockPillar(int var1){
        super(var1,Material.rock);
    }

    public int getBlockTextureFromSide(int side) {
        if (side==0 || side==1){
            //uses mod_Terrain.png
            return this.blockIndexInTexture+81;
        }
        return ModBlocksRenderer.getTextureIndex(1, 6);
    }
}
