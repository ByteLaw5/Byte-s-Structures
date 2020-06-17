package com.bytelaw.bytesstructures;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.bytelaw.bytesstructures.feature.AbstractStructure;
import com.bytelaw.bytesstructures.feature.BytesFeatures;
import com.bytelaw.bytesstructures.item.BytesItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Mod(BytesStructures.MODID)
public class BytesStructures {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "bytesstructures";
    public static final ItemGroup GROUP = new ItemGroup("bytesitemgroup") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(BytesItems.ANCIENT_BRICKS.get());
        }
    };

    public BytesStructures() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setup);
        BytesBlocks.init();
        BytesItems.init();
        BytesFeatures.init();
        for(DeferredRegister<?> deferredRegister : RegUtil.DEFERRED_REGISTERS) {
            deferredRegister.register(modBus);
        }
    }

    private void setup(FMLCommonSetupEvent event) {
        for(Feature<?> feature : ForgeRegistries.FEATURES) {
            if(feature instanceof AbstractStructure) {
                AbstractStructure<?> structure = (AbstractStructure<?>)feature;
                structure.getBiomesToSpawnAt().forEach(biome -> {
                    biome.addStructure(BytesFeatures.TEST_STRUCTURE.get().withConfiguration(new ProbabilityConfig(0.15F)));
                    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, BytesFeatures.TEST_STRUCTURE.get().withConfiguration(new ProbabilityConfig(0.15F)));
                    AbstractStructure.LOGGER_STATIC.debug("Added structure to biome " + biome);
                });
            }
        }
        LOGGER.info("Completed common setup");
    }

    @Nonnull
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
