package net.minecraft.src;

import java.util.Random;

public class EntityHellCreeper extends EntityCreeper {
	public EntityHellCreeper(World var1) {
		super(var1);
		this.health = 24;
		this.moveSpeed = 0.55F;
		this.isImmuneToFire = true;
		this.texture = "/mod_mob/hell_creeper.png";
	}

	protected void attackEntity(Entity var1, float var2) {
		if(!this.worldObj.multiplayerWorld) {
			int var3 = this.dataWatcher.getWatchableObjectByte(16);
			if(var3 <= 0 && var2 < 3.0F || var3 > 0 && var2 < 7.0F) {
				if(this.timeSinceIgnited == 0) {
					this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 0.5F);
				}

				this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
				++this.timeSinceIgnited;
				if(this.timeSinceIgnited >= 30) {
					// Hell Creeper explosion is 2x normal creeper (6.0F instead of 3.0F)
					this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 6.0F);
					this.setEntityDead();
				}

				this.hasAttacked = true;
			} else {
				this.dataWatcher.updateObject(16, Byte.valueOf((byte)-1));
				--this.timeSinceIgnited;
				if(this.timeSinceIgnited < 0) {
					this.timeSinceIgnited = 0;
				}
			}

		}
	}
	protected int getDropItemId() {
		return Item.feather.shiftedIndex;
	}

	protected void dropFewItems() {
		if(this.rand.nextBoolean()) {
			this.dropItem(Item.feather.shiftedIndex, 2);
		} else {
			this.dropItem(Item.redstone.shiftedIndex, 1);
		}
	}

	public boolean getCanSpawnHere() {
		return this.worldObj.worldProvider.isNether && super.getCanSpawnHere();
	}
}
