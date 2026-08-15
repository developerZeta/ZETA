package net.minecraft.src;

import java.util.Random;

public class EntityMimic extends EntitySlime{
    private static final String TEXTURE_CLOSED = "/mod_mob/mimic.png";
    private static final String TEXTURE_OPEN_1 = "/mod_mob/mimic_open1.png";
    private static final String TEXTURE_OPEN_2 = "/mod_mob/mimic_open2.png";
    private String lastTexture = "";
    private int slimeJumpDelay = 0;

    @Override
    public boolean allowParticles(){
        return false;
    }



    public EntityMimic(World world){
        super(world);
        this.health =30;
        this.texture = TEXTURE_CLOSED;
        this.setSlimeSize(2);
        this.health = this.getMaxHealth();
    }

    @Override
    public void setEntityDead() {
        int var2 = this.getSlimeSize();
        for(int var3 = 0; var3 < var2 * 16; ++var3) {
            float var4 = this.rand.nextFloat() * (float) Math.PI * 2.0F;
            float var5 = this.rand.nextFloat() * 0.5F + 0.5F;
            float var6 = MathHelper.sin(var4) * (float) var2 * 0.5F * var5;
            float var7 = MathHelper.cos(var4) * (float) var2 * 0.5F * var5;
            this.worldObj.spawnParticle("reddust", this.posX + (double) var6, this.boundingBox.minY, this.posZ + (double) var7, 0.0D, 0.0D, 0.0D);
        }
        this.isDead = true;
    }

    protected int getDropItemId() {
        return Block.trapChest.blockID;
    }

    @Override
    protected String getHurtSound() {
        return "damage.fallbig";
    }
    @Override
    protected String getDeathSound() {
        return "damage.hurtflesh";
    }

    @Override
    public void onUpdate() {
        this.updateTextureForPlayerDistance();

        boolean var1 = this.onGround;
        super.onUpdate();
        if(this.onGround && !var1) {
            int var2 = this.getSlimeSize();

            for(int var3 = 0; var3 < var2 * 8; ++var3) {
                float var4 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float var5 = this.rand.nextFloat() * 0.5F + 0.5F;
                float var6 = MathHelper.sin(var4) * (float)var2 * 0.5F * var5;
                float var7 = MathHelper.cos(var4) * (float)var2 * 0.5F * var5;
                    this.worldObj.spawnParticle("smoke", this.posX + (double) var6, this.boundingBox.minY, this.posZ + (double) var7, 0.0D, 0.0D, 0.0D);

            }

            if(var2 > 2) {
                this.worldObj.playSoundAtEntity(this, "mob.mimic", this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }


        }


    }

    private void updateTextureForPlayerDistance() {
        EntityPlayer var1 = this.worldObj.getClosestPlayerToEntity(this, 7.0D);
        String var2 = TEXTURE_CLOSED;

        if(var1 != null) {
            var2 = this.getDistanceSqToEntity(var1) < 25.0D ? TEXTURE_OPEN_2 : TEXTURE_OPEN_1;
        }

        if(!var2.equals(this.lastTexture)) {
            this.texture = var2;
            this.lastTexture = var2;
        }
    }

    @Override
    protected void updatePlayerActionState() {
        this.func_27021_X();
        EntityPlayer var1 = this.worldObj.getClosestPlayerToEntity(this, 16.0D);
        if(var1 != null) {
            this.faceEntity(var1, 10.0F, 20.0F);
        }

        if(this.onGround && this.slimeJumpDelay-- <= 0) {
            this.slimeJumpDelay = this.rand.nextInt(10) + 2;
            if(var1 != null) {
                this.slimeJumpDelay /= 3;
            }

            this.isJumping = true;
           if(this.getSlimeSize() > 1) {
               this.worldObj.playSoundAtEntity(this, "step.wood", this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
           }


            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
            this.moveForward = (float)(5 * this.getSlimeSize());
        } else {
            this.isJumping = false;
            if(this.onGround) {
                this.moveStrafing = this.moveForward = 2.0F;
            }
        }

    }
    @Override
    public void onCollideWithPlayer(EntityPlayer var1) {
        int attack = 5+this.getSlimeSize();
        if(this.canEntityBeSeen(var1) && (double)this.getDistanceToEntity(var1) < 0.6D * (double)attack && var1.attackEntityFrom(this, attack)) {
            this.worldObj.playSoundAtEntity(this, "mob.mimic", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        }

    }


    public int getMaxHealth() {
        return 45;
    }

    protected void dropFewItems() {
        int chance = this.rand.nextInt(100);
        int ruby = this.rand.nextInt(2);
        int garnets = this.rand.nextInt(4);
        int gold = this.rand.nextInt(7);
        if (chance > 33 && chance < 66){
            for (int i = 0; i < ruby; i++) {
                this.dropItem(Item.ruby.shiftedIndex, 1);
            }
        }
        if(chance<=23 || chance>=86) {
            for (int i = 0; i < gold; ++i) {
                this.dropItem(Item.ingotGold.shiftedIndex, 1);
            }
        }
        if(chance>=76 || chance<=23) {
            for (int i = 0; i < garnets; ++i) {
                this.dropItem(Item.garnet.shiftedIndex, 1);
            }
        }

        if(chance<=66) {
            for (int i = 0; i < garnets; ++i) {
                this.dropItem(Item.ingotIron.shiftedIndex, 1);
            }
        }
        if(chance>=70) {
                this.dropItem(Item.saddle.shiftedIndex, 1);
        }
        if(chance<=80 && chance>60) {
            this.dropItem(Item.bucketEmpty.shiftedIndex, 1);
        }
        if(chance==99){
            this.dropItem(Item.philosophyStone.shiftedIndex,1 );
        }
        if(chance<=16) {
            for (int i = 0; i < garnets+2; ++i) {
                this.dropItem(Item.cookie.shiftedIndex, 1);
            }
        }
        if(chance<=46) {
            for (int i = 0; i < garnets+3; ++i) {
                this.dropItem(Item.redstone.shiftedIndex, 1);
            }
        }
        if(chance<=5) {
            for (int i = 0; i < garnets+2; ++i) {
                this.dropItem(Item.iridiumIngot.shiftedIndex, 1);
            }
        }
        if(chance>3 && chance<=14 || chance>75) {
            for (int i = 0; i < garnets+1; ++i) {
                this.dropItem(Item.gunpowder.shiftedIndex, 1);
            }
        }
        if(chance>30 && chance<=45) {
            this.dropItem(Item.bread.shiftedIndex, 1);
        }

    }


}
