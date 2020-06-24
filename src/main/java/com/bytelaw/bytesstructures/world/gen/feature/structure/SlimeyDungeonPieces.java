package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.bytelaw.bytesstructures.world.gen.feature.BytesFeatures;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundNBT;
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

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.bytelaw.bytesstructures.BytesStructures.resource;

public class SlimeyDungeonPieces {
    private static final ResourceLocation SLIMEY_DUNGEON = resource("slimey_dungeon");
    private static final Map<ResourceLocation, BlockPos> OFFSET_MAP = ImmutableMap.of(SLIMEY_DUNGEON, new BlockPos(0, 1, 0));

    public static void addStructurePieces(TemplateManager manager, BlockPos origin, Rotation rotation, List<StructurePiece> pieces, Random random) {
        int x = origin.getX();
        int z = origin.getZ();

        BlockPos rotationOffset = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos pos = rotationOffset.add(x, origin.getY(), z);
        pieces.add(new Piece(pos, manager, SLIMEY_DUNGEON, rotation));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation templateLocation;
        private final Rotation rotation;

        public Piece(BlockPos pos, TemplateManager manager, ResourceLocation location, Rotation rotation) {
            super(BytesFeatures.SLIMEY_DUNGEON_PIECE, 0);
            this.templatePosition = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            this.templateLocation = location;
            this.rotation = rotation;
            setup(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(BytesFeatures.SLIMEY_DUNGEON_PIECE, nbt);
            this.templateLocation = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rotation"));
            setup(manager);
        }

        private void setup(TemplateManager manager) {
            Template template = manager.getTemplateDefaulted(templateLocation);
            PlacementSettings settings = new PlacementSettings().setRotation(rotation).setMirror(Mirror.NONE).setCenterOffset(BlockPos.ZERO).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            setup(template, this.templatePosition, settings);
        }

        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.templateLocation.toString());
            tagCompound.putString("Rotation", this.rotation.name());
        }

        @Override
        public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
            PlacementSettings placementSettings = new PlacementSettings().setRotation(this.rotation).setMirror(Mirror.NONE);
            BlockPos pos = OFFSET_MAP.get(this.templateLocation);
            this.templatePosition.add(Template.transformedBlockPos(placementSettings, new BlockPos(-pos.getX(), 0, -pos.getZ())));
            return super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {}
    }
}
