package net.minecraft.src;

/**
 * @deprecated Use ItemSteelBase instead. Kept for backward compatibility.
 */
@Deprecated
public class ItemHotIron extends ItemSteelBase {
    public ItemHotIron(int var1) {
        super(var1);
    }

    /**
     * @deprecated Use getCarbonPer(int damage) instead
     */
    @Deprecated
    public double carbonPer(int var1) {
        return getCarbonPer(var1);
    }

    /**
     * @deprecated Use getTemperature(int damage) instead
     */
    @Deprecated
    public double tempOfIngot(int var2) {
        return getTemperature(var2);
    }
}
