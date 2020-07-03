package com.bytelaw.bytesstructures.world.gen.structure;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MeteorImpactStructure extends BaseStructure<NoFeatureConfig> {
    public MeteorImpactStructure() {
        super(new Builder<>("meteor_impact", NoFeatureConfig.field_236558_a_, (generator, manager, chunkX, chunkZ, biome, config, components, random) -> {
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 8;
            int z = (chunkZ << 4) + 8;

            int y = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG) - 20;
            BlockPos pos = new BlockPos(x, y, z);

            MeteorImpactPieces.addStructurePieces(manager, pos, rotation, components, random);
        }).decorationStage(GenerationStage.Decoration.SURFACE_STRUCTURES).separation(7, 15));
    }
}
