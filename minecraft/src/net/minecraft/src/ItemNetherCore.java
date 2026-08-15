package net.minecraft.src;

public class ItemNetherCore extends Item {
    public ItemNetherCore(int var1) {
        super(var1);
        this.maxStackSize = 1;
        this.setMaxDamage(6);
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
        if(var8 == 0) {
            var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                var3.setBlockWithNotify(var4, var5, var6, Block.greenFire.blockID);

        }

        var1.damageItem(1, var2);
        return true;
    }

    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        float var4 = 1.0F;
        float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var4;
        float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var4;
        double var7 = var3.prevPosX + (var3.posX - var3.prevPosX) * (double)var4;
        double var9 = var3.prevPosY + (var3.posY - var3.prevPosY) * (double)var4 + 1.62D - (double)var3.yOffset;
        double var11 = var3.prevPosZ + (var3.posZ - var3.prevPosZ) * (double)var4;
        Vec3D var13 = Vec3D.createVector(var7, var9, var11);
        float var14 = MathHelper.cos(-var6 * ((float)Math.PI / 180.0F) - (float)Math.PI);
        float var15 = MathHelper.sin(-var6 * ((float)Math.PI / 180.0F) - (float)Math.PI);
        float var16 = -MathHelper.cos(-var5 * ((float)Math.PI / 180.0F));
        float var17 = MathHelper.sin(-var5 * ((float)Math.PI / 180.0F));
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = 5.0D;
        Vec3D var23 = var13.addVector((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
        MovingObjectPosition var24 = var2.rayTraceBlocks_do(var13, var23, true);
        if(var24 == null) {
            if(!var2.multiplayerWorld) {
                EntityHellCreeper var25 = new EntityHellCreeper(var2);
                var25.setLocationAndAngles(var3.posX, var3.posY + (double)var3.getEyeHeight(), var3.posZ, var3.rotationYaw, var3.rotationPitch);
                var2.entityJoinedWorld(var25);
                var2.playSoundAtEntity(var3, "random.fuse", 1.0F, 0.5F);
            }
            var1.damageItem(1, var3);
        }
        return var1;
    }
}

