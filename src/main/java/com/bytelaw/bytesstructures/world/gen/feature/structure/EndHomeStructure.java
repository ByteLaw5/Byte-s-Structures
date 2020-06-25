package com.bytelaw.bytesstructures.world.gen.feature.structure;

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

public class EndHomeStructure extends ScatteredStructure<ChanceConfig> {
    public EndHomeStructure() {
        super(ChanceConfig::deserialize);
    }

    @Override
    protected int getSeedModifier() {
        return 214878353;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public String getStructureName() {
        return "bytesstructures:end_home";
    }

    @Override
    public int getSize() {
        return 1;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;

            int y = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, y, z);

            EndHomePieces.addStructurePieces(templateManagerIn, pos, rotation, components, rand);
            this.recalculateStructureSize();
        }
    }
}
