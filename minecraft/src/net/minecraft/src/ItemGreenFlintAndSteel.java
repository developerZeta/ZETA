package net.minecraft.src;

public class ItemGreenFlintAndSteel extends Item {
	public ItemGreenFlintAndSteel(int var1) {
		super(var1);
		this.maxStackSize = 1;
		this.setMaxDamage(64);
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
		// Place green fire on blockSulfur (like netherrack)
		if(var8 == 0 && (var9 == Block.netherrack.blockID || var9 == Block.blockSulfur.blockID)) {
			var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			var3.setBlockWithNotify(var4, var5, var6, Block.greenFire.blockID);
		} else if(var8 == 0) {
			var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			var3.setBlockWithNotify(var4, var5, var6, Block.greenFire.blockID);
		} else if(var8 == Block.torchUnlit.blockID) {
			var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			int var10 = var3.getBlockMetadata(var4, var5, var6);
			var3.setBlockAndMetadataWithNotify(var4, var5, var6, Block.foreverTorch.blockID, var10);
		} else {
			return false;
		}

		var1.damageItem(1, var2);
		return true;
	}

	public int getColorFromDamage(int var1) {
		return 0x00FF00;
	}
}
