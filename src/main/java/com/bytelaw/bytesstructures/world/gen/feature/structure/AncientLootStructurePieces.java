package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.world.gen.feature.BytesFeatures;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class AncientLootStructurePieces {
    private static final ResourceLocation MAIN_ROOM = BytesStructures.resource("ancient_loot_room"),
                                    HALLWAY = BytesStructures.resource("ancient_hallway"),
                                    ROOM_LEFT = BytesStructures.resource("ancient_room_left"),
                                    ROOM_RIGHT = BytesStructures.resource("ancient_room_right");
    private static final Map<ResourceLocation, BlockPos> OFFSET_MAP = ImmutableMap.of(
            MAIN_ROOM, new BlockPos(0, 1, 0),
            HALLWAY, new BlockPos(0, 1, 0),
            ROOM_LEFT, new BlockPos(0, 1, 0),
            ROOM_RIGHT, new BlockPos(0, 1, 0));

    public static void addStructurePieces(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random random) {
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffset = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, MAIN_ROOM, rotation));

        rotationOffset = new BlockPos(-9, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, HALLWAY, rotation));

        rotationOffset = new BlockPos(8, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, HALLWAY, rotation));

        rotationOffset = new BlockPos(16, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, ROOM_LEFT, rotation));

        rotationOffset = new BlockPos(-16, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, ROOM_RIGHT, rotation));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;
        private final Rotation rotation;

        public Piece(BlockPos pos, TemplateManager manager, ResourceLocation location, Rotation rotation) {
            super(BytesFeatures.ANCIENT_LOOT_ROOM, 0);
            this.templatePosition = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            this.templateLocation = location;
            this.rotation = rotation;
            setup(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(BytesFeatures.ANCIENT_LOOT_ROOM, nbt);
            this.templateLocation = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rotation"));
            setup(manager);
        }

        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.templateLocation.toString());
            tagCompound.putString("Rotation", this.rotation.name());
        }

        private void setup(TemplateManager manager) {
            Template template = manager.getTemplateDefaulted(templateLocation);
            PlacementSettings settings = new PlacementSettings().setRotation(rotation).setMirror(Mirror.NONE).setCenterOffset(BlockPos.ZERO).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            setup(template, this.templatePosition, settings);
        }

        @Override
        public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
            PlacementSettings placementSettings = new PlacementSettings().setRotation(this.rotation).setMirror(Mirror.NONE);
            BlockPos pos = OFFSET_MAP.get(this.templateLocation);
            this.templatePosition.add(Template.transformedBlockPos(placementSettings, new BlockPos(-pos.getX(), 0, -pos.getZ())));
            return super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2);
                TileEntity te = worldIn.getTileEntity(pos);
                if(te instanceof ChestTileEntity) {
                    ((ChestTileEntity)te).setLootTable(LootTables.CHESTS_BURIED_TREASURE, rand.nextLong());
                }
            }
        }
    }
}
