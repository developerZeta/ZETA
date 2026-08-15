package net.minecraft.src;

public class EntityAngel extends EntitySpecter{
    @Override
    protected boolean is_inmunne_to_light(){
        return true;
    }
    @Override
    public boolean getCanSpawnHere() {
      return true;
    }
    private static final ItemStack defaultHeldItem = new ItemStack(Item.swordDiamond, 1);

    public EntityAngel(World var1){
        super(var1);
        this.health=30;
        this.texture="/mod_mob/angel.png";
        this.isImmuneToFire = true;
        this.moveSpeed=0.10F;
        this.noClip=false;
    }

    public void onUpdate() {
        super.onUpdate();
        this.noClip = false;
        if(!this.worldObj.multiplayerWorld && this.worldObj.difficultySetting == 0) {
            this.setEntityDead();
        }
    }

    protected int getDropItemId() {
        return Item.topaz.shiftedIndex;
    }

    protected String getHurtSound() {
        return "mob.angel.hurt_angel";
    }

    protected String getDeathSound() {
        return "mob.angel.dying_angel";
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }
}
