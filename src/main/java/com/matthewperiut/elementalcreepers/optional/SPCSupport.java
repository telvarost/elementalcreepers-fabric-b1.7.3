package com.matthewperiut.elementalcreepers.optional;

import com.matthewperiut.elementalcreepers.entity.behavior.*;
import com.matthewperiut.spc.api.PosParse;
import com.matthewperiut.spc.api.SummonRegistry;
import com.matthewperiut.spc.mixin.EntityAccessor;
import net.minecraft.entity.monster.Creeper;

public class SPCSupport
{
    private static Creeper handleCharge(Creeper creeper, PosParse pos, String[] param)
    {
        if (param.length > 5)
            if (!param[5].isEmpty())
                if (param[5].charAt(0) != '0')
                    ((EntityAccessor) creeper).getDataTracker().setInt(17, (byte) 1);

        creeper.setPosition(pos.x, pos.y, pos.z);

        return creeper;
    }

    public static void addEntities()
    {
        SummonRegistry.add(CookieCreeper.class, (level, pos, param) -> handleCharge(new CookieCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(EarthCreeper.class, (level, pos, param) -> handleCharge(new EarthCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(ElectricCreeper.class, (level, pos, param) -> handleCharge(new ElectricCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(FireCreeper.class, (level, pos, param) -> handleCharge(new FireCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(GhostCreeper.class, (level, pos, param) -> handleCharge(new GhostCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(IceCreeper.class, (level, pos, param) -> handleCharge(new IceCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(MagmaCreeper.class, (level, pos, param) -> handleCharge(new MagmaCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(PsychicCreeper.class, (level, pos, param) -> handleCharge(new PsychicCreeper(level), pos, param), "{charged (0 or 1)}");
        SummonRegistry.add(WaterCreeper.class, (level, pos, param) -> handleCharge(new WaterCreeper(level), pos, param), "{charged (0 or 1)}");
    }
}
