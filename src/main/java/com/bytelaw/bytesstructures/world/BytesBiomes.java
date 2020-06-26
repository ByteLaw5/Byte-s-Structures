package com.bytelaw.bytesstructures.world;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.config.BytesConfig;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BytesBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, BytesStructures.MODID);

    public static final RegistryObject<WalnutForestBiome> WALNUT_FOREST = BIOMES.register("walnut_forest", WalnutForestBiome::new);

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void registerBiomesEvent(RegistryEvent.Register<Biome> event) {
        BiomeDictionary.makeBestGuess(WALNUT_FOREST.get());
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(WALNUT_FOREST.get(), BytesConfig.walnutForestBiomeSpawnChance));
        BiomeManager.addSpawnBiome(WALNUT_FOREST.get());
    }
}
