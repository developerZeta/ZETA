package net.minecraft.src;

public class ItemCrimsonOrb extends Item{
    public ItemCrimsonOrb(int var1){
        super(var1);
        this.maxStackSize = 1;
        this.setMaxDamage(30);

    }
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        if(var1.getItemDamage() >= var1.getMaxDamage()) {
            return var1;
        }
        if(!var2.multiplayerWorld) {
            // Spawn Beholder 3 blocks above player with random offset
            EntityBeholder beholder = new EntityBeholder(var2);
            double spawnX = var3.posX + (double)(var3.rand.nextFloat() * 4.0F - 2.0F);
            double spawnY = var3.posY + 3.0D;
            double spawnZ = var3.posZ + (double)(var3.rand.nextFloat() * 4.0F - 2.0F);
            beholder.setLocationAndAngles(spawnX, spawnY, spawnZ, var2.rand.nextFloat() * 360.0F, 0.0F);
            var2.entityJoinedWorld(beholder);
            // Play sound AFTER spawning
            var2.playSoundAtEntity(var3, "ambient.cave.cave1", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }
        /*
        if(!var2.multiplayerWorld) {
            Entity giant = EntityList.createEntityInWorld("Gashadokuro", var2);
            if(giant == null) {

                giant = new EntityGiantSkeleton(var2);
            }
            giant.setLocationAndAngles(var3.posX+(var3.rand.nextInt()%16)+5, var3.posY+3, var3.posZ+(var3.rand.nextInt()%16)+5, var2.rand.nextFloat() * 360.0F, 0.0F);
            var2.entityJoinedWorld(giant);
        }*/
        var1.damageItem(30, var3);

        return var1;
    }

    public void onCreated(ItemStack var1, World var2, EntityPlayer var3) {
        var1.setItemDamage(this.getMaxDamage());
    }
}
