package com.bytelaw.bytesstructures.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

@SuppressWarnings("deprecation")
public class WalnutBlock extends Block {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 16.0D, 4.0D, 12.0D, 10.0D, 12.0D);

    public WalnutBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.up()).getBlock() == BytesBlocks.BLACK_WALNUT_LEAVES.get();
    }
}
