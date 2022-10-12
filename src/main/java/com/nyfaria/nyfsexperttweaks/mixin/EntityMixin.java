package com.nyfaria.nyfsexperttweaks.mixin;

import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaks;
import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaksConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.annotation.Target;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public float fallDistance;

    @Shadow protected boolean touchingWater;



    @Shadow public abstract void extinguish();


    @Shadow public abstract boolean updateMovementInFluid(TagKey<Fluid> tag, double speed);

    @Inject(at=@At(value="HEAD"), method = "checkWaterState", cancellable = true)
    public void makeWaterHurt(CallbackInfo ci){
        if(NyfsExpertTweaksConfig.WATER_HURTS) {
            if (this.updateMovementInFluid(FluidTags.WATER, 0.014D)) {
                if ((Entity) (Object) this instanceof PlayerEntity) {
                    this.fallDistance = this.fallDistance * 0.8f;
                    this.touchingWater = true;
                    this.extinguish();
                    ci.cancel();
                }
            }
        }
    }
}
