package com.bytelaw.bytesstructures.config;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BytesConfig {
    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static float testStructureSpawnChance;

    @SubscribeEvent
    public static void bakeConfigEvent(ModConfig.ModConfigEvent configEvent) {
        if(configEvent.getConfig().getSpec() == COMMON_SPEC) {
            bakeConfig();
        }
    }

    public static void bakeConfig() {
        int spawnChanceTest = COMMON.testStructureSpawnChance.get();
        testStructureSpawnChance = (float)spawnChanceTest / 100;
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.ConfigValue<Integer> testStructureSpawnChance;

        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("structures");
            testStructureSpawnChance = builder
                    .comment("Chance that the test structure will spawn")
                    .worldRestart() //Makes it that this will need a world restart
                    .defineInRange("testStructureSpawnChance", 15, 0, 100);
            builder.pop();
        }
    }
}
