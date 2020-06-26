package com.bytelaw.bytesstructures.world.gen.structure;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class EndHomeStructure extends Structure<ChanceConfig> {
    public EndHomeStructure() {
        super(ChanceConfig.CODEC);
    }

    @Override
    public IStartFactory<ChanceConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    public String getStructureName() {
        return "bytesstructures:end_home";
    }

    @Override
    public GenerationStage.Decoration func_236396_f_() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<ChanceConfig> {
        public Start(Structure<ChanceConfig> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
        }

        @Override
        public void func_230364_a_(ChunkGenerator p_230364_1_, TemplateManager p_230364_2_, int p_230364_3_, int p_230364_4_, Biome p_230364_5_, ChanceConfig p_230364_6_) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            int x = (p_230364_3_ << 4) + 7;
            int z = (p_230364_4_ << 4) + 7;

            int y = p_230364_1_.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, y, z);

            if(p_230364_6_.chance > rand.nextInt(1000))
                return;

            EndHomePieces.addStructurePieces(p_230364_2_, pos, rotation, components, rand);
            this.recalculateStructureSize();
        }
    }
}
