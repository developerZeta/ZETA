package net.minecraft.src;

/**
 * @deprecated Use ItemSteelBase instead. Kept for backward compatibility.
 */
@Deprecated
public class ItemCrudeSteel extends ItemSteelBase {
    public ItemCrudeSteel(int var1) {
        super(var1);
    }

    /**
     * @deprecated Use getCarbonPer(int damage) instead
     */
    @Deprecated
    public double carbonPer(int var2) {
        return getCarbonPer(var2);
    }

    /**
     * @deprecated Use getTemperature(int damage) instead
     */
    @Deprecated
    public double tempOfIngot(int var3) {
        return getTemperature(var3);
    }
}
