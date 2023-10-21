package com.matthewperiut.elementalcreepers.client;

import com.matthewperiut.elementalcreepers.client.render.GhostCreeperRenderer;
import com.matthewperiut.elementalcreepers.entity.behavior.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.render.entity.CreeperRenderer;
import net.minecraft.client.render.entity.model.Creeper;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;

public class EntityRendererListener
{
    @EventListener
    public void registerEntityRenderers(EntityRendererRegisterEvent event)
    {
        event.renderers.put(CookieCreeper.class, new CreeperRenderer());
        event.renderers.put(EarthCreeper.class, new CreeperRenderer());
        event.renderers.put(ElectricCreeper.class, new CreeperRenderer());
        event.renderers.put(FireCreeper.class, new CreeperRenderer());
        event.renderers.put(GhostCreeper.class, new GhostCreeperRenderer(new Creeper()));
        event.renderers.put(IceCreeper.class, new CreeperRenderer());
        event.renderers.put(MagmaCreeper.class, new CreeperRenderer());
        event.renderers.put(PsychicCreeper.class, new CreeperRenderer());
        event.renderers.put(WaterCreeper.class, new CreeperRenderer());
    }
}