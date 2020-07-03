package com.bytelaw.bytesstructures.world.gen.structure;

import com.bytelaw.bytesstructures.item.BytesLootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

import static com.bytelaw.bytesstructures.BytesStructures.resource;

public class AncientLootStructurePieces {
    private static final ResourceLocation MAIN_ROOM = resource("ancient_loot_room"),
                                    HALLWAY_X = resource("ancient_hallway_x"),
                                    HALLWAY_Z = resource("ancient_hallway_z"),
                                    ROOM_LEFT = resource("ancient_room_left"),
                                    ROOM_RIGHT = resource("ancient_room_right"),
                                    ROOM_LEFT_DOWN = resource("ancient_room_left_down"),
                                    ROOM_RIGHT_DOWN = resource("ancient_room_right_down");

    public static void addStructurePieces(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random random) {
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffset = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(MAIN_ROOM, blockpos, manager, rotation, BytesLootTables.CHESTS_LOOT));

        rotationOffset = new BlockPos(-9, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(HALLWAY_X, blockpos, manager, rotation));

        rotationOffset = new BlockPos(8, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(HALLWAY_X, blockpos, manager, rotation));

        rotationOffset = new BlockPos(16, 0, -1).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(ROOM_RIGHT, blockpos, manager, rotation));

        rotationOffset = new BlockPos(-15, 0, -1).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(ROOM_LEFT, blockpos, manager, rotation));

        rotationOffset = new BlockPos(-14, 0, 6).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(HALLWAY_Z, blockpos, manager, rotation));

        rotationOffset = new BlockPos(16, 0, 6).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(HALLWAY_Z, blockpos, manager, rotation));

        rotationOffset = new BlockPos(17, 0, 13).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(Piece.construct(ROOM_LEFT_DOWN, blockpos, manager, rotation));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }

    public static class Piece extends BasePiece {
        private static final IStructurePieceType TYPE = registerType(MAIN_ROOM, Piece::new);

        protected Piece(ValidatedBuilder builder) {
            super(builder);
        }

        private Piece(TemplateManager manager, CompoundNBT nbt) {
            super(manager, nbt);
        }

        public static Piece construct(ResourceLocation template, BlockPos pos, TemplateManager manager, Rotation rotation) {
            return new Piece(new Builder(template, pos, manager, TYPE, rotation).validate());
        }

        public static Piece construct(ResourceLocation template, BlockPos pos, TemplateManager manager, Rotation rotation, ResourceLocation lootTable) {
            return new Piece(new Builder(template, pos, manager, TYPE, rotation).setLootTable(lootTable).validate());
        }

        public static Builder constructBuilder(ResourceLocation template, BlockPos pos, TemplateManager manager) {
            return new Builder(template, pos, manager, TYPE);
        }

        public static Builder constructBuilder(ResourceLocation template, BlockPos pos, TemplateManager manager, Rotation rotation) {
            return new Builder(template, pos, manager, TYPE, rotation);
        }
    }
}
