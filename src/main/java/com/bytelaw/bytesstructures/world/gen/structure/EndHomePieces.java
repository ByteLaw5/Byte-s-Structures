package com.bytelaw.bytesstructures.world.gen.structure;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

import static com.bytelaw.bytesstructures.BytesStructures.resource;

public class EndHomePieces {
    private static final ResourceLocation END_HOME = resource("end_home");

    public static void addStructurePieces(TemplateManager manager, BlockPos origin, Rotation rotation, List<StructurePiece> pieces, Random random) {
        int x = origin.getX();
        int z = origin.getZ();

        BlockPos rotationOffset = BlockPos.ZERO.rotate(rotation);
        BlockPos pos = rotationOffset.add(x, origin.getY(), z);
        pieces.add(Piece.construct(END_HOME, pos, manager, rotation, LootTables.CHESTS_END_CITY_TREASURE, ((function, pos1, worldIn, rand, sbb, lootTable) -> {
            if("chest".equals(function)) {
                worldIn.setBlockState(pos1, Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, rotation.rotate(Direction.WEST)), 2);
                TileEntity te = worldIn.getTileEntity(pos1);
                if(te instanceof ChestTileEntity && lootTable != null) {
                    ((ChestTileEntity)te).setLootTable(lootTable, rand.nextLong());
                }
            }
            if("craftingtable".equals(function)) {
                worldIn.setBlockState(pos1, Blocks.CRAFTING_TABLE.getDefaultState(), 2);
            }
            if("furnace".equals(function)) {
                worldIn.setBlockState(pos1, Blocks.FURNACE.getDefaultState().with(AbstractFurnaceBlock.FACING, rotation.rotate(Direction.NORTH)), 2);
            }
            if("bed".equals(function)) {
                worldIn.setBlockState(pos1, Blocks.PURPLE_BED.getDefaultState().with(BedBlock.HORIZONTAL_FACING, rotation.rotate(Direction.WEST)), 2);
                worldIn.setBlockState(pos1.north().rotate(rotation), Blocks.PURPLE_BED.getDefaultState().with(BedBlock.HORIZONTAL_FACING, rotation.rotate(Direction.WEST)).with(BedBlock.PART, BedPart.HEAD), 2);
            }
        })));

        pieces.forEach(piece1 -> piece1.buildComponent(piece1, pieces, random));
    }

    public static class Piece extends BasePiece {
        private static final IStructurePieceType TYPE = registerType(END_HOME, Piece::new);

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

        public static Piece construct(ResourceLocation template, BlockPos pos, TemplateManager manager, Rotation rotation, ResourceLocation lootTable, IDataMarkerHandler handler) {
            return new Piece(new Builder(template, pos, manager, TYPE, rotation).setLootTable(lootTable).setDataMarkerHandler(handler).validate());
        }
    }
}
