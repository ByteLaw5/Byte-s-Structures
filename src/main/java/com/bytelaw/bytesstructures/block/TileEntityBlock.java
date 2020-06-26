package com.bytelaw.bytesstructures.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TileEntityBlock extends Block {
    private final Supplier<? extends TileEntity> te;

    public TileEntityBlock(Properties properties, Supplier<? extends TileEntity> te) {
        super(properties);
        this.te = te;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return te.get();
    }
}
