package com.nyfaria.nyfsexperttweaks.modtweaks;

import mcp.mobius.waila.api.event.WailaRenderEvent;
import net.minecraft.item.Item;
import net.minecraft.stat.Stats;

public class WTHITTweaks {
    public static void init()
    {
        WailaRenderEvent.WAILA_RENDER_PRE.register(event -> {
                    Item item = event.getAccessor().getStack().getItem();
                    boolean itemStat = Stats.PICKED_UP.hasStat(item);
                    if (!itemStat) {
                        return true;
                    }
                    return false;
                }
        );

    }

}
