package com.matthewperiut.elementalcreepers.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public interface CreeperExplosion
{
    default void detonate(World level, Entity creeper, double x, double y, double z, float power)
    {
        if (((CreeperEntity) creeper).isCharged()) {
            level.createExplosion(creeper, x, y, z, 6.0F);
        } else {
            level.createExplosion(creeper, x, y, z, 3.0F);
        }
    }
}
