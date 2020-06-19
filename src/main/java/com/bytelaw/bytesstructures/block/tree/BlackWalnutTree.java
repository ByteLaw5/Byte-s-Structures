package com.bytelaw.bytesstructures.block.tree;

import com.bytelaw.bytesstructures.world.gen.feature.BlackWalnutBigTreeFeatureConfig;
import com.bytelaw.bytesstructures.world.gen.feature.BlackWalnutTreeFeatureConfig;
import com.bytelaw.bytesstructures.world.gen.feature.BytesFeatures;
import com.google.common.collect.Lists;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class BlackWalnutTree extends BigTree {
    @Nullable
    @Override
    protected ConfiguredFeature<HugeTreeFeatureConfig, ?> getHugeTreeFeature(Random p_225547_1_) {
        return BytesFeatures.BLACK_WALNUT_BIG_TREE.get().withConfiguration(new BlackWalnutBigTreeFeatureConfig(Lists.newArrayList(BytesTreeDecorators.WALNUT_TREE_DECORATOR.get().func_227431_a_(null))));
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return BytesFeatures.BLACK_WALNUT_TREE.get().withConfiguration(new BlackWalnutTreeFeatureConfig(Lists.newArrayList(BytesTreeDecorators.WALNUT_TREE_DECORATOR.get().func_227431_a_(null))));
    }
}
