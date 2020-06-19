package com.bytelaw.bytesstructures.block.tree;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class WalnutTreeDecorator extends TreeDecorator {
    public WalnutTreeDecorator() {
        super(BytesTreeDecorators.WALNUT_TREE_DECORATOR.get());
    }

    public <T> WalnutTreeDecorator(Dynamic<T> dynamic) {
        this();
    }

    @Override
    public void func_225576_a_(IWorld p_225576_1_, Random p_225576_2_, List<BlockPos> p_225576_3_, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, MutableBoundingBox p_225576_6_) {
        Direction direction = BeehiveBlock.GENERATE_DIRECTIONS[p_225576_2_.nextInt(BeehiveBlock.GENERATE_DIRECTIONS.length)];
        int i = !p_225576_4_.isEmpty() ? Math.max(p_225576_4_.get(0).getY() - 1, p_225576_3_.get(0).getY()) : Math.min(p_225576_3_.get(0).getY() + 1 + p_225576_2_.nextInt(3), p_225576_3_.get(p_225576_3_.size() - 1).getY());
        List<BlockPos> list = p_225576_3_.stream().filter((p_227416_1_) -> p_227416_1_.getY() == i).collect(Collectors.toList());
        if (!list.isEmpty()) {
            BlockPos blockpos = list.get(p_225576_2_.nextInt(list.size()));
            BlockPos blockpos1 = blockpos.offset(direction);
            if (AbstractTreeFeature.isAir(p_225576_1_, blockpos1) && AbstractTreeFeature.isAir(p_225576_1_, blockpos1.offset(Direction.SOUTH))) {
                BlockState blockstate = BytesBlocks.WALNUT.get().getDefaultState();
                this.func_227423_a_(p_225576_1_, blockpos1, blockstate, p_225576_5_, p_225576_6_);
            }
        }
    }

    @Override
    public <T> T serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("type"), ops.createString(ForgeRegistries.TREE_DECORATOR_TYPES.getKey(this.field_227422_a_).toString())))).getValue();
    }
}
