package net.minecraft.src;

public interface SteelCarbonPer {
    /**
     * Get carbon percentage (0.0 to 4.0+)
     * @param damage Item damage value storing carbon data
     * @return Carbon percentage
     */
    double getCarbonPer(int damage);
    
    /**
     * Get temperature in Celsius
     * @param damage Item damage value
     * @return Temperature
     */
    double getTemperature(int damage);
    
    /**
     * Determine what item this should become based on carbon/temp
     * @param carbonPer Carbon percentage
     * @param temp Temperature
     * @return Item ID to transform into
     */
    int getNextStageItem(double carbonPer, double temp);
    
    /**
     * Encode carbon and temp into damage value
     */
    int encodeCarbonTemp(double carbonPer, double temp);
    
    /**
     * Transform item based on carbon/temp changes
     * @param stack Current item stack
     * @param newCarbon New carbon percentage
     * @param newTemp New temperature
     * @return Transformed item stack
     */
    ItemStack transformItem(ItemStack stack, double newCarbon, double newTemp);
}
