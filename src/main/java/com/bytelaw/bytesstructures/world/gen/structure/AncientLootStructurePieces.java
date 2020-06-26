package com.bytelaw.bytesstructures.world.gen.structure;

import com.bytelaw.bytesstructures.item.BytesLootTables;
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
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.bytelaw.bytesstructures.BytesStructures.resource;

public class AncientLootStructurePieces {
    private static final ResourceLocation MAIN_ROOM = resource("ancient_loot_room"),
                                    HALLWAY_X = resource("ancient_hallway_x"),
                                    HALLWAY_Z = resource("ancient_hallway_z"),
                                    ROOM_LEFT = resource("ancient_room_left"),
                                    ROOM_RIGHT = resource("ancient_room_right");
    private static final Map<ResourceLocation, BlockPos> OFFSET_MAP = ImmutableMap.of(
            MAIN_ROOM, new BlockPos(0, 1, 0),
            HALLWAY_X, new BlockPos(0, 1, 0),
            HALLWAY_Z, new BlockPos(0, 1, 0),
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
        pieces.add(new Piece(blockpos, manager, HALLWAY_X, rotation));

        rotationOffset = new BlockPos(8, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, HALLWAY_X, rotation));

        rotationOffset = new BlockPos(16, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, ROOM_RIGHT, rotation));

        rotationOffset = new BlockPos(-16, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(new Piece(blockpos, manager, ROOM_LEFT, rotation));

//        rotationOffset = new BlockPos(-16, 0, -16).rotate(rotation);
//        blockpos = rotationOffset.add(x, pos.getY(), z);
//        pieces.add(new Piece(blockpos, manager, HALLWAY_Z, rotation));
//
//        rotationOffset = new BlockPos(16, 0, 16).rotate(rotation);
//        blockpos = rotationOffset.add(x, pos.getY(), z);
//        pieces.add(new Piece(blockpos, manager, HALLWAY_Z, rotation));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;
        private final Rotation rotation;

        public Piece(BlockPos pos, TemplateManager manager, ResourceLocation location, Rotation rotation) {
            super(BytesFeatureStructures.ANCIENT_LOOT_ROOM, 0);
            this.templatePosition = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            this.templateLocation = location;
            this.rotation = rotation;
            setup(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(BytesFeatureStructures.ANCIENT_LOOT_ROOM, nbt);
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
        public boolean func_230383_a_(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
            PlacementSettings placementSettings = new PlacementSettings().setRotation(this.rotation).setMirror(Mirror.NONE);
            BlockPos pos = OFFSET_MAP.get(this.templateLocation);
            this.templatePosition.add(Template.transformedBlockPos(placementSettings, new BlockPos(-pos.getX(), 0, -pos.getZ())));
            return super.func_230383_a_(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if("chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2);
                TileEntity te = worldIn.getTileEntity(pos);
                if(te instanceof ChestTileEntity) {
                    ((ChestTileEntity)te).setLootTable(BytesLootTables.CHESTS_ANCIENT_LOOT, rand.nextLong());
                }
            }
        }
    }
}
