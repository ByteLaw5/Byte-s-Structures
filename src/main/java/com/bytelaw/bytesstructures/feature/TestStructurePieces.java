package com.bytelaw.bytesstructures.feature;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.List;
import java.util.Random;

public class TestStructurePieces {
    private static final ResourceLocation
            LOCATION = BytesStructures.resource("teststructureroom"),
            LOCATION_2 = BytesStructures.resource("teststructureroom2");

    public static void addStructurePieces(TemplateManager manager, BlockPos pos, List<StructurePiece> pieces) {
        pieces.add(new Piece(pos, manager));
        pieces.add(new AncientPiece(pos, manager));
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(BlockPos pos, TemplateManager manager) {
            super(BytesFeatures.TEST_STRUCTURE_ROOM, 0);
            this.templatePosition = pos;
            setup(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(BytesFeatures.TEST_STRUCTURE_ROOM, nbt);
            setup(manager);
        }

        protected void setup(TemplateManager manager) {
            PlacementSettings settings = new PlacementSettings().addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            Template template = manager.getTemplateDefaulted(LOCATION);
            setup(template, this.templatePosition, settings);
        }

        @Override
        public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
            PlacementSettings placementsettings = (new PlacementSettings()).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockpos = BlockPos.ZERO;
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, -blockpos.getZ())));
            int i = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
            boolean flag = super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);

            this.templatePosition = blockpos2;
            return flag;
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if("chest".equals(function)) {
                TileEntity te = worldIn.getTileEntity(pos);
                if(te instanceof ChestTileEntity) {
                    ((ChestTileEntity)te).setLootTable(LootTables.CHESTS_BURIED_TREASURE, rand.nextLong());
                }
            }
        }
    }

    public static class AncientPiece extends TemplateStructurePiece {
        public AncientPiece(BlockPos pos, TemplateManager manager) {
            super(BytesFeatures.TEST_STRUCTURE_ANCIENT_ROOM, 1);
            this.templatePosition = pos;
            setup(manager);
        }

        public AncientPiece(TemplateManager manager, CompoundNBT nbt) {
            super(BytesFeatures.TEST_STRUCTURE_ANCIENT_ROOM, nbt);
            setup(manager);
        }

        private void setup(TemplateManager manager) {
            PlacementSettings settings = new PlacementSettings().addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            Template template = manager.getTemplateDefaulted(LOCATION_2);
            setup(template, this.templatePosition, settings);
        }

        @Override
        public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
            PlacementSettings placementsettings = (new PlacementSettings()).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockpos = BlockPos.ZERO;
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, -blockpos.getZ())));
            int i = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);
            boolean flag = super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);

            this.templatePosition = blockpos2;
            return flag;
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if("chest".equals(function)) {
                TileEntity te = worldIn.getTileEntity(pos);
                if(te instanceof ChestTileEntity) {
                    ((ChestTileEntity)te).setLootTable(LootTables.CHESTS_BURIED_TREASURE, rand.nextLong());
                }
            }
        }
    }
}
