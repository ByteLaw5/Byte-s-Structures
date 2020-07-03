package com.bytelaw.bytesstructures.world.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class LargeRockFeature extends Feature<LargeRockConfig> {
    public LargeRockFeature() {
        super(LargeRockConfig.CODEC);
    }

    @Override
    public boolean func_230362_a_(ISeedReader p_230362_1_, StructureManager p_230362_2_, ChunkGenerator p_230362_3_, Random p_230362_4_, BlockPos p_230362_5_, LargeRockConfig p_230362_6_) {
        return true;
    }
}
