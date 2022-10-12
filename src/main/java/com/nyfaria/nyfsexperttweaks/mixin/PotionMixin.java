package com.nyfaria.nyfsexperttweaks.mixin;

import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaks;
import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaksConfig;
import com.nyfaria.nyfsexperttweaks.PotionDelayStatusEffect;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PotionItem.class)
public class PotionMixin extends Item {


    public PotionMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author TheNyfaria
     */
    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    public void potionDelay(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (NyfsExpertTweaksConfig.POTION_ABSORB) {
            PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity) playerEntity, stack);
            }


            PotionDelayStatusEffect statusEffect = NyfsExpertTweaks.POTION_DELAY;
            statusEffect.potionStack = stack;

            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(statusEffect, NyfsExpertTweaksConfig.POTION_ABSORB_TIME);
            user.addStatusEffect(statusEffectInstance);

            if (playerEntity != null) {
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                if (!playerEntity.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }

            if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
                if (stack.isEmpty()) {
                    cir.setReturnValue(new ItemStack(Items.GLASS_BOTTLE));
                }

                if (playerEntity != null) {
                    playerEntity.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
                }
            }

            world.emitGameEvent(user, GameEvent.DRINKING_FINISH, user.getCameraBlockPos());
            cir.setReturnValue(stack);
        }
    }
}
