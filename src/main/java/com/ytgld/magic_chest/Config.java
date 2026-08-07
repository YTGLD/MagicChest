package com.ytgld.magic_chest;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final Pair<Config, ModConfigSpec> BUILDER = (new ModConfigSpec.Builder()).configure(Config::new);
    public static Config config;
    public static ModConfigSpec fc;

    public Config(ModConfigSpec.Builder builder) {
        builder.push("Common");
        builder.pop();
    }

    static {
        config = BUILDER.getKey();
        fc = BUILDER.getRight();
    }
}
