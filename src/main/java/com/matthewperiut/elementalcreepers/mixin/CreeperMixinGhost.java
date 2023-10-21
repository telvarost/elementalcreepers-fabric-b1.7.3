package com.matthewperiut.elementalcreepers.mixin;

import com.matthewperiut.elementalcreepers.ElementalCreepersMod;
import com.matthewperiut.elementalcreepers.entity.behavior.GhostCreeper;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Creeper.class)
public class CreeperMixinGhost
{
    @Inject(method = "onKilledBy", at = @At("HEAD"))
    void onKilledBy(EntityBase killer, CallbackInfo ci)
    {
        if((new Random()).nextInt(100) < ElementalCreepersMod.config.ghostCreeperChance) {
            Creeper dead = (Creeper) (Object) this;
            if (dead instanceof GhostCreeper)
                return;
            GhostCreeper entityghostcreeper = new GhostCreeper(dead.level);
            entityghostcreeper.setPosition(dead.x, dead.y, dead.z);
            dead.level.spawnEntity(entityghostcreeper);
        }
    }
}
