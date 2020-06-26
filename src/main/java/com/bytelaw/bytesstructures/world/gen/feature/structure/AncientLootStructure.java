package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import com.google.common.collect.Lists;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID)
public class AncientLootStructure extends Structure<ChanceConfig> {
    public static final String STRUCTURE_NAME = "bytesstructures:ancient_loot";

    public AncientLootStructure() {
        super(ChanceConfig.CONFIG);
    }

    protected int getSeedModifier() {
        return 832236751;
    }

    @Override
    public IStartFactory<ChanceConfig> getStartFactory() {
        return Start::new;
    }

    public static List<Biome.SpawnListEntry> getSpawns() {
        return Lists.newArrayList(new Biome.SpawnListEntry(BytesEntities.GUARD.get(), 10, 1, 5));
    }

    @Override
    public String getStructureName() {
        return STRUCTURE_NAME;
    }

    public static class Start extends StructureStart<ChanceConfig> {
        public Start(Structure<ChanceConfig> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
        }

        private static int getYForStart(ChunkGenerator generator, int chunkX, int chunkZ) {
            return MathHelper.clamp(generator.func_222531_c(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG), 35, 70) - 20;
        }

        @Override
        public void func_230364_a_(ChunkGenerator p_230364_1_, TemplateManager p_230364_2_, int p_230364_3_, int p_230364_4_, Biome p_230364_5_, ChanceConfig p_230364_6_) {
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            int x = (p_230364_3_ << 4) + 7;
            int z = (p_230364_4_ << 4) + 7;

            int y = getYForStart(p_230364_1_, p_230364_3_, p_230364_4_);
            BlockPos pos = new BlockPos(x, y, z);

            AncientLootStructurePieces.addStructurePieces(p_230364_2_, pos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }
}
