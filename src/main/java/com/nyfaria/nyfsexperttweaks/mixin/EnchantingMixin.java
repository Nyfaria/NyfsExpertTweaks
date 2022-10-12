package com.nyfaria.nyfsexperttweaks.mixin;

import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaks;
import com.nyfaria.nyfsexperttweaks.NyfsExpertTweaksConfig;
import net.minecraft.advancement.criterion.EnchantedItemCriterion;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Random;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantingMixin extends ScreenHandler {
    @Shadow @Final public int[] enchantmentPower;
    @Shadow @Final public int[] enchantmentLevel;
    @Shadow
    @Final
    private Inventory inventory;
    Random random = new Random();

    @Unique
    public int power;

    protected EnchantingMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "method_17410", at = @At(value = "HEAD"/*, target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"*/))
    public void assignPower(ItemStack itemStack, int i, PlayerEntity player, int id, ItemStack itemStack2, World world, BlockPos pos, CallbackInfo cir) {
        power = enchantmentPower[id - 1];
    }
    @Inject(method = "method_17410", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"))
    public void enchantmentFail(ItemStack itemStack, int i, PlayerEntity player, int id, ItemStack itemStack2, World world, BlockPos pos, CallbackInfo cir) {

        if (NyfsExpertTweaksConfig.ENCHANT_FAIL && !player.world.isClient) {
            System.out.println(power);
            if (random.nextInt(100) < (NyfsExpertTweaksConfig.ENCHANT_FAIL_CHANCE * power)) {
                this.inventory.getStack(0).setCount(0);
                this.inventory.setStack(0, ItemStack.EMPTY);
                if (NyfsExpertTweaksConfig.ENCHANT_EXPLODE_FAIL && random.nextInt(100) < NyfsExpertTweaksConfig.ENCHANT_EXPLODE_CHANCE) {
                    player.world.createExplosion(null, player.getX(), player.getBodyY(0.0625D), player.getZ(), 1.0F, Explosion.DestructionType.BREAK);

                }
            }
        }
    }


    @Shadow
    public boolean canUse(PlayerEntity player) {
        return false;
    }


}
