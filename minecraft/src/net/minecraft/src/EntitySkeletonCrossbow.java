package net.minecraft.src;

public class EntitySkeletonCrossbow extends EntitySkeleton{
    private static final ItemStack defaultHeldItem = new ItemStack(Item.crossbow, 1);

    public EntitySkeletonCrossbow(World var1) {
        super(var1);
        this.texture = "/mob/skeleton.png";
    }
    protected void attackEntity(Entity var1, float var2) {
        if(var2 < 16.0F) {
            double var3 = var1.posX - this.posX;
            double var5 = var1.posZ - this.posZ;
            if(this.attackTime == 0) {
                EntityArrow var7 = new EntityCrossbowBolt(this.worldObj, this);
                var7.posY += (double)1.0F;
                double var8 = var1.posY + (double)var1.getEyeHeight() - (double)0.2F - var7.posY;
                float var10 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2F;
                this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
                this.worldObj.entityJoinedWorld(var7);
                var7.setArrowHeading(var3, var8 + (double)var10, var5, 1.0F, 1.0F);
                this.attackTime = 20;
            }

            this.rotationYaw = (float)(Math.atan2(var5, var3) * 180.0D / (double)((float)Math.PI)) - 90.0F;
            this.hasAttacked = true;
        }

    }
    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }
}
