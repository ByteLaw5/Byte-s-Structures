package com.bytelaw.bytesstructures;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.bytelaw.bytesstructures.block.trees.BytesTreeDecorators;
import com.bytelaw.bytesstructures.block.trees.WalnutTreeDecorator;
import com.bytelaw.bytesstructures.client.Client;
import com.bytelaw.bytesstructures.config.BytesConfig;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import com.bytelaw.bytesstructures.entity.GuardEntity;
import com.bytelaw.bytesstructures.item.BytesItems;
import com.bytelaw.bytesstructures.world.BytesBiomes;
import com.bytelaw.bytesstructures.world.gen.feature.BlackWalnutHugeTreeConfig;
import com.bytelaw.bytesstructures.world.gen.feature.BytesFeatures;
import com.bytelaw.bytesstructures.world.gen.structure.BytesFeatureStructures;
import com.bytelaw.bytesstructures.world.gen.structure.ChanceConfig;
import com.google.common.collect.Lists;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
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

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, BytesConfig.SERVER_SPEC);

        BytesBlocks.BLOCKS.register(modBus);
        BytesItems.ITEMS.register(modBus);
        BytesFeatures.FEATURES.register(modBus);
        BytesEntities.ENTITIES.register(modBus);
        BytesTreeDecorators.TREE_DECORATORS.register(modBus);
        BytesBiomes.BIOMES.register(modBus);
        BytesFeatureStructures.STRUCTURES.register(modBus);
        modBus.addListener(this::loadComplete);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityJoinWorld);

        if(FMLEnvironment.dist == Dist.CLIENT) {
            modBus.register(Client.class);
        }

        LOGGER.info("Mod construction complete");
    }

    @SuppressWarnings("deprecation")
    private void loadComplete(FMLLoadCompleteEvent event) {
        DeferredWorkQueue.runLater(() -> {
            BytesBiomes.WALNUT_FOREST.get().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BytesFeatures.BLACK_WALNUT_BIG_TREE.get().withConfiguration(new BlackWalnutHugeTreeConfig(Lists.newArrayList(new WalnutTreeDecorator()))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(12, 0.1F, 1))));
            BytesBiomes.WALNUT_FOREST.get().func_235063_a_(BytesFeatureStructures.ANCIENT_LOOT_STRUCTURE.get().func_236391_a_(new ChanceConfig(BytesConfig.ancientLootStructureSpawnChance)));
            Biomes.SWAMP.func_235063_a_(BytesFeatureStructures.SLIMEY_DUNGEON.get().func_236391_a_(new ChanceConfig(BytesConfig.slimeyDungeonSpawnChance)));
            Biomes.END_BARRENS.func_235063_a_(BytesFeatureStructures.END_HOME.get().func_236391_a_(new ChanceConfig(BytesConfig.endHomeSpawnChance)));
            Biomes.END_HIGHLANDS.func_235063_a_(BytesFeatureStructures.END_HOME.get().func_236391_a_(new ChanceConfig(BytesConfig.endHomeSpawnChance)));
            Biomes.END_MIDLANDS.func_235063_a_(BytesFeatureStructures.END_HOME.get().func_236391_a_(new ChanceConfig(BytesConfig.endHomeSpawnChance)));
            Biomes.field_235251_aB_.func_235063_a_(BytesFeatureStructures.BASALT_DUNGEON.get().func_236391_a_(IFeatureConfig.NO_FEATURE_CONFIG));
        });
        LOGGER.info("Completed loading");
    }

    private void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof MonsterEntity) {
            MonsterEntity monster = (MonsterEntity)entity;
            NearestAttackableTargetGoal<GuardEntity> attackGuardGoal = new NearestAttackableTargetGoal<>(monster, GuardEntity.class, true);
            monster.targetSelector.addGoal(7, attackGuardGoal);
        } else if(entity instanceof GolemEntity && !(entity instanceof IronGolemEntity)) {
            GolemEntity golem = (GolemEntity)entity;
            NearestAttackableTargetGoal<GuardEntity> attackGuardGoal = new NearestAttackableTargetGoal<>(golem, GuardEntity.class, true);
            golem.targetSelector.addGoal(7, attackGuardGoal);
        } else if(entity instanceof SlimeEntity) {
            SlimeEntity monster = (SlimeEntity)entity;
            NearestAttackableTargetGoal<GuardEntity> attackGuardGoal = new NearestAttackableTargetGoal<>(monster, GuardEntity.class, true);
            monster.targetSelector.addGoal(7, attackGuardGoal);
        }
    }

    @Nonnull
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public static ResourceLocation resource(@Nonnull String path) {
        return new ResourceLocation(MODID, path);
    }
}
