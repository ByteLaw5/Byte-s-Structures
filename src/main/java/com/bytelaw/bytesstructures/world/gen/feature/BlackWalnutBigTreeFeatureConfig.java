package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class BlackWalnutBigTreeFeatureConfig extends HugeTreeFeatureConfig {
    public BlackWalnutBigTreeFeatureConfig(List<TreeDecorator> decoratorsIn) {
        super(new SimpleBlockStateProvider(BytesBlocks.BLACK_WALNUT_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BytesBlocks.BLACK_WALNUT_LEAVES.get().getDefaultState()), decoratorsIn, 5, 5, 5);
    }

    public static <T> BlackWalnutBigTreeFeatureConfig deserialize(Dynamic<T> dynamic) {
        BlackWalnutBigTreeFeatureConfig config = (BlackWalnutBigTreeFeatureConfig)func_227277_a_(dynamic);
        config.setSapling((IPlantable)BytesBlocks.BLACK_WALNUT_SAPLING.get());
        return config;
    }
}
