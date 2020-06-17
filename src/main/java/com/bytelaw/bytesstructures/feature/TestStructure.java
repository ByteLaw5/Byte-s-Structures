package com.bytelaw.bytesstructures.feature;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Function;

public class TestStructure extends AbstractStructure<ProbabilityConfig> {
    public TestStructure(Function<Dynamic<?>, ? extends ProbabilityConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public List<Biome> getBiomesToSpawnAt() {
        return Lists.newArrayList(ForgeRegistries.BIOMES.getValues());
    }

    @Override
    protected int getSeedModifier() {
        return 0;
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

    public static class Start extends StructureStart {
        public Start(Structure<?> structure, int cx, int cz, MutableBoundingBox boundingBox, int references, long seed) {
            super(structure, cx, cz, boundingBox, references, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            int i = chunkX * 16;
            int j = chunkZ * 16;

            int k = generator.func_222531_c(i, j, Heightmap.Type.WORLD_SURFACE);
            BlockPos pos = new BlockPos(i, k, j);

            TestStructurePieces.addStructurePieces(templateManagerIn, pos, this.components);
            this.components.forEach(piece -> piece.buildComponent(piece, this.components, this.rand));
            this.recalculateStructureSize();
        }
    }
}
