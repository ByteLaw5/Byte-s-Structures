package com.bytelaw.bytesstructures.block;

import com.bytelaw.bytesstructures.block.trees.BlackWalnutTree;
import net.minecraft.block.SaplingBlock;

public class BlackWalnutSaplingBlock extends SaplingBlock {
    public BlackWalnutSaplingBlock(Properties properties) {
        super(new BlackWalnutTree(), properties);
    }
}
