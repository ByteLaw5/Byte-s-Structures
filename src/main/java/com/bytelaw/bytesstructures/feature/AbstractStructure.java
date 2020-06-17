package com.bytelaw.bytesstructures.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public abstract class AbstractStructure<C extends ProbabilityConfig> extends ScatteredStructure<C> {
    public static final Logger LOGGER_STATIC = LogManager.getLogger();

    public AbstractStructure(Function<Dynamic<?>, ? extends C> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, C config) {
        return rand.nextFloat() < config.probability && super.place(worldIn, generator, rand, pos, config);
    }

    public abstract List<Biome> getBiomesToSpawnAt();
}
