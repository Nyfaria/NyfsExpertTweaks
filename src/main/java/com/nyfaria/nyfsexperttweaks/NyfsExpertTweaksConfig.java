package com.nyfaria.nyfsexperttweaks;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

public class NyfsExpertTweaksConfig implements Config {

    @Comment(value = "WTHIT Tweak to only show if block has been inside the inventory before")
    public boolean WTIT_DISCOVERY = true;
    @Comment(value = "Do Potions take time to Absorb?")
    public boolean POTION_ABSORB = true;
    @Comment(value = "If so, How long?")
    public int POTION_ABSORB_TIME = 100;
    @Comment(value = "Does Water hurt?(Water will slow descent, and damage will be reduced not negated)")
    public boolean WATER_HURTS = true;
    @Comment(value = "Does Enchanting have a Chance to Fail?")
    public boolean ENCHANT_FAIL = true;
    @Comment(value = "What is the chance per level the Enchant will fail?")
    public double ENCHANT_FAIL_CHANCE = 1.11;
    @Comment(value = "Does Enchantment Fail Explode?")
    public boolean ENCHANT_EXPLODE_FAIL = true;
    @Comment(value = "If true, what percent chance is it to Explode?")
    public double ENCHANT_EXPLODE_CHANCE = 33;

    @Override
    public String getName() {
        return "nyfsexperttweaks";
    }
}
