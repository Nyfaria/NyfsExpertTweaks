package com.nyfaria.nyfsexperttweaks.modtweaks;

import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaks;
import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaksConfig;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(NyfsExpertTweaks.MOD_ID)
public class WTHITTweaks implements IWailaPlugin {
//    public static void init()
//    {
//
//        WailaRenderEvent.WAILA_RENDER_PRE.register(event -> {
//                    Item item = event.getAccessor().getStack().getItem();
//                    boolean itemStat = Stats.PICKED_UP.hasStat(item);
//                    if (!itemStat) {
//                        return true;
//                    }
//                    return false;
//                }
//        );
//
//    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.addRayTraceCallback(
                (hitResult, accessor, accessor1)->{

                    if (NyfsExpertTweaksConfig.WTIT_DISCOVERY && accessor instanceof BlockAccessor blockAccessor) {
                        Item item = blockAccessor.getBlock().asItem();
                        boolean itemStat = Stats.PICKED_UP.hasStat(item);
                        BlockState camo = NyfsExpertTweaks.UNKNOWN.getDefaultState();
                        if (camo != null && !itemStat) {
                            return registration.blockAccessor().from(blockAccessor).blockState(camo).build();
                        }
                    }
                    if(NyfsExpertTweaksConfig.WTIT_ENTITY_DISCOVERY && accessor instanceof EntityAccessor entityAccessor){
                        boolean itemStat = false;//Stats.PICKED_UP.hasStat(item);
                        Item camo = NyfsExpertTweaks.UNKNOWN_ITEM;
                        ItemEntity clone = new ItemEntity(MinecraftClient.getInstance().world, 0,0,0,new ItemStack(camo));
                        if(entityAccessor.getEntity() instanceof ItemEntity itemEntity){
                            Item item = itemEntity.getStack().getItem();
                            itemStat = Stats.PICKED_UP.hasStat(item);
                        }
                        if(entityAccessor.getEntity() instanceof LivingEntity livingEntity){
                            itemStat = Stats.KILLED.hasStat(livingEntity.getType());
                        }
                        if (!itemStat) {
                            return registration.entityAccessor().from(entityAccessor).entity(clone).build();
                        }
                    }
                    return accessor;
                }
        );
    }
}
