package net.minecraft.src;

public class ItemTungstenWarhammer extends ItemTool{

    private int weaponDamage;
    private static Block[] blocksEffectiveAgainst = new Block[]{Block.cobblestone, Block.stairDouble, Block.stairSingle, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.blockSulfur, Block.oreLapis, Block.blockLapis,Block.blockTopaz,Block.netherIron,Block.oreNetherCoal,Block.blockSteel,Block.oreGarnet,Block.flintBricks,Block.brick,Block.emeraldOre,Block.oreRuby,Block.oreTopaz,Block.ironBars,Block.oreTitanium,Block.blockGarnet,Block.cage,Block.pillar,Block.boneBricks,Block.stoneTile,Block.obsidian};

    public ItemTungstenWarhammer(int var1, EnumToolMaterial var2){
        super(var1,10,var2,blocksEffectiveAgainst);
        this.weaponDamage = 4 + var2.getDamageVsEntity() * 2;
    }
    public boolean canHarvestBlock(Block var1) {
        return var1 == Block.obsidian ? true : (var1 != Block.blockDiamond && var1 != Block.oreDiamond ? (var1 != Block.blockGold && var1 != Block.oreGold ? (var1 != Block.blockIron && var1 != Block.oreIron ? (var1 != Block.blockLapis && var1 != Block.oreLapis ? (var1 != Block.oreRedstone && var1 != Block.oreRedstoneGlowing ? (var1.blockMaterial == Material.rock ? true : var1.blockMaterial == Material.iron) : EnumToolMaterial.IRIDIUM.getHarvestLevel() >= 2) : EnumToolMaterial.IRIDIUM.getHarvestLevel() >= 1) : EnumToolMaterial.IRIDIUM.getHarvestLevel() >= 1) : EnumToolMaterial.IRIDIUM.getHarvestLevel() >= 2) : EnumToolMaterial.IRIDIUM.getHarvestLevel() >= 2);
    }
    public int getDamageVsEntity(Entity var1) {
        return this.weaponDamage;
    }

    public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2) {
        if(var2 instanceof Entity) {
            if(!var2.worldObj.multiplayerWorld) {
                var2.worldObj.createExplosion(var2, var2.posX, var2.posY+1, var2.posZ, 1.0F);
                var2.damageEntity(weaponDamage-10);
                var2.onEntityUpdate();
                var2.getHurtSound();
                var2.dealFireDamage(1);
                var1.damageItem(1, var2);
            }
            return true;
        }
        return false;
    }

    public void saddleEntity(ItemStack var1, EntityLiving var2) {
        this.itemInteractionForEntity(var1, var2);
    }

        public boolean isFull3D() {return true;}

}
