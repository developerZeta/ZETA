package net.minecraft.src;

public class ItemCrossbow extends Item{
        public ItemCrossbow(int var1) {
            super(var1);
            this.maxStackSize = 1;
            this.setMaxDamage(384);
        }

        public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {

            if(var3.crossbowCooldown > 0) {
                return var1;
            }

            if(var3.inventory.consumeInventoryItem(Item.arrow.shiftedIndex)) {
                var2.playSoundAtEntity(var3, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                if(!var2.multiplayerWorld) {
                    var2.entityJoinedWorld(new EntityCrossbowBolt(var2, var3));
                }
                var3.crossbowCooldown = 20;
            }
            var1.damageItem(1,var3);
            return var1;
        }


}
