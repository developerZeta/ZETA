package net.minecraft.src;

import java.util.Random;

public class ItemTungstenBattleaxe extends ItemTool{

    private int weaponDamage;
    private static Block[] blocksEffectiveAgainst = new Block[]{
            Block.planks, Block.bookShelf, Block.wood, Block.chest,
            Block.workbench, Block.oreCoal, Block.sandStone, Block.cloth, Block.blockLapis,
            Block.oreLapis, Block.web, Block.blockMossylog, Block.netherWood, Block.fence,Block.ladder,Block.burnedPlanks,Block.doorWood
    };
    public ItemTungstenBattleaxe(int var1){
        super(var1, 8, EnumToolMaterial.TUNGSTEN, blocksEffectiveAgainst);
		this.weaponDamage = 20;
    }
    public float getStrVsBlock(ItemStack var1, Block var2) {
        for(int var3 = 0; var3 < this.blocksEffectiveAgainst.length; ++var3) {
            if(this.blocksEffectiveAgainst[var3] == var2) {
                return 6.0F;
            }
        }
        return 1.0F;
    }


    public boolean hitEntity(ItemStack var1, EntityLiving var2, EntityLiving var3) {
        var1.damageItem(2, var3);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack var1, int var2, int var3, int var4, int var5, EntityLiving var6) {
        var1.damageItem(1, var6);
        return true;
    }

    public int getDamageVsEntity(Entity var1) {
        return this.weaponDamage;
    }

    public boolean isFull3D() {
        return true;
    }


    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
        if(var7 == 0) {
            --var5;
        }

        if(var7 == 1) {
            ++var5;
        }

        if(var7 == 2) {
            --var6;
        }

        if(var7 == 3) {
            ++var6;
        }

        if(var7 == 4) {
            --var4;
        }

        if(var7 == 5) {
            ++var4;
        }

        int var8 = var3.getBlockId(var4, var5, var6);
        int var9 = var3.getBlockId(var4, var5 - 1, var6);
        // Place GREEN fire on blockSulfur, normal fire elsewhere
        if(var8 == 0) {
            var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            if(var9 == Block.blockSulfur.blockID) {
                var3.setBlockWithNotify(var4, var5, var6, Block.greenFire.blockID);
            } else {
                var3.setBlockWithNotify(var4, var5, var6, Block.fire.blockID);
            }
        }

        var1.damageItem(1, var2);
        return true;
    }

    public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2) {

        if(var2 instanceof Entity) {
            if(!var2.worldObj.multiplayerWorld) {
                //var2.worldObj.createExplosion(var2, var2.posX, var2.posY + 1, var2.posZ, 1.0F);
                var2.onEntityUpdate();
                var2.getHurtSound();
               // var2.attackEntityFrom(var2,1);
                //getDamageVsEntity(var2);
               // var2.knockBack(var2,0, var2.posX, var2.posZ);
                var2.dealFireDamage(2);
                var2.fire = 40;
                var1.damageItem(1, var2);
            }
            return true;
        }
        return false;
    }


    public void saddleEntity(ItemStack var1, EntityLiving var2) {
        this.itemInteractionForEntity(var1, var2);
    }

    /*
    public void specialAttack(ItemStack var1, EntityLiving var2, EntityPlayer player){
        this.itemInteractionForEntity(var1, var2, player);
    }
*/
    public boolean canHarvestBlock(Block var1) {
        return var1.blockID == Block.cobblestone.blockID ||
                var1.blockID == Block.stone.blockID ||
                var1.blockID == Block.oreCoal.blockID ||
                var1.blockID == Block.oreIron.blockID ||
                var1.blockID == Block.oreGold.blockID ||
                var1.blockID == Block.oreDiamond.blockID;
    }
}
