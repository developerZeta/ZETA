package net.minecraft.src;

public class ItemGarnet extends Item{
    public ItemGarnet(int var1){
        super(var1);
    }

    public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2) {
        if (var2 instanceof EntityPigHuman) {
            EntityPigHuman var3 = (EntityPigHuman) var2;
            ItemStack var7 = new ItemStack(Item.wolfram, 1);
            ItemStack var8 = new ItemStack(Block.blueBricks, 32);
            ItemStack var9 = new ItemStack(Block.obsidian,32);
            ItemStack var10 = new ItemStack(Block.glass,64);
            ItemStack var11 = new ItemStack(Block.ice,32);
            ItemStack var12 = new ItemStack(Item.chainlink,16);
            ItemStack var13 = new ItemStack(Block.blockMossylog,32);
            ItemStack var14 = new ItemStack(Block.oreCoal,16);
            ItemStack var15 = new ItemStack(Item.philosophyStone,1);
            ItemStack var16 = new ItemStack(Block.oreNetherCoal,16);

            int chance1 = this.rand.nextInt(100);

            if (!var3.worldObj.multiplayerWorld) {
                var1.stackSize -- ;

                if(chance1 <2) {
                    var2.entityDropItem(var7, 1);

                } else if(chance1<10){
                    var2.entityDropItem(var8, 1);
                } else if(chance1<25){
                    var2.entityDropItem(var9, 1);
                }else if(chance1<36){
                    var2.entityDropItem(var10, 1);
                }else if(chance1<44){
                    var2.entityDropItem(var11, 1);
                } else if(chance1<55){
                    var2.entityDropItem(var12, 1);
                }else if(chance1<60){
                    var2.entityDropItem(var13, 1);
                } else if(chance1<70){
                    var2.entityDropItem(var14, 1);
                } else if(chance1<72){
                    var2.entityDropItem(var15, 1);
                }else if(chance1<78){
                    var2.entityDropItem(var15, 1);
                }

            }
            return true;
        }
        return false;
    }
    public void saddleEntity(ItemStack var1, EntityLiving var2) {
        this.itemInteractionForEntity(var1, var2);
    }
}
