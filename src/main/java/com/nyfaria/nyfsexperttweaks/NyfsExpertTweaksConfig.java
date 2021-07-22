package com.nyfaria.nyfsexperttweaks;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

public class NyfsExpertTweaksConfig implements Config {

    @Comment(value = "WTHIT Tweak to only show if block has been inside the inventory before")
    public boolean WTIT_DISCOVERY = true;

    @Override
    public String getName() {
        return "nyfsexperttweaks";
    }
}
