package com.bytelaw.bytesstructures.world.gen.structure;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID)
public class AncientLootStructure extends BaseStructure<ChanceConfig> {
    public AncientLootStructure() {
        super(new Builder<>(ChanceConfig.CODEC, (generator, manager, chunkX, chunkZ, biome, config, components, random) -> {
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 8;
            int z = (chunkZ << 4) + 8;

            int y = getYForStart(generator, chunkX, chunkZ);
            BlockPos pos = new BlockPos(x, y, z);

            if(config.chance > random.nextInt(1000))
                return;

            AncientLootStructurePieces.addStructurePieces(manager, pos, rotation, components, random);
        }).decorationStage(GenerationStage.Decoration.UNDERGROUND_STRUCTURES));
    }

    private static int getYForStart(ChunkGenerator generator, int chunkX, int chunkZ) {
        return MathHelper.clamp(generator.func_222531_c(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG), 35, 75) - 20;
    }
}
