package com.bytelaw.bytesstructures;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.bytelaw.bytesstructures.block.tree.BytesTreeDecorators;
import com.bytelaw.bytesstructures.client.GuardRenderer;
import com.bytelaw.bytesstructures.config.BytesConfig;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import com.bytelaw.bytesstructures.item.BytesItems;
import com.bytelaw.bytesstructures.world.BytesBiomes;
import com.bytelaw.bytesstructures.world.gen.feature.BlackWalnutBigTreeFeatureConfig;
import com.bytelaw.bytesstructures.world.gen.feature.BytesFeatures;
import com.google.common.collect.Lists;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BytesConfig.COMMON_SPEC);

        BytesBlocks.BLOCKS.register(modBus);
        BytesItems.ITEMS.register(modBus);
        BytesFeatures.FEATURES.register(modBus);
        BytesEntities.ENTITIES.register(modBus);
        BytesTreeDecorators.TREE_DECORATORS.register(modBus);
        BytesBiomes.BIOMES.register(modBus);
        modBus.addListener(this::registerBlockColors);
        modBus.addListener(this::registerItemColors);
        modBus.addListener(this::loadComplete);
        modBus.addListener(this::client);
    }

    @SuppressWarnings("deprecation")
    private void loadComplete(FMLLoadCompleteEvent event) {
        DeferredWorkQueue.runLater(() -> {
            BytesBiomes.WALNUT_FOREST.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BytesFeatures.BLACK_WALNUT_BIG_TREE.get().withConfiguration(new BlackWalnutBigTreeFeatureConfig(Lists.newArrayList())).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(12, 0.1F, 1))));
            BytesBiomes.WALNUT_FOREST.get().addStructure(BytesFeatures.TEST_STRUCTURE.get().withConfiguration(new ProbabilityConfig(BytesConfig.testStructureSpawnChance)));
            BytesBiomes.WALNUT_FOREST.get().addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, BytesFeatures.TEST_STRUCTURE.get().withConfiguration(new ProbabilityConfig(BytesConfig.testStructureSpawnChance)).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(BytesConfig.testStructureSpawnChance))));
        });
        LOGGER.info("Completed loading!");
    }

    private void client(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(BytesEntities.GUARD.get(), GuardRenderer::new);
        RenderTypeLookup.setRenderLayer(BytesBlocks.BLACK_WALNUT_LEAVES.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BytesBlocks.BLACK_WALNUT_SAPLING.get(), RenderType.getCutout());
    }

    private void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state, lightReader, pos, i) -> FoliageColors.get(0.5D, 1.0D), BytesBlocks.BLACK_WALNUT_LEAVES.get());
    }

    private void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, i) -> event.getBlockColors().getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, i), BytesItems.BLACK_WALNUT_LEAVES.get());
    }

    @Nonnull
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
