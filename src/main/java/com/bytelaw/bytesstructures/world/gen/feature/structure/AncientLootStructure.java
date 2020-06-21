package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.Random;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID)
public class AncientLootStructure extends Structure<NoFeatureConfig> {
    public AncientLootStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    protected int getSeedModifier() {
        return 165745293;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public String getStructureName() {
        return "bytesstructures:ancient_loot";
    }

    @Override
    public int getSize() {
        return 1;
    }

    @SubscribeEvent
    public static void getPossibleCreatures(WorldEvent.PotentialSpawns event) {
        event.getList().addAll(Lists.newArrayList(new Biome.SpawnListEntry(BytesEntities.GUARD.get(), 10, 1, 3)));
    }

    @Override
    public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
        ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);

        if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
            return generatorIn.hasStructure(biomeIn, this);
        }

        return false;
    }

    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
        int maxDistance = 12;
        int minDistance = 7;

        int xTemp = x + maxDistance * spacingOffsetsX;
        int ztemp = z + maxDistance * spacingOffsetsZ;
        int xTemp2 = xTemp < 0 ? xTemp - maxDistance + 1 : xTemp;
        int zTemp2 = ztemp < 0 ? ztemp - maxDistance + 1 : ztemp;
        int validChunkX = xTemp2 / maxDistance;
        int validChunkZ = zTemp2 / maxDistance;

        ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), validChunkX, validChunkZ, this.getSeedModifier());
        validChunkX = validChunkX * maxDistance;
        validChunkZ = validChunkZ * maxDistance;
        validChunkX = validChunkX + random.nextInt(maxDistance - minDistance);
        validChunkZ = validChunkZ + random.nextInt(maxDistance - minDistance);

        return new ChunkPos(validChunkX, validChunkZ);
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

            LogManager.getLogger().info("Structure at x:{} y:{} z:{}", x, y, z);
        }
    }
}
