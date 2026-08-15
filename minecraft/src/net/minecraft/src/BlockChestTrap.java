package net.minecraft.src;

import java.util.Random;

public class BlockChestTrap extends Block{
    protected BlockChestTrap(int var1) {
        super(var1, Material.wood);
        this.blockIndexInTexture = 26;
    }
    public int getBlockTextureFromSide(int var1) {
        return var1 == 1 ? this.blockIndexInTexture - 1 : (var1 == 0 ? this.blockIndexInTexture - 1 : (var1 == 3 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture));
    }
    public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5) {
        if (var5 == 1) {
            return this.blockIndexInTexture - 1;
        } else if (var5 == 0) {
            return this.blockIndexInTexture - 1;
        } else {
            int var6 = var1.getBlockId(var2, var3, var4 - 1);
            int var7 = var1.getBlockId(var2, var3, var4 + 1);
            int var8 = var1.getBlockId(var2 - 1, var3, var4);
            int var9 = var1.getBlockId(var2 + 1, var3, var4);
            int var10;
            int var11;
            int var12;
            byte var13;
            if (var6 != this.blockID && var7 != this.blockID) {
                if (var8 != this.blockID && var9 != this.blockID) {
                    byte var14 = 3;
                    if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var7]) {
                        var14 = 3;
                    }

                    if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var6]) {
                        var14 = 2;
                    }

                    if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var9]) {
                        var14 = 5;
                    }

                    if (Block.opaqueCubeLookup[var9] && !Block.opaqueCubeLookup[var8]) {
                        var14 = 4;
                    }

                    return var5 == var14 ? this.blockIndexInTexture + 1 : this.blockIndexInTexture;
                } else if (var5 != 4 && var5 != 5) {
                    var10 = 0;
                    if (var8 == this.blockID) {
                        var10 = -1;
                    }

                    var11 = var1.getBlockId(var8 == this.blockID ? var2 - 1 : var2 + 1, var3, var4 - 1);
                    var12 = var1.getBlockId(var8 == this.blockID ? var2 - 1 : var2 + 1, var3, var4 + 1);
                    if (var5 == 3) {
                        var10 = -1 - var10;
                    }

                    var13 = 3;
                    if ((Block.opaqueCubeLookup[var6] || Block.opaqueCubeLookup[var11]) && !Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var12]) {
                        var13 = 3;
                    }

                    if ((Block.opaqueCubeLookup[var7] || Block.opaqueCubeLookup[var12]) && !Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var11]) {
                        var13 = 2;
                    }

                    return (var5 == var13 ? this.blockIndexInTexture + 16 : this.blockIndexInTexture + 32) + var10;
                } else {
                    return this.blockIndexInTexture;
                }
            } else if (var5 != 2 && var5 != 3) {
                var10 = 0;
                if (var6 == this.blockID) {
                    var10 = -1;
                }

                var11 = var1.getBlockId(var2 - 1, var3, var6 == this.blockID ? var4 - 1 : var4 + 1);
                var12 = var1.getBlockId(var2 + 1, var3, var6 == this.blockID ? var4 - 1 : var4 + 1);
                if (var5 == 4) {
                    var10 = -1 - var10;
                }

                var13 = 5;
                if ((Block.opaqueCubeLookup[var8] || Block.opaqueCubeLookup[var11]) && !Block.opaqueCubeLookup[var9] && !Block.opaqueCubeLookup[var12]) {
                    var13 = 5;
                }

                if ((Block.opaqueCubeLookup[var9] || Block.opaqueCubeLookup[var12]) && !Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var11]) {
                    var13 = 4;
                }

                return (var5 == var13 ? this.blockIndexInTexture + 16 : this.blockIndexInTexture + 32) + var10;
            } else {
                return this.blockIndexInTexture;
            }
        }
    }


    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer var5) {

        if (world.multiplayerWorld) {
            return true;
        } else {

                System.out.println("null");


                    EntityMimic mimic = new EntityMimic(world);
                    double spawnX = x+0.5;
                    double spawnY = y+0.1;
                    double spawnZ = z+0.5;
                mimic.setLocationAndAngles(spawnX,spawnY,spawnZ,world.rand.nextFloat()*360.0F,0.0F);
                world.entityJoinedWorld(mimic);



                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "mob.mimic", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);

                Random var8 = new Random();
                for (int var9 = 0; var9 < 20; ++var9) {
                    double var10 = (double) x + var8.nextDouble();
                    double var12 = (double) y + var8.nextDouble();
                    double var14 = (double) z + var8.nextDouble();
                    world.spawnParticle("smoke", var10, var12, var14, 0.0D, 0.0D, 0.0D);
                }

                world.setBlockWithNotify(x,y,z, 0);

        }
        return true;
    }
}
