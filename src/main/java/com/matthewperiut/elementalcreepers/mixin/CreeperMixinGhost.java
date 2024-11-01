package com.matthewperiut.elementalcreepers.mixin;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.entity.behavior.GhostCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;

@Mixin(CreeperEntity.class)
public class CreeperMixinGhost
{
    @Inject(method = "onKilledBy", at = @At("HEAD"))
    void onKilledBy(Entity killer, CallbackInfo ci)
    {
        if((new Random()).nextInt(100) < ElementalCreepersMod.config.ghostCreeperChance) {
            CreeperEntity dead = (CreeperEntity) (Object) this;
            if (dead instanceof GhostCreeper)
                return;
            GhostCreeper entityghostcreeper = new GhostCreeper(dead.world);
            entityghostcreeper.setPosition(dead.x, dead.y, dead.z);
            dead.world.spawnEntity(entityghostcreeper);
        }
    }
}
