package com.matthewperiut.elementalcreepers.api;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.level.Level;

public interface CreeperExplosion
{
    default void detonate(Level level, EntityBase creeper, double x, double y, double z, float power)
    {
        if (((Creeper) creeper).isCharged()) {
            level.createExplosion(creeper, x, y, z, 6.0F);
        } else {
            level.createExplosion(creeper, x, y, z, 3.0F);
        }
    }
}
