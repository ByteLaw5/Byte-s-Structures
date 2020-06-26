package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class BlackWalnutTreeFeatureConfig extends TreeFeatureConfig {
    public <T> BlackWalnutTreeFeatureConfig(List<TreeDecorator> decoratorsIn) {
        super(new SimpleBlockStateProvider(BytesBlocks.BLACK_WALNUT_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BytesBlocks.BLACK_WALNUT_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 3), decoratorsIn, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, true);
    }

    public static <T> BlackWalnutTreeFeatureConfig deserialize(Dynamic<T> dynamic) {
        BlackWalnutTreeFeatureConfig config = (BlackWalnutTreeFeatureConfig)deserializeFoliage(dynamic);
        config.setSapling((IPlantable)BytesBlocks.BLACK_WALNUT_SAPLING.get());
        return config;
    }
}
