package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.modificationstation.stationapi.api.util.Identifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class ElectricCreeper extends CreeperEntity implements MobSpawnDataProvider, CreeperExplosion
{
    public ElectricCreeper(World arg)
    {
        super(arg);
        texture = "elementalcreepers:textures/electriccreeper.png";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "ElectricCreeper");
    }

    @Override
    public void detonate(World level, Entity creeper, double posX, double posY, double posZ, float power)
    {
        int radius = isCharged() ? (int)((float)ElementalCreepersMod.config.electricCreeperRadius * 1.5F) : ElementalCreepersMod.config.electricCreeperRadius;
        List list = level.collectEntitiesByClass(LivingEntity.class, Box.create(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand((double)radius, (double)radius, (double)radius));

        for(int j = 0; j < list.size(); ++j) {
            LivingEntity entityliving = (LivingEntity)list.get(j);
            if(entityliving != null && !(entityliving instanceof ElectricCreeper)) {
                level.spawnEntity(new LightningEntity(level, entityliving.x, entityliving.y, entityliving.z));
            }
        }

        level.playSound(posX, posY, posZ, "random.explode", 4.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
        animateSpawn();
    }
}
