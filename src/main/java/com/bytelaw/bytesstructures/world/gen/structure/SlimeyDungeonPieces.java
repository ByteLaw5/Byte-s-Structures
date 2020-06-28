package com.bytelaw.bytesstructures.world.gen.structure;

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

public class SlimeyDungeonPieces {
    private static final ResourceLocation SLIMEY_DUNGEON = resource("slimey_dungeon");
    public static void addStructurePieces(TemplateManager manager, BlockPos origin, Rotation rotation, List<StructurePiece> pieces, Random random) {
        int x = origin.getX();
        int z = origin.getZ();
        BlockPos rotationOffset = BlockPos.ZERO.rotate(rotation);
        BlockPos pos = rotationOffset.add(x, origin.getY(), z);
        pieces.add(Piece.construct(SLIMEY_DUNGEON, pos, manager, rotation));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }

    public static class Piece extends BasePiece {
        private static final IStructurePieceType TYPE = registerType(SLIMEY_DUNGEON, Piece::new);

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
    }
}
