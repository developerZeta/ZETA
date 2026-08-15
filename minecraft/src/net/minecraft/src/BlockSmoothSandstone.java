package net.minecraft.src;

public class BlockSmoothSandstone extends Block{
    public BlockSmoothSandstone(int var1){
        super(var1,Material.sand);
    }
    public int getBlockTextureFromSide(int side) {
        return 11*16;
    }
}
