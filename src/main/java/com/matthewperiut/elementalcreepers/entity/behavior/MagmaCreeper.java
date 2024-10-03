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

public class MagmaCreeper extends Creeper implements MobSpawnDataProvider, CreeperExplosion
{
    public MagmaCreeper(Level arg)
    {
        super(arg);
        texture = "elementalcreepers:textures/magmacreeper.png";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "MagmaCreeper");
    }

    @Override
    public void detonate(Level level, EntityBase creeper, double posX, double posY, double posZ, float power)
    {
        int radius = isCharged() ? (int)((double)ElementalCreepersMod.config.magmaCreeperRadius * 1.5D) : ElementalCreepersMod.config.magmaCreeperRadius;
        int x = -radius;

        while(true) {
            if(x > radius) {
                level.playSound(posX, posY, posZ, "random.explode", 4.0F, (1.0F + (level.rand.nextFloat() - level.rand.nextFloat()) * 0.2F) * 0.7F);
                onSpawnedFromSpawner();
                break;
            }

            for(int y = -radius; y <= radius; ++y) {
                for(int z = -radius; z <= radius; ++z) {
                    if(level.canPlaceTile(BlockBase.STILL_LAVA.id, (int)posX + x, (int)posY + y, (int)posZ + z, false, 0) && !level.canPlaceTile(BlockBase.STILL_LAVA.id, (int)posX + x, (int)posY + y - 1, (int)posZ + z, false, 0)) {
                        level.setBlockStateWithNotify((int)posX + x, (int)posY + y, (int)posZ + z, BlockBase.STILL_LAVA.getDefaultState());
                    }
                }
            }

            ++x;
        }
    }
}
