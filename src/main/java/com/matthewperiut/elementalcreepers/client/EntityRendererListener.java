package com.matthewperiut.elementalcreepers.client;

import com.matthewperiut.elementalcreepers.client.render.GhostCreeperRenderer;
import com.matthewperiut.elementalcreepers.entity.behavior.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;

public class EntityRendererListener
{
    @EventListener
    public void registerEntityRenderers(EntityRendererRegisterEvent event)
    {
        event.renderers.put(CookieCreeper.class, new CreeperEntityRenderer());
        event.renderers.put(EarthCreeper.class, new CreeperEntityRenderer());
        event.renderers.put(ElectricCreeper.class, new CreeperEntityRenderer());
        event.renderers.put(FireCreeper.class, new CreeperEntityRenderer());
        event.renderers.put(GhostCreeper.class, new GhostCreeperRenderer(new CreeperEntityModel()));
        event.renderers.put(IceCreeper.class, new CreeperEntityRenderer());
        event.renderers.put(MagmaCreeper.class, new CreeperEntityRenderer());
        event.renderers.put(PsychicCreeper.class, new CreeperEntityRenderer());
        event.renderers.put(WaterCreeper.class, new CreeperEntityRenderer());
    }
}