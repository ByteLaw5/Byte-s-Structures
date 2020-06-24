package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
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

    public static List<Biome.SpawnListEntry> getSpawns() {
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
        if(world instanceof ServerWorld) {
            BlockPos pos1 = ((ServerWorld)world).findNearestStructure(STRUCTURE_NAME, pos, 500, false);
            if(pos1 != null) {
                event.getList().addAll(AncientLootStructure.getSpawns());
            } else {
                System.out.println("position null!");
            }
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

            int y = getYForStart(generator, chunkX, chunkZ);
            BlockPos pos = new BlockPos(x, y, z);

            AncientLootStructurePieces.addStructurePieces(templateManagerIn, pos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
        }

        private static int getYForStart(ChunkGenerator<?> generator, int chunkX, int chunkZ) {
            return MathHelper.clamp(generator.func_222531_c(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG), 35, 70) - 20;
        }
    }
}
