package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Lightning;
import net.minecraft.entity.Living;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Box;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;
import java.util.Random;

public class ElectricCreeper extends Creeper implements MobSpawnDataProvider, CreeperExplosion
{
    public ElectricCreeper(Level arg)
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
    public void detonate(Level level, EntityBase creeper, double posX, double posY, double posZ, float power)
    {
        int radius = isCharged() ? (int)((float)ElementalCreepersMod.config.electricCreeperRadius * 1.5F) : ElementalCreepersMod.config.electricCreeperRadius;
        List list = level.getEntities(Living.class, Box.create(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand((double)radius, (double)radius, (double)radius));

        for(int j = 0; j < list.size(); ++j) {
            Living entityliving = (Living)list.get(j);
            if(entityliving != null && !(entityliving instanceof ElectricCreeper)) {
                level.spawnEntity(new Lightning(level, entityliving.x, entityliving.y, entityliving.z));
            }
        }

        level.playSound(posX, posY, posZ, "random.explode", 4.0F, (1.0F + (level.rand.nextFloat() - level.rand.nextFloat()) * 0.2F) * 0.7F);
        onSpawnedFromSpawner();
    }
}
