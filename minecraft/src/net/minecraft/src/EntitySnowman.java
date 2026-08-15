package net.minecraft.src;

public class EntitySnowman extends EntityWolf{
    private static final ItemStack defaultHeldItem = new ItemStack(Item.snowball, 1);


    public EntitySnowman(World var1){
        super(var1);
        this.texture = "/mod_mob/snowman.png";
        this.setSize(1.0F, 2.0F);
        this.health = 20;
        this.moveSpeed = 0.5F;
    }
    //from wolf
    @Override
    public String getEntityTexture() {
        return "/mod_mob/snowman.png";
    }
    @Override
    public boolean interact(EntityPlayer var1) {
        ItemStack var2 = var1.inventory.getCurrentItem();
        if(!this.isWolfTamed()) {
            if(var2 != null && var2.itemID == Item.diamond.shiftedIndex && !this.isWolfAngry()) {
                --var2.stackSize;
                if(var2.stackSize <= 0) {
                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                }

                if(!this.worldObj.multiplayerWorld) {
                    if(this.rand.nextInt(3) == 0) {
                        this.setWolfTamed(true);
                        this.setPathToEntity((PathEntity)null);
                        this.setWolfSitting(true);
                        this.health = 20;
                        this.setWolfOwner(var1.username);
                        this.showHeartsOrSmokeFX(true);
                        this.worldObj.func_9425_a(this, (byte)7);
                    } else {
                        this.showHeartsOrSmokeFX(false);
                        this.worldObj.func_9425_a(this, (byte)6);
                    }
                }

                return true;
            }
        } else {
            if(var2 != null && Item.itemsList[var2.itemID] instanceof ItemFood) {
                ItemFood var3 = (ItemFood)Item.itemsList[var2.itemID];
                if(var3.getIsSnowmansFavoriteFood() && this.dataWatcher.getWatchableObjectInt(18) < 20) {
                    --var2.stackSize;
                    if(var2.stackSize <= 0) {
                        var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                    }

                    this.heal(((ItemFood)Item.pieceCake).getHealAmount());
                    return true;
                }
            }

            if(var1.username.equalsIgnoreCase(this.getWolfOwner())) {
                if(!this.worldObj.multiplayerWorld) {
                    this.setWolfSitting(!this.isWolfSitting());
                    this.isJumping = false;
                    this.setPathToEntity((PathEntity)null);
                }

                return true;
            }
        }

        return false;
    }

//
    protected void attackEntity(Entity var1, float var2) {
        if(var2 < 10.0F) {
            double var3 = var1.posX - this.posX;
            double var5 = var1.posZ - this.posZ;
            if(this.attackTime == 0) {
                EntitySnowball var7 = new EntitySnowball(this.worldObj, this);
                var7.posY += (double)1.4F;
                double var8 = var1.posY + (double)var1.getEyeHeight() - (double)0.2F - var7.posY;
                float var10 = MathHelper.sqrt_double(var3 * var3 + var5 * var5) * 0.2F;
                this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
                this.worldObj.entityJoinedWorld(var7);
                var7.setSnowballHeading(var3, var8 + (double)var10, var5, 0.7F, 5.0F);
                this.attackTime = 30;
            }

            this.rotationYaw = (float)(Math.atan2(var5, var3) * 180.0D / (double)((float)Math.PI)) - 90.0F;
            this.hasAttacked = true;
        }

    }
    protected String getLivingSound() {
        return "mob.skeleton";
    }

    protected String getHurtSound() {
        return "mob.skeletonhurt";
    }

    protected String getDeathSound() {
        return "mob.skeletonhurt";
    }

    protected void dropFewItems() {
        int var1 = this.rand.nextInt(3);

        int var2;
        for(var2 = 0; var2 < var1; ++var2) {
            this.dropItem(Block.ice.blockID, 1);
        }

        var1 = this.rand.nextInt(3);

        for(var2 = 0; var2 < var1; ++var2) {
            this.dropItem(Item.snowball.shiftedIndex, 1);
        }

    }

    public void writeEntityToNBT(NBTTagCompound var1) {
        super.writeEntityToNBT(var1);
    }

    public void readEntityFromNBT(NBTTagCompound var1) {
        super.readEntityFromNBT(var1);
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }
}
