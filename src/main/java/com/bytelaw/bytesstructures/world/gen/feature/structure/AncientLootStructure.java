package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class AncientLootStructure extends ScatteredStructure<ChanceConfig> {
    public AncientLootStructure(Function<Dynamic<?>, ? extends ChanceConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    protected int getSeedModifier() {
        return 165745293;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

//    @Override
//    public List<Biome.SpawnListEntry> getSpawnList() {
//        return Lists.newArrayList(new Biome.SpawnListEntry(BytesEntities.GUARD.get(), 10, 1, 5));
//    }

    @Override
    public String getStructureName() {
        return "bytesstructures:ancient_loot";
    }

    @Override
    public int getSize() {
        return 1;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
            super(structure, chunkX, chunkZ, boundingBox, references, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;

            int y = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, y, z);

            AncientLootStructurePieces.addStructurePieces(templateManagerIn, pos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}
