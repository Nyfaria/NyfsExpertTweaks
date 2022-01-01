package com.nyfaria.nyfsexperttweaks;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Iterator;
import java.util.List;

public class PotionDelayStatusEffect extends StatusEffect {

    public ItemStack potionStack;

    public PotionDelayStatusEffect() {
        super(StatusEffectType.BENEFICIAL, 0x98D982);

    }


    public PotionDelayStatusEffect(ItemStack potionStack) {
        super(StatusEffectType.BENEFICIAL, 0x98D982);
        this.potionStack = potionStack;

    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        PlayerEntity playerEntity = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
        if(potionStack == null){
            return;
        }
        List<StatusEffectInstance> list = PotionUtil.getPotionEffects(potionStack);

        for (StatusEffectInstance statusEffectInstance : list) {
            if (statusEffectInstance.getEffectType().isInstant()) {
                statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, entity, statusEffectInstance.getAmplifier(), 1.0D);
            } else {
                entity.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
            }
        }

    }
}
