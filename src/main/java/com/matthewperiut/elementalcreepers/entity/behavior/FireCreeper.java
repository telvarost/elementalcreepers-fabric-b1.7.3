package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.Random;

public class FireCreeper extends Creeper implements MobSpawnDataProvider, CreeperExplosion
{
    public FireCreeper(Level arg)
    {
        super(arg);
        texture = "elementalcreepers:textures/firecreeper.png";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "FireCreeper");
    }

    @Override
    public void detonate(Level level, EntityBase creeper, double posX, double posY, double posZ, float power)
    {
        int radius = isCharged() ? (int)((float)ElementalCreepersMod.config.fireCreeperRadius * 1.5F) : ElementalCreepersMod.config.fireCreeperRadius;
        int x = -radius;

        while(true) {
            if(x > radius) {
                level.playSound(posX, posY, posZ, "random.explode", 4.0F, (1.0F + (level.rand.nextFloat() - level.rand.nextFloat()) * 0.2F) * 0.7F);
                onSpawnedFromSpawner();
                break;
            }

            for(int y = -radius; y <= radius; ++y) {
                for(int z = -radius; z <= radius; ++z) {
                    if(level.canPlaceTile(BlockBase.STILL_WATER.id, (int)posX + x, (int)posY + y, (int)posZ + z, false, 0) && !level.canPlaceTile(BlockBase.STILL_WATER.id, (int)posX + x, (int)posY + y - 1, (int)posZ + z, false, 0)) {
                        Random rand = new Random();
                        if(rand.nextBoolean()) {
                            level.setBlockStateWithNotify((int)posX + x, (int)posY + y, (int)posZ + z, BlockBase.FIRE.getDefaultState());
                        }
                    }
                }
            }

            ++x;
        }
    }
}
