package net.minecraft.src;

public class EntityPigHuman extends EntityWolf{
    public EntityPigHuman(World var1){
        super(var1);
        this.texture = "/mod_mob/pig_humanoid.png";
        this.setSize(1.0F,2.0F);
        this.moveSpeed = 0.5F;
    }
    @Override
    public String getEntityTexture() {
        return "/mod_mob/pig_humanoid.png";
    }

    protected String getLivingSound() {
        return "mob.pig";
    }

    protected String getHurtSound() {
        return "mob.pig";
    }

    protected String getDeathSound() {
        return "mob.pigdeath";
    }

    protected int getDropItemId() {
        return this.fire > 0 ? Item.porkCooked.shiftedIndex : Item.porkRaw.shiftedIndex;
    }


}
