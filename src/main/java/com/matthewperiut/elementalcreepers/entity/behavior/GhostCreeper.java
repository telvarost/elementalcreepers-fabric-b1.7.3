package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.api.explosion.GhostExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.modificationstation.stationapi.api.util.Identifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class GhostCreeper extends CreeperEntity implements MobSpawnDataProvider, CreeperExplosion
{
    public GhostCreeper(World arg)
    {
        super(arg);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "GhostCreeper");
    }

    @Override
    public void detonate(World level, Entity creeper, double x, double y, double z, float power)
    {
        int strength;
        if(this.isCharged()) {
            strength = (int)((float)ElementalCreepersMod.config.ghostCreeperRadius * 1.5F);
        } else {
            strength = ElementalCreepersMod.config.ghostCreeperRadius;
        }

        // yes this is cursed, yes this is how original v1.4.0 worked
        this.createGhostExplosion(this, this.x, this.y, this.z, 2.0F);
    }

    public GhostExplosion createGhostExplosion(Entity entity, double d, double d1, double d2, float f) {
        return this.newGhostExplosion(entity, d, d1, d2, f);
    }

    public GhostExplosion newGhostExplosion(Entity entity, double d, double d1, double d2, float f) {
        GhostExplosion explosion = new GhostExplosion(this.world, entity, d, d1, d2, f);
        explosion.doExplosionA();
        explosion.doExplosionB(true, entity);
        return explosion;
    }
}
