package com.bytelaw.bytesstructures.config;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BytesConfig {
    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    //Right now this value doesn't have a use until I figure out how to properly spawn structures with a chance
    public static int ancientLootStructureSpawnChance;
    public static int walnutForestBiomeSpawnChance;
    public static int slimeyDungeonSpawnChance;
    public static int endHomeSpawnChance;
    public static boolean spawnStructures;

    @SubscribeEvent
    public static void bakeConfigEvent(ModConfig.ModConfigEvent configEvent) {
        if(configEvent.getConfig().getSpec() == SERVER_SPEC) {
            bakeConfig();
        }
    }

    public static void bakeConfig() {
        ancientLootStructureSpawnChance = SERVER.ancientLootStructureSpawnChance.get();
        walnutForestBiomeSpawnChance = SERVER.walnutForestBiomeSpawnChance.get();
        slimeyDungeonSpawnChance = SERVER.slimeyDungeonSpawnChance.get();
        endHomeSpawnChance = SERVER.endHomeSpawnChance.get();
        spawnStructures = SERVER.spawnStructures.get();
    }

    public static class ServerConfig {
        public final ForgeConfigSpec.ConfigValue<Integer> ancientLootStructureSpawnChance;
        public final ForgeConfigSpec.ConfigValue<Integer> walnutForestBiomeSpawnChance;
        public final ForgeConfigSpec.ConfigValue<Integer> slimeyDungeonSpawnChance;
        public final ForgeConfigSpec.ConfigValue<Integer> endHomeSpawnChance;
        public final ForgeConfigSpec.ConfigValue<Boolean> spawnStructures;

        public ServerConfig(ForgeConfigSpec.Builder builder) {
            builder.push("structures");
            spawnStructures = builder
                    .comment("Enable or disable structure spawning")
                    .define("spawnStructures", true);
            ancientLootStructureSpawnChance = builder
                    .comment("Chance that the ancient loot structure will spawn")
                    .worldRestart() //Makes it that this will need a world restart
                    .defineInRange("ancientLootStructureSpawnChance", 15, 0, 100);
            slimeyDungeonSpawnChance = builder
                    .comment("Chance that the slimey dungeon will spawn")
                    .worldRestart()
                    .defineInRange("slimeyDungeonSpawnChance", 25, 0, 100);
            endHomeSpawnChance = builder
                    .comment("Chance that the end home will spawn")
                    .worldRestart()
                    .defineInRange("endHomeSpawnChance", 10, 0, 100);
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
