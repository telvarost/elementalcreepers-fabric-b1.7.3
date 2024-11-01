package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class CookieCreeper extends CreeperEntity implements MobSpawnDataProvider, CreeperExplosion
{
    public CookieCreeper(World arg)
    {
        super(arg);
        this.texture = "elementalcreepers:textures/cookiecreeper.png";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return Identifier.of(EntityListener.MOD_ID, "CookieCreeper");
    }

    @Override
    public void detonate(World level, Entity creeper, double x, double y, double z, float power)
    {
        int amount = this.isCharged() ? (int)((float) ElementalCreepersMod.config.cookieCreeperAmount * 1.5F) : ElementalCreepersMod.config.cookieCreeperAmount;

        for(int i = 0; i < amount; ++i)
        {
            float f1 = 0.7F;
            double d = (double) (this.world.random.nextFloat() * f1) + (double) (1.0F + f1) * 0.5D;
            double d1 = (double) (this.world.random.nextFloat() * f1) + (double) (1.0F + f1) * 0.5D;
            double d2 = (double) (this.world.random.nextFloat() * f1) + (double) (1.0F + f1) * 0.5D;
            ItemEntity entityitem = new ItemEntity(level, x + d, y + d1, z + d2, new ItemStack(Item.COOKIE, 1));
            entityitem.pickupDelay = 10;
            level.spawnEntity(entityitem);
        }
    }
}
