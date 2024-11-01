package com.matthewperiut.elementalcreepers.mixin;

import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreeperEntity.class)
public class CreeperMixinCustomExplosion implements CreeperExplosion
{
    @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDF)Lnet/minecraft/world/explosion/Explosion;"))
    Explosion explode(World level, Entity creeper, double x, double y, double z, float power)
    {
        detonate(level, creeper, x, y, z, power);
        return null;
    }
}
