package net.minecraft.src;

import java.util.Random;

public class ItemRuby extends Item{
    public ItemRuby(int var1){
        super(var1);

    }


    public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2) {
        if (var2 instanceof EntityPigHuman) {
            EntityPigHuman var3 = (EntityPigHuman) var2;
            ItemStack var7 = new ItemStack(Item.wolfram, 1);
            ItemStack var8 = new ItemStack(Block.blueBricks, 16);
            ItemStack var9 = new ItemStack(Block.boneBricks,32);
            ItemStack var10 = new ItemStack(Block.stoneTile,32);
            ItemStack var11 = new ItemStack(Block.flintBricks,32);
            ItemStack var12 = new ItemStack(Item.chainlink,16);
            ItemStack var13 = new ItemStack(Block.mossyBricks,16);
            ItemStack var14 = new ItemStack(Block.brick,16);

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


