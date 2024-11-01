package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.api.explosion.PsychicExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.modificationstation.stationapi.api.util.Identifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class PsychicCreeper extends CreeperEntity implements MobSpawnDataProvider, CreeperExplosion
{
    public PsychicCreeper(World arg)
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
    public void detonate(World level, Entity creeper, double posX, double posY, double posZ, float power)
    {
        float p = isCharged() ? (float)ElementalCreepersMod.config.psychicCreeperPower * 1.5F : (float)ElementalCreepersMod.config.psychicCreeperPower;
        createPsychicCreeperLauncher(this, posX, posY, posZ, p);
    }

    public PsychicExplosion createPsychicCreeperLauncher(Entity entity, double d, double d1, double d2, float f) {
        return this.newPsychicCreeperLauncher(entity, d, d1, d2, f, false);
    }

    public PsychicExplosion newPsychicCreeperLauncher(Entity entity, double d, double d1, double d2, float f, boolean flag) {
        PsychicExplosion explosion = new PsychicExplosion(world, entity, d, d1, d2, f);
        explosion.doExplosionA();
        explosion.doExplosionB(true);
        return explosion;
    }
}
