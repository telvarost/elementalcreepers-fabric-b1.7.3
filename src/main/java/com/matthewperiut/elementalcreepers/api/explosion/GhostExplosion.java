package com.matthewperiut.elementalcreepers.api.explosion;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GhostExplosion {
	private World worldObj;
	public double explosionX;
	public double explosionY;
	public double explosionZ;
	public Entity exploder;
	public float explosionSize;

	public GhostExplosion(World world, Entity entity, double d, double d1, double d2, float f) {
		this.worldObj = world;
		this.exploder = entity;
		this.explosionSize = f;
		this.explosionX = d;
		this.explosionY = d1;
		this.explosionZ = d2;
	}

	public void doExplosionA() {
		this.explosionSize *= 2.0F;
		int k = MathHelper.floor(this.explosionX - (double)this.explosionSize - 1.0D);
		int i1 = MathHelper.floor(this.explosionX + (double)this.explosionSize + 1.0D);
		int k1 = MathHelper.floor(this.explosionY - (double)this.explosionSize - 1.0D);
		int l1 = MathHelper.floor(this.explosionY + (double)this.explosionSize + 1.0D);
		int i2 = MathHelper.floor(this.explosionZ - (double)this.explosionSize - 1.0D);
		int j2 = MathHelper.floor(this.explosionZ + (double)this.explosionSize + 1.0D);
		List list = this.worldObj.getEntities(this.exploder, Box.create((double)k, (double)k1, (double)i2, (double)i1, (double)l1, (double)j2));
		Vec3d vec3d = Vec3d.createCached(this.explosionX, this.explosionY, this.explosionZ);

		for(int k2 = 0; k2 < list.size(); ++k2) {
			Entity entity = (Entity) list.get(k2);
			double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)this.explosionSize;
			if(d4 <= 1.0D) {
				double d6 = entity.x - this.explosionX;
				double d8 = entity.y - this.explosionY;
				double d10 = entity.z - this.explosionZ;
				double d11 = (double)MathHelper.sqrt(d6 * d6 + d8 * d8 + d10 * d10);
				d6 /= d11;
				d8 /= d11;
				d10 /= d11;
				double d12 = (double)this.worldObj.getVisibilityRatio(vec3d, entity.boundingBox);
				double d13 = (1.0D - d4) * d12;
				entity.damage(this.exploder, (int)((d13 * d13 + d13) / 2.0D * 8.0D * (double)this.explosionSize + 1.0D));
				entity.velocityX += d6 * d13;
				entity.velocityY += d8 * d13;
				entity.velocityZ += d10 * d13;
			}
		}

	}

	public void doExplosionB(boolean flag, Entity entity) {
		this.worldObj.playSound(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.random.nextFloat() - this.worldObj.random.nextFloat()) * 0.2F) * 0.7F);
		if(entity instanceof LivingEntity) {
			((LivingEntity)entity).animateSpawn();
		}

	}
}