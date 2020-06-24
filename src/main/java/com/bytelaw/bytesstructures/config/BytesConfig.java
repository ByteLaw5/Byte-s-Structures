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

    //Right now this value doesn't have a use until I figure out how to properly spawn structures with a chance
    public static int ancientLootStructureSpawnChance;
    public static int walnutForestBiomeSpawnChance;
    public static int slimeyDungeonSpawnChance;

    @SubscribeEvent
    public static void bakeConfigEvent(ModConfig.ModConfigEvent configEvent) {
        if(configEvent.getConfig().getSpec() == COMMON_SPEC) {
            bakeConfig();
        }
    }

    public static void bakeConfig() {
        ancientLootStructureSpawnChance = COMMON.ancientLootStructureSpawnChance.get();
        walnutForestBiomeSpawnChance = COMMON.walnutForestBiomeSpawnChance.get();
        slimeyDungeonSpawnChance = COMMON.slimeyDungeonSpawnChance.get();
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.ConfigValue<Integer> ancientLootStructureSpawnChance;
        public final ForgeConfigSpec.ConfigValue<Integer> walnutForestBiomeSpawnChance;
        public final ForgeConfigSpec.ConfigValue<Integer> slimeyDungeonSpawnChance;

        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("structures");
            ancientLootStructureSpawnChance = builder
                    .comment("Chance that the structure will spawn")
                    .worldRestart() //Makes it that this will need a world restart
                    .defineInRange("ancientLootStructureSpawnChance", 15, 0, 100);
            slimeyDungeonSpawnChance = builder
                    .comment("Chance that the slimey dungeon will spawn")
                    .worldRestart()
                    .defineInRange("slimeyDungeonSpawnChance", 15, 0, 100);
            builder.pop();
            builder.push("biomes");
            walnutForestBiomeSpawnChance = builder
                    .comment("Chance that the walnut forest biome will spawn")
                    .worldRestart()
                    .defineInRange("walnutForestBiomeSpawnChance", 5, 0, 100);
            builder.pop();
        }
    }
}
