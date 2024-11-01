package com.matthewperiut.elementalcreepers.api.explosion;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PsychicExplosion
{
    public boolean isFlaming = false;
    private World level;
    public double x;
    public double y;
    public double z;
    public Entity exploder;
    public float explosionSize;

    public PsychicExplosion(World var1, Entity var2, double var3, double var5, double var7, float var9) {
        this.level = var1;
        this.exploder = var2;
        this.explosionSize = var9;
        this.x = var3;
        this.y = var5;
        this.z = var7;
    }

    public void doExplosionA() {
        this.explosionSize *= 2.0F;
        int var3 = MathHelper.floor(this.x - (double)this.explosionSize - 1.0D);
        int var4 = MathHelper.floor(this.x + (double)this.explosionSize + 1.0D);
        int var5 = MathHelper.floor(this.y - (double)this.explosionSize - 1.0D);
        int var29 = MathHelper.floor(this.y + (double)this.explosionSize + 1.0D);
        int var7 = MathHelper.floor(this.z - (double)this.explosionSize - 1.0D);
        int var30 = MathHelper.floor(this.z + (double)this.explosionSize + 1.0D);
        List var9 = this.level.getEntities(this.exploder, Box.create((double)var3, (double)var5, (double)var7, (double)var4, (double)var29, (double)var30));
        Vec3d var31 = Vec3d.createCached(this.x, this.y, this.z);

        for(int var11 = 0; var11 < var9.size(); ++var11) {
            Entity var32 = (Entity)var9.get(var11);
            double var13 = var32.getDistance(this.x, this.y, this.z) / (double)this.explosionSize;
            if(var13 <= 1.0D) {
                double var15 = var32.x - this.x;
                double var17 = var32.y - this.y;
                double var19 = var32.z - this.z;
                double var40 = (double)MathHelper.sqrt(var15 * var15 + var17 * var17 + var19 * var19);
                var15 /= var40;
                double var10000 = var17 / var40;
                var19 /= var40;
                double var39 = (double)this.level.getVisibilityRatio(var31, var32.boundingBox);
                double var41 = (1.0D - var13) * var39;
                var32.velocityX += var15 * var41 * 2.0D;
                var32.velocityY += 1.5D - Math.sqrt(Math.pow(var15, 2.0D) + Math.pow(var19, 2.0D)) * 0.75D;
                var32.velocityZ += var19 * var41 * 2.0D;
            }
        }

    }
    public void doExplosionB(boolean bl) {
        this.level.playSound(this.x, this.y, this.z, "random.explode", 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F);
        ArrayList var2 = new ArrayList();

        for(int var3 = var2.size() - 1; var3 >= 0; --var3) {
            BlockPos var4 = (BlockPos)var2.get(var3);
            int var5 = var4.x;
            int var6 = var4.y;
            int var7 = var4.z;
            int var8 = this.level.getBlockId(var5, var6, var7);
            if (bl) {
                double var9 = (double)((float)var5 + this.level.random.nextFloat());
                double var11 = (double)((float)var6 + this.level.random.nextFloat());
                double var13 = (double)((float)var7 + this.level.random.nextFloat());
                double var15 = var9 - this.x;
                double var17 = var11 - this.y;
                double var19 = var13 - this.z;
                double var21 = (double)MathHelper.sqrt(var15 * var15 + var17 * var17 + var19 * var19);
                var15 /= var21;
                var17 /= var21;
                var19 /= var21;
                double var23 = 0.5 / (var21 / (double)this.explosionSize + 0.1);
                var23 *= (double)(this.level.random.nextFloat() * this.level.random.nextFloat() + 0.3F);
                var15 *= var23;
                var17 *= var23;
                var19 *= var23;
                this.level.addParticle("explode", (var9 + this.x * 1.0) / 2.0, (var11 + this.y * 1.0) / 2.0, (var13 + this.z * 1.0) / 2.0, var15, var17, var19);
                this.level.addParticle("smoke", var9, var11, var13, var15, var17, var19);
            }

            if (var8 > 0) {
                Block.BLOCKS[var8].dropStacks(this.level, var5, var6, var7, this.level.getBlockMeta(var5, var6, var7), 0.3F);
                this.level.setBlock(var5, var6, var7, 0);
                Block.BLOCKS[var8].onDestroyedByExplosion(this.level, var5, var6, var7);
            }
        }

    }
}
