package com.bytelaw.bytesstructures.block;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class WalnutBlock extends Block {
    private static final VoxelShape SHAPE = VoxelShapes.create(4.0D, 16.0D, 4.0D, 12.0D, 10.0D, 12.0D);

    public WalnutBlock(Properties properties) {
        super(properties);
    }
}
