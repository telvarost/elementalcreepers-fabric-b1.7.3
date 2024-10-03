package com.matthewperiut.elementalcreepers.entity;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.entity.behavior.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.EntityEntry;
import net.minecraft.level.biome.Biome;
import net.modificationstation.stationapi.api.event.worldgen.biome.BiomeModificationEvent;


public class BiomeModificationListener {
    @EventListener
    public void event(BiomeModificationEvent event) {
        if (true == ElementalCreepersMod.config.biomeSpecific) {
            Biome.SEASONAL_FOREST.addHostileEntity(PsychicCreeper.class, ElementalCreepersMod.config.psychicCreeperSpawn);
            Biome.FOREST.addHostileEntity(PsychicCreeper.class, ElementalCreepersMod.config.psychicCreeperSpawn);
            Biome.SWAMPLAND.addHostileEntity(WaterCreeper.class, ElementalCreepersMod.config.waterCreeperSpawn);
            Biome.RAINFOREST.addHostileEntity(WaterCreeper.class, ElementalCreepersMod.config.waterCreeperSpawn);
            Biome.RAINFOREST.addHostileEntity(ElectricCreeper.class, ElementalCreepersMod.config.electricCreeperSpawn);
            Biome.SHRUBLAND.addHostileEntity(ElectricCreeper.class, ElementalCreepersMod.config.electricCreeperSpawn);
            Biome.SKY.addHostileEntity(ElectricCreeper.class, ElementalCreepersMod.config.electricCreeperSpawn);
            Biome.SAVANNA.addHostileEntity(CookieCreeper.class, ElementalCreepersMod.config.cookieCreeperSpawn);
            Biome.PLAINS.addHostileEntity(EarthCreeper.class, ElementalCreepersMod.config.earthCreeperSpawn);
            Biome.TAIGA.addHostileEntity(IceCreeper.class, ElementalCreepersMod.config.iceCreeperSpawn);
            Biome.TUNDRA.addHostileEntity(IceCreeper.class, ElementalCreepersMod.config.iceCreeperSpawn);
            Biome.ICE_DESERT.addHostileEntity(IceCreeper.class, ElementalCreepersMod.config.iceCreeperSpawn);
            Biome.DESERT.addHostileEntity(FireCreeper.class, ElementalCreepersMod.config.fireCreeperSpawn);
            Biome.NETHER.addHostileEntity(FireCreeper.class, ElementalCreepersMod.config.fireCreeperSpawn);
            Biome.NETHER.addHostileEntity(MagmaCreeper.class, ElementalCreepersMod.config.magmaCreeperSpawn);
        }
    }
}