package net.minecraft.src;

public class EntityGiantSkeleton extends EntityMob {

    private static final float SCALE = 2.0F;
    private static final int SUMMON_INTERVAL_TICKS = 900;

    public EntityGiantSkeleton(World world) {
        super(world);

        this.texture = "/mob/skeleton.png";
        this.moveSpeed = 0.60F;
        this.isImmuneToFire = true;
        applyScaleAttributes();
    }

    private void applyScaleAttributes() {

        // Scale hitbox
        this.setSize(0.6F * SCALE, 1.8F * SCALE);

        // Scale health (normal skeleton = 20)
        this.health = MathHelper.floor_float(20.0F * SCALE);

        // Scale melee damage
        this.attackStrength = MathHelper.floor_float(4.0F * SCALE);
    }

    protected void attackEntity(Entity target, float distance) {

        float reach = 2.0F * 1.5F;
        // 2.0F = normal reach
        // Multiply by SCALE (2.0)

        if (distance < reach && target.boundingBox.maxY > this.boundingBox.minY
                && target.boundingBox.minY < this.boundingBox.maxY) {

            this.attackTime = 20;
            target.attackEntityFrom(this, this.attackStrength);
        } else if (distance < 24.0F && this.attackTime == 0 && this.canEntityBeSeen(target)) {
            double dx = target.posX - this.posX;
            double dz = target.posZ - this.posZ;
            double dy = target.posY + (double)target.getEyeHeight() - (this.posY + this.height * 0.6D);

            EntityCannonball ball = new EntityCannonball(this.worldObj, this, dx, dy, dz);
            Vec3D look = this.getLook(1.0F);
            double offset = (double)this.width * 0.5D + 1.0D;
            ball.posX = this.posX + look.xCoord * offset;
            ball.posY = this.posY + this.height * 0.6D;
            ball.posZ = this.posZ + look.zCoord * offset;
            ball.directHitDamage = 12;
            this.worldObj.playSoundAtEntity(this, "mob.ghast.fireball", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
            this.worldObj.entityJoinedWorld(ball);

            this.attackTime = 100;
            this.rotationYaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
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

    public boolean attackEntityFrom(Entity source, int amount) {
        if(source == null || source instanceof EntityTNTPrimed || source instanceof EntityCreeper || source instanceof EntityFireball || source instanceof EntityCannonball) {
            return false;
        }

        return super.attackEntityFrom(source, amount);
    }

    public void fall(float distance) {
        // Immune to fall damage.
    }

    protected void jump() {
        // Scale jump height with size.
        this.motionY = (double)(0.42F * SCALE);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(this.worldObj.multiplayerWorld) {
            return;
        }

        if(this.ticksExisted % SUMMON_INTERVAL_TICKS != 0) {
            return;
        }

        int count = this.rand.nextInt(3);
        for(int i = 0; i < count; ++i) {
            EntitySkeletonWarrior var1 = new EntitySkeletonWarrior(this.worldObj);
            double angle = this.rand.nextDouble() * Math.PI * 2.0D;
            double radius = 1.5D + this.rand.nextDouble() * 1.5D;
            double x = this.posX + Math.cos(angle) * radius;
            double z = this.posZ + Math.sin(angle) * radius;
            double y = this.posY;
            var1.setLocationAndAngles(x, y, z, this.rand.nextFloat() * 360.0F, 0.0F);
            this.worldObj.entityJoinedWorld(var1);
        }
    }

   // private static final ItemStack defaultHeldItem = new ItemStack(Item.bone, 1);
    protected int getDropItemId() {
        return Item.topaz.shiftedIndex;
    }
    // it drops a few bones
    protected void dropFewItems() {
        int count = this.rand.nextInt(3);

        for (int i = 0; i < count; i++) {
            this.dropItem(Item.bone.shiftedIndex, 2);
        }
        int ruby=this.rand.nextInt(2);
        for(int i=0;i<ruby;i++){
            this.dropItem(Item.ruby.shiftedIndex, 1);
        }

        int garnets = this.rand.nextInt(3);
        for(int i = 0; i < garnets; ++i) {
            this.dropItem(Item.garnet.shiftedIndex, 1);
        }

        int gold = this.rand.nextInt(7);
        for(int i = 0; i < gold; ++i) {
            this.dropItem(Item.ingotGold.shiftedIndex, 1);
        }
    }
    public int getMaxSpawnedInChunk() {
        return 2; // Prevent spam
    }

    public boolean getCanSpawnHere() {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.boundingBox.minY);
        int var3 = MathHelper.floor_double(this.posZ);
        if(this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) <= 0) {
            return false;
        }
        return super.getCanSpawnHere();
    }
}
   /* public class EntityGiantSkeleton extends EntitySkeleton {

        public EntityGiantSkeleton(World world) {
            super(world);

            this.texture = "/mob/skeleton.png";
            this.moveSpeed = 0.35F;

            // Random size between 3.0 and 6.0
            this.setGiantScale(3.0F + this.rand.nextFloat() * 3.0F);

            this.health = this.getScaledMaxHealth();
        }

        protected void entityInit() {
            super.entityInit();
            this.dataWatcher.addObject(16, Integer.valueOf(30));
        }

        private int getScaleRaw() {
            return this.dataWatcher.getWatchableObjectInt(16);
        }

        public float getGiantScale() {
            return (float) this.getScaleRaw() / 10.0F;
        }

        public void setGiantScale(float scale) {
            if (scale < 3.0F) scale = 3.0F;
            if (scale > 8.0F) scale = 8.0F;

            int raw = Math.round(scale * 10.0F);
            this.dataWatcher.updateObject(16, Integer.valueOf(raw));

            this.applyScaleAttributes();
        }

        private void applyScaleAttributes() {
            float scale = this.getGiantScale();

            // Scale hitbox
            this.setSize(0.6F * scale, 1.8F * scale);

            // Scale health and damage
            this.health = this.getScaledMaxHealth();
            this.attackStrength = this.getScaledAttackStrength();
        }

        private int getScaledMaxHealth() {
            return MathHelper.floor_float(20.0F * this.getGiantScale());
        }

        private int getScaledAttackStrength() {
            return MathHelper.floor_float(4.0F * this.getGiantScale());
        }

        public void writeEntityToNBT(NBTTagCompound nbt) {
            super.writeEntityToNBT(nbt);
            nbt.setFloat("GiantScale", this.getGiantScale());
        }

        public void readEntityFromNBT(NBTTagCompound nbt) {
            super.readEntityFromNBT(nbt);

            if (nbt.hasKey("GiantScale")) {
                this.setGiantScale(nbt.getFloat("GiantScale"));
            } else {
                this.applyScaleAttributes();
            }
        }

        public int getMaxSpawnedInChunk() {
            return 2; // Prevent overpopulation
        }
    }

    */
