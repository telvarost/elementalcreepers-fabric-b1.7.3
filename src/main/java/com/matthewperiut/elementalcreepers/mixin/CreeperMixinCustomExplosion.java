package com.matthewperiut.elementalcreepers.mixin;

import com.matthewperiut.elementalcreepers.api.CreeperExplosion;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.Creeper;
import net.minecraft.level.Level;
import net.minecraft.sortme.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Creeper.class)
public class CreeperMixinCustomExplosion implements CreeperExplosion
{
    @Redirect(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/level/Level;createExplosion(Lnet/minecraft/entity/EntityBase;DDDF)Lnet/minecraft/sortme/Explosion;"))
    Explosion explode(Level level, EntityBase creeper, double x, double y, double z, float power)
    {
        detonate(level, creeper, x, y, z, power);
        return null;
    }
}
