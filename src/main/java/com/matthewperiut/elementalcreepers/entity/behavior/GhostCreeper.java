package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.api.explosion.GhostExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class GhostCreeper extends Creeper implements MobSpawnDataProvider, CreeperExplosion
{
    public GhostCreeper(Level arg)
    {
        super(arg);
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "GhostCreeper");
    }

    @Override
    public void detonate(Level level, EntityBase creeper, double x, double y, double z, float power)
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

    public GhostExplosion createGhostExplosion(EntityBase entity, double d, double d1, double d2, float f) {
        return this.newGhostExplosion(entity, d, d1, d2, f);
    }

    public GhostExplosion newGhostExplosion(EntityBase entity, double d, double d1, double d2, float f) {
        GhostExplosion explosion = new GhostExplosion(this.level, entity, d, d1, d2, f);
        explosion.doExplosionA();
        explosion.doExplosionB(true, entity);
        return explosion;
    }
}
