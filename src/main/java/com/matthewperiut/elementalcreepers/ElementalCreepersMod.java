package com.matthewperiut.elementalcreepers;

import com.matthewperiut.elementalcreepers.config.ElementalCreepersConfigFields;
import com.matthewperiut.elementalcreepers.optional.SPCSupport;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class ElementalCreepersMod
{

    @Entrypoint.Logger
    public static final Logger LOGGER = Null.get();

    @Entrypoint.Instance
    public static final ElementalCreepersMod INSTANCE = Null.get();

    @Entrypoint.ModID
    public static final ModID MODID = Null.get();

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
