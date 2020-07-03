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

public class MeteorImpactPieces {
    private static final ResourceLocation IMPACT = resource("meteor_impact");

    public static void addStructurePieces(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random random) {
        pieces.add(Piece.construct(IMPACT, pos, manager));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }

    public static class Piece extends BasePiece {
        private static final IStructurePieceType TYPE = registerType(IMPACT, Piece::new);

        protected Piece(ValidatedBuilder builder) {
            super(builder);
        }

        private Piece(TemplateManager manager, CompoundNBT nbt) {
            super(manager, nbt);
        }

        public static Piece construct(ResourceLocation template, BlockPos pos, TemplateManager manager) {
            return new Piece(new Builder(template, pos, manager, TYPE).validate());
        }
    }
}
