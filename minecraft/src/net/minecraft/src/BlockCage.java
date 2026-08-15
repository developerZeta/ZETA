package net.minecraft.src;

public class BlockCage extends Block{
    public BlockCage(int var1){
        super(var1, Material.iron);
    }
    public boolean isOpaqueCube() {
        return false;
    }
}
