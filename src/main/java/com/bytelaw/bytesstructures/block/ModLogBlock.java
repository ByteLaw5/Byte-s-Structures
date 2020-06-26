package com.bytelaw.bytesstructures.block;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public class ModLogBlock extends RotatedPillarBlock {
    public ModLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isIn(ITag<Block> tagIn) {
        return tagIn == BlockTags.LOGS;
    }
}
