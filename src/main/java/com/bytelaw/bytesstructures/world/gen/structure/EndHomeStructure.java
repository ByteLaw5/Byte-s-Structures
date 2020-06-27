package com.bytelaw.bytesstructures.world.gen.structure;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;

public class EndHomeStructure extends BaseStructure<ChanceConfig> {
    public EndHomeStructure() {
        super(new Builder<>("end_home", ChanceConfig.CODEC, (generator, manager, chunkX, chunkZ, biome, config, components, random) -> {
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;

            int y = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, y, z);

            if(config.chance > random.nextInt(1000))
                return;

            EndHomePieces.addStructurePieces(manager, pos, rotation, components, random);
        }).separation(20, 70));
    }
}
