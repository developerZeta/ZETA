package net.minecraft.src;

public class ItemDough extends Item {
    public ItemDough(int var1){
        super(var1);
    }
    public int getColorFromDamage(int var1) {
        // Simple yellow - no brightness adjustment
        return 0xFFFF00;
    }

    public int getColorFromItemStack(ItemStack var1, int var2) {
        return 0xFFFF00;
    }
}
