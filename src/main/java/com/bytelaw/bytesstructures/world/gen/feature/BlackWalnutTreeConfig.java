package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ThreeLayerFeature;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class BlackWalnutTreeConfig extends BaseTreeFeatureConfig {
    public BlackWalnutTreeConfig(List<TreeDecorator> decoratorsIn) {
        super(new SimpleBlockStateProvider(BytesBlocks.BLACK_WALNUT_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BytesBlocks.BLACK_WALNUT_LEAVES.get().getDefaultState()), new FancyFoliagePlacer(2, 2, 2, 2, 2), new GiantTrunkPlacer(7, 7, 7), new ThreeLayerFeature(5, 5, 5, 5, 5, OptionalInt.of(5)), decoratorsIn, 5, true, Heightmap.Type.WORLD_SURFACE_WG);
    }
}
