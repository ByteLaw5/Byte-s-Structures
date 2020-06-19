package com.bytelaw.bytesstructures.feature.structure;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.Function;

public class TestStructure extends ScatteredStructure<ProbabilityConfig> {
    private static final Logger LOGGER = LogManager.getLogger();

    public TestStructure(Function<Dynamic<?>, ? extends ProbabilityConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    protected int getSeedModifier() {
        return 165745293;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public String getStructureName() {
        return "bytesstructures:test_structure";
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
        ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
            int i = chunkX >> 4;
            int j = chunkZ >> 4;
            randIn.setSeed((long)(i ^ j << 4) ^ generatorIn.getSeed());
            randIn.nextInt();
            if (randIn.nextInt(5) != 0) {
                return false;
            }

            return generatorIn.hasStructure(biomeIn, this);
        }

        return false;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> structure, int cx, int cz, MutableBoundingBox boundingBox, int references, long seed) {
            super(structure, cx, cz, boundingBox, references, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            int i = chunkX * 16;
            int j = chunkZ * 16;

            int k = generator.func_222531_c(i, j, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(i, k, j);

            TestStructurePieces.addStructurePieces(templateManagerIn, pos, this.components);
            this.recalculateStructureSize();
        }
    }
}
