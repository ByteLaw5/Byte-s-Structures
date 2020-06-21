package com.bytelaw.bytesstructures.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class ChancePlacement extends Placement<ChanceConfig> {
    public ChancePlacement(Function<Dynamic<?>, ? extends ChanceConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generatorIn, Random random, ChanceConfig configIn, BlockPos pos) {
        if(random.nextFloat() < 1.0F / configIn.chance)
            return Stream.of(pos);
        else
            return Stream.empty();
    }
}
