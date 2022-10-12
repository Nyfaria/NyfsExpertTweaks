package com.nyfaria.nyfsexperttweaks;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Collection;

public class NyfsExpertTweaks implements ModInitializer {

	public static final String MOD_ID = "nyfsexperttweaks";

	public static final PotionDelayStatusEffect POTION_DELAY = new PotionDelayStatusEffect();
	public static final Block UNKNOWN = new Block(Block.Settings.copy(Blocks.DIRT));
	public static final BlockItem UNKNOWN_ITEM = new BlockItem(UNKNOWN, new FabricItemSettings()){
		@Override
		public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
			stack.decrement(stack.getCount());
		}
	};

	@Override
	public void onInitialize() {
//		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();

		MidnightConfig.init(MOD_ID, NyfsExpertTweaksConfig.class);
		Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "potion_delay"), POTION_DELAY);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "unknown"), UNKNOWN);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "unknown"), UNKNOWN_ITEM);



	}


}
