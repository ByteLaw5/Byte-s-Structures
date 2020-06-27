package com.bytelaw.bytesstructures.block.trees;

import com.bytelaw.bytesstructures.world.gen.feature.BlackWalnutHugeTreeConfig;
import com.bytelaw.bytesstructures.world.gen.feature.BlackWalnutTreeConfig;
import com.bytelaw.bytesstructures.world.gen.feature.BytesFeatures;
import com.google.common.collect.Lists;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class BlackWalnutTree extends BigTree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random p_225547_1_) {
        return BytesFeatures.BLACK_WALNUT_BIG_TREE.get().withConfiguration(new BlackWalnutHugeTreeConfig(Lists.newArrayList(new WalnutTreeDecorator())));
    }

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return BytesFeatures.BLACK_WALNUT_TREE.get().withConfiguration(new BlackWalnutTreeConfig(Lists.newArrayList(new WalnutTreeDecorator())));
    }
}
