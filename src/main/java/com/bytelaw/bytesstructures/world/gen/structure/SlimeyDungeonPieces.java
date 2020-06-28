package com.bytelaw.bytesstructures.world.gen.structure;

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
        IStructurePieceType type = BasePiece.Builder.constructType(SLIMEY_DUNGEON);

        BlockPos rotationOffset = BlockPos.ZERO.rotate(rotation);
        BlockPos pos = rotationOffset.add(x, origin.getY(), z);
        pieces.add(BasePiece.construct(SLIMEY_DUNGEON, pos, manager, type, rotation));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }
}
