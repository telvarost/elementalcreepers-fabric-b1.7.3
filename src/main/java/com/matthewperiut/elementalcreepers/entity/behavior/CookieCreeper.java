package com.matthewperiut.elementalcreepers.entity.behavior;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import com.matthewperiut.elementalcreepers.entity.EntityListener;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

public class CookieCreeper extends Creeper implements MobSpawnDataProvider, CreeperExplosion
{
    public CookieCreeper(Level arg)
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
    public void detonate(Level level, EntityBase creeper, double x, double y, double z, float power)
    {
        int amount = this.isCharged() ? (int)((float) ElementalCreepersMod.config.cookieCreeperAmount * 1.5F) : ElementalCreepersMod.config.cookieCreeperAmount;

        for(int i = 0; i < amount; ++i)
        {
            float f1 = 0.7F;
            double d = (double) (this.level.rand.nextFloat() * f1) + (double) (1.0F + f1) * 0.5D;
            double d1 = (double) (this.level.rand.nextFloat() * f1) + (double) (1.0F + f1) * 0.5D;
            double d2 = (double) (this.level.rand.nextFloat() * f1) + (double) (1.0F + f1) * 0.5D;
            Item entityitem = new Item(level, x + d, y + d1, z + d2, new ItemInstance(ItemBase.cookie, 1));
            entityitem.pickupDelay = 10;
            level.spawnEntity(entityitem);
        }
    }
}
