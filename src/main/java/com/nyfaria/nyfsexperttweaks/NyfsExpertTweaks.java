package com.nyfaria.nyfsexperttweaks;

import com.nyfaria.nyfsexperttweaks.tweaks.WTHITTweaks;
import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.util.Collection;

public class NyfsExpertTweaks implements ModInitializer {

	public static final String MOD_ID = "nyfsexperttweaks";

	public static final NyfsExpertTweaksConfig CONFIG = OmegaConfig.register(NyfsExpertTweaksConfig.class);

	@Override
	public void onInitialize() {
		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();
		for (ModContainer mod : mods) {
			if (mod.getMetadata().getId().equals("wthit") && CONFIG.WTIT_DISCOVERY)
			WTHITTweaks.init();
		}
	}


}
