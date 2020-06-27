package com.bytelaw.bytesstructures.world.gen.structure;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;

public class SlimeyDungeonStructure extends BaseStructure<ChanceConfig> {
    public SlimeyDungeonStructure() {
        super(new Builder<>("slimey_dungeon", ChanceConfig.CODEC, (generator, manager, chunkX, chunkZ, biome, config, components, random) -> {
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;

            int y = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, y, z);

            if(config.chance > random.nextInt(1000))
                return;

            SlimeyDungeonPieces.addStructurePieces(manager, pos, rotation, components, random);
        }).separation(10, 50));
    }
}
