package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

public class IceCreeper extends Creeper implements MobSpawnDataProvider, CreeperExplosion
{
    public IceCreeper(Level arg)
    {
        super(arg);
        texture = "elementalcreepers:textures/icecreeper.png";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "IceCreeper");
    }

    @Override
    public void detonate(Level level, EntityBase creeper, double posX, double posY, double posZ, float power)
    {
        int radius = isCharged() ? (int)((float)ElementalCreepersMod.config.iceCreeperRadius * 1.5F) : ElementalCreepersMod.config.iceCreeperRadius;
        int x = -radius;

        while(true) {
            if(x > radius) {
                level.playSound(posX, posY, posZ, "random.explode", 4.0F, (1.0F + (level.rand.nextFloat() - level.rand.nextFloat()) * 0.2F) * 0.7F);
                onSpawnedFromSpawner();
                return;
            }

            for(int y = -radius; y <= radius; ++y) {
                for(int z = -radius; z <= radius; ++z) {
                    if(level.getMaterial((int)posX + x, (int)posY + y, (int)posZ + z) == Material.WATER && level.getTileMeta((int)posX + x, (int)posY + y, (int)posZ + z) == 0) {
                        level.setBlockStateWithNotify((int)posX + x, (int)posY + y, (int)posZ + z, BlockBase.ICE.getDefaultState());
                    } else if(level.getMaterial((int)posX + x, (int)posY + y, (int)posZ + z) == Material.LAVA && level.getTileMeta((int)posX + x, (int)posY + y, (int)posZ + z) == 0) {
                        level.setBlockStateWithNotify((int)posX + x, (int)posY + y, (int)posZ + z, BlockBase.OBSIDIAN.getDefaultState());
                    } else if(level.canPlaceTile(BlockBase.SNOW.id, (int)posX + x, (int)posY + y, (int)posZ + z, false, 0) && !level.canPlaceTile(BlockBase.SNOW.id, (int)posX + x, (int)posY + y - 1, (int)posZ + z, false, 0)) {
                        level.setBlockStateWithNotify((int)posX + x, (int)posY + y, (int)posZ + z, BlockBase.SNOW.getDefaultState());
                    }
                }
            }

            ++x;
        }
    }
}
