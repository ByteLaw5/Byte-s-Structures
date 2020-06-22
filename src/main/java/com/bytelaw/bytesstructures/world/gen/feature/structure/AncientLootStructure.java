package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID)
public class AncientLootStructure extends ScatteredStructure<ChanceConfig> {
    public static final String STRUCTURE_NAME = "bytesstructures:ancient_loot";

    public AncientLootStructure(Function<Dynamic<?>, ? extends ChanceConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    protected int getSeedModifier() {
        return 832236751;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public List<Biome.SpawnListEntry> getSpawnList() {
        return Lists.newArrayList(new Biome.SpawnListEntry(BytesEntities.GUARD.get(), 10, 1, 5));
    }

    @Override
    public String getStructureName() {
        return STRUCTURE_NAME;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @SubscribeEvent
    public static void potentialSpawns(WorldEvent.PotentialSpawns event) {
        IWorld world = event.getWorld();
        BlockPos pos = event.getPos();
        StructureStart start = world.getChunk(pos).getStructureStart(STRUCTURE_NAME);
        if(start != null) {
            boolean flag = start.getStructure().isPositionInStructure(world, pos);
            if(flag)
                event.getList().addAll(start.getStructure().getSpawnList());
        }
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
