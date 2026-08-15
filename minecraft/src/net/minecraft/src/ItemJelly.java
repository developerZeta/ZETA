package net.minecraft.src;

public class ItemJelly extends ItemFood{

    public ItemJelly(int i, int i1, boolean b) {
        super(i, i1, b);
    }
    
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        --var1.stackSize;
        var3.heal(this.getHealAmount());
        
        // Give Jump Boost effect for 2 minutes (2400 ticks)
        JumpBoostManager.addJumpBoost(var3, 2400);
        
        return var1;
    }
}
