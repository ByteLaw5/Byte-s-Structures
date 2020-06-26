package com.bytelaw.bytesstructures.block;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;

public class ModLogBlock extends LogBlock {
    public ModLogBlock(MaterialColor verticalColorIn, Properties properties) {
        super(verticalColorIn, properties);
    }

    @Override
    public boolean isIn(Tag<Block> tagIn) {
        return tagIn == BlockTags.LOGS;
    }
}
