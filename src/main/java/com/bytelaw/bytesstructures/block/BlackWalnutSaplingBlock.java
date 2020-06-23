package com.bytelaw.bytesstructures.block;

import com.bytelaw.bytesstructures.block.tree.BlackWalnutTree;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.SaplingBlock;

public class BlackWalnutSaplingBlock extends SaplingBlock {
    public BlackWalnutSaplingBlock(Properties properties) {
        super(new BlackWalnutTree(), properties);
        ComposterBlock.CHANCES.put(this.asItem(), 0.3F);
    }
}
