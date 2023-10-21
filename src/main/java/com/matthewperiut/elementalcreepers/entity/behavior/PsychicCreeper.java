package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.api.explosion.PsychicExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class PsychicCreeper extends Creeper implements MobSpawnDataProvider, CreeperExplosion
{
    public PsychicCreeper(Level arg)
    {
        super(arg);
        texture = "elementalcreepers:textures/psychiccreeper.png";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "PsychicCreeper");
    }

    @Override
    public void detonate(Level level, EntityBase creeper, double posX, double posY, double posZ, float power)
    {
        float p = isCharged() ? (float)ElementalCreepersMod.config.psychicCreeperPower * 1.5F : (float)ElementalCreepersMod.config.psychicCreeperPower;
        createPsychicCreeperLauncher(this, posX, posY, posZ, p);
    }

    public PsychicExplosion createPsychicCreeperLauncher(EntityBase entity, double d, double d1, double d2, float f) {
        return this.newPsychicCreeperLauncher(entity, d, d1, d2, f, false);
    }

    public PsychicExplosion newPsychicCreeperLauncher(EntityBase entity, double d, double d1, double d2, float f, boolean flag) {
        PsychicExplosion explosion = new PsychicExplosion(level, entity, d, d1, d2, f);
        explosion.doExplosionA();
        explosion.doExplosionB(true);
        return explosion;
    }
}
