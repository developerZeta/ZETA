package net.minecraft.src;

import java.util.Random;

public class WorldGenEmeraldOre extends WorldGenerator {
    public WorldGenEmeraldOre() {
    }

    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        int var6 = var2.nextInt(4) + 4;
        float var7 = var2.nextFloat() * (float)Math.PI;
        double var8 = (double)((float)(var3 + 8) + MathHelper.sin(var7) * (float)var6 / 8.0F);
        double var10 = (double)((float)(var3 + 8) - MathHelper.sin(var7) * (float)var6 / 8.0F);
        double var12 = (double)((float)(var5 + 8) + MathHelper.cos(var7) * (float)var6 / 8.0F);
        double var14 = (double)((float)(var5 + 8) - MathHelper.cos(var7) * (float)var6 / 8.0F);
        double var16 = (double)(var4 + var2.nextInt(3) - 2);
        double var18 = (double)(var4 + var2.nextInt(3) - 2);

        for(int var20 = 0; var20 <= var6; ++var20) {
            double var21 = var8 + (var10 - var8) * (double)var20 / (double)var6;
            double var23 = var16 + (var18 - var16) * (double)var20 / (double)var6;
            double var25 = var12 + (var14 - var12) * (double)var20 / (double)var6;
            double var27 = var2.nextFloat() * (float)var6 / 4.0F;

            for(int var29 = 0; var29 <= 1; ++var29) {
                for(int var30 = 0; var30 <= 1; ++var30) {
                    for(int var31 = 0; var31 <= 1; ++var31) {
                        int var32 = MathHelper.floor_double(var21 + (double)var29);
                        int var33 = MathHelper.floor_double(var23 + (double)var30);
                        int var34 = MathHelper.floor_double(var25 + (double)var31);
                        if(var29 == 0 || var30 == 0 || var31 == 0) {
                            double var35 = (double)var32 + 0.5D - var21;
                            double var37 = (double)var33 + 0.5D - var23;
                            double var39 = (double)var34 + 0.5D - var25;
                            if(var35 * var35 + var37 * var37 + var39 * var39 < var27 * var27) {
                                if(var1.getBlockId(var32, var33, var34) == Block.netherrack.blockID) {
                                    var1.setBlockAndMetadata(var32, var33, var34, Block.emeraldOre.blockID, 0);
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
