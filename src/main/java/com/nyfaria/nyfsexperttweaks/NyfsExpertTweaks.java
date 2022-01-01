package com.nyfaria.nyfsexperttweaks;

import com.nyfaria.nyfsexperttweaks.modtweaks.WTHITTweaks;
import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Collection;

public class NyfsExpertTweaks implements ModInitializer {

	public static final String MOD_ID = "nyfsexperttweaks";

	public static final NyfsExpertTweaksConfig CONFIG = OmegaConfig.register(NyfsExpertTweaksConfig.class);
	public static final PotionDelayStatusEffect POTION_DELAY = new PotionDelayStatusEffect();

	@Override
	public void onInitialize() {
		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();
		for (ModContainer mod : mods) {
			if (mod.getMetadata().getId().equals("wthit") && CONFIG.WTIT_DISCOVERY)
			WTHITTweaks.init();
		}
		Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "potion_delay"), POTION_DELAY);



	}


}
