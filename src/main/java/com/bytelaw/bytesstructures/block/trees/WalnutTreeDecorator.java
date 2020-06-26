package com.bytelaw.bytesstructures.block.trees;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class WalnutTreeDecorator extends TreeDecorator {
    private static final WalnutTreeDecorator INSTANCE = new WalnutTreeDecorator();
    public static final Codec<WalnutTreeDecorator> CODEC = Codec.unit(INSTANCE);
    private static final Direction[] GENERATE_DIRECTIONS = new Direction[]{Direction.WEST, Direction.EAST, Direction.SOUTH};

    @Override
    protected TreeDecoratorType<?> func_230380_a_() {
        return BytesTreeDecorators.WALNUT.get();
    }

    @Override
    public void func_225576_a_(IWorld p_225576_1_, Random p_225576_2_, List<BlockPos> p_225576_3_, List<BlockPos> p_225576_4_, Set<BlockPos> p_225576_5_, MutableBoundingBox p_225576_6_) {
        Direction direction = GENERATE_DIRECTIONS[p_225576_2_.nextInt(GENERATE_DIRECTIONS.length)];
        int i = !p_225576_4_.isEmpty() ? Math.max(p_225576_4_.get(0).getY() - 1, p_225576_3_.get(0).getY()) : Math.min(p_225576_3_.get(0).getY() + 1 + p_225576_2_.nextInt(3), p_225576_3_.get(p_225576_3_.size() - 1).getY());
        List<BlockPos> list = p_225576_3_.stream().filter((p_227416_1_) -> p_227416_1_.getY() == i).collect(Collectors.toList());
        if (!list.isEmpty()) {
            BlockPos blockpos = list.get(p_225576_2_.nextInt(list.size()));
            BlockPos blockpos1 = blockpos.offset(direction);
            if (p_225576_1_.isAirBlock(blockpos1) && p_225576_1_.isAirBlock(blockpos1.offset(Direction.SOUTH))) {
                BlockState blockstate = BytesBlocks.WALNUT.get().getDefaultState();
                this.func_227423_a_(p_225576_1_, blockpos1, blockstate, p_225576_5_, p_225576_6_);
            }
        }
    }
}