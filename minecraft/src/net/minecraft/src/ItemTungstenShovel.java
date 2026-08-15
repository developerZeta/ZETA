package net.minecraft.src;



    public class ItemTungstenShovel extends ItemTool {
        private static Block[] blocksEffectiveAgainst = new Block[]{Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand};

        public ItemTungstenShovel(int var1) {
            super(var1, 3, EnumToolMaterial.TUNGSTEN, blocksEffectiveAgainst);
        }

        public boolean canHarvestBlock(Block var1) {
            return var1 == Block.snow ? true : var1 == Block.blockSnow;
        }

        public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2) {

            if(var2 instanceof Entity) {
                if(!var2.worldObj.multiplayerWorld) {
                    //var2.worldObj.createExplosion(var2, var2.posX, var2.posY + 1, var2.posZ, 1.0F);
                    //var2.knockBack(var2,0, var2.posY, var2.posY);
                    //var2.dealFireDamage(2);
                    //var1.damageItem(1, var2);
                }
                return true;
            }
            return false;
        }


        public void saddleEntity(ItemStack var1, EntityLiving var2) {
            this.itemInteractionForEntity(var1, var2);
        }



    }


