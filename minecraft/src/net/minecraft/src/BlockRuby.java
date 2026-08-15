package net.minecraft.src;

public class BlockRuby extends Block{
    public BlockRuby(int var1){
        super(var1,Material.iron);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundMetalFootstep);
        this.setBlockName("blockRuby");
    }
    
    public int getBlockTextureFromSide(int side) {
        return ModBlocksRenderer.getTextureIndex(10, 1);
    }
    
    @Override
    public boolean canProvidePower() {
        return true;
    }
    
    @Override
    public boolean isIndirectlyPoweringTo(World var1, int var2, int var3, int var4, int var5) {
        return true;
    }
    
    @Override
    public boolean isPoweringTo(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        return true;
    }
    
    public int isProvidingStrongPower(World var1, int var2, int var3, int var4, int var5) {
        return 15;  // Max redstone signal
    }
    
    public int isProvidingWeakPower(World var1, int var2, int var3, int var4, int var5) {
        return 15;  // Max redstone signal
    }
}
