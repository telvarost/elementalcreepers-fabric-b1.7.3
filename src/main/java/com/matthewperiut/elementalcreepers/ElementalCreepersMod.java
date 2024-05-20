package com.matthewperiut.elementalcreepers;

import com.matthewperiut.elementalcreepers.config.ElementalCreepersConfigFields;
import com.matthewperiut.elementalcreepers.optional.SPCSupport;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;

public class ElementalCreepersMod
{

    @GConfig(value = "config", visibleName = "Elemental Creeper Config")
    public static ElementalCreepersConfigFields config = new ElementalCreepersConfigFields();

    @EventListener
    public void init(InitEvent event)
    {
        if (FabricLoader.getInstance().isModLoaded("spc"))
        {
            SPCSupport.addEntities();
        }
    }
}
