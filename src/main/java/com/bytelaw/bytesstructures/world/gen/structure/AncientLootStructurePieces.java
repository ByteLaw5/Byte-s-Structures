package com.bytelaw.bytesstructures.world.gen.structure;

import com.bytelaw.bytesstructures.item.BytesLootTables;
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
                                    ROOM_RIGHT = resource("ancient_room_right");

    public static void addStructurePieces(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random random) {
        int x = pos.getX();
        int z = pos.getZ();
        IStructurePieceType type = BasePiece.Builder.constructType(MAIN_ROOM);

        BlockPos rotationOffset = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(BasePiece.construct(MAIN_ROOM, blockpos, manager, type, rotation, BytesLootTables.CHESTS_ANCIENT_LOOT));

        rotationOffset = new BlockPos(-9, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(BasePiece.construct(HALLWAY_X, blockpos, manager, type, rotation));

        rotationOffset = new BlockPos(8, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(BasePiece.construct(HALLWAY_X, blockpos, manager, type, rotation));

        rotationOffset = new BlockPos(16, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(BasePiece.construct(ROOM_RIGHT, blockpos, manager, type, rotation));

        rotationOffset = new BlockPos(-16, 0, 0).rotate(rotation);
        blockpos = rotationOffset.add(x, pos.getY(), z);
        pieces.add(BasePiece.construct(ROOM_LEFT, blockpos, manager, type, rotation));

        pieces.forEach(piece -> piece.buildComponent(piece, pieces, random));
    }
}
