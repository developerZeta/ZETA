package net.minecraft.src;

public class ItemTungstenArmor extends ItemArmor{
    public ItemTungstenArmor(int var1, int var2, int var3, int var4) {
        super(var1, var2, var3, var4);
        this.setMaxDamage((int)(this.getMaxDamage() * 0.25F));
    }
}
