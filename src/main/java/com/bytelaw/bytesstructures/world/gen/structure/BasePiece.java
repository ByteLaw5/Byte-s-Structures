package com.bytelaw.bytesstructures.world.gen.structure;

import com.google.common.base.Preconditions;
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
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Random;

public class BasePiece extends TemplateStructurePiece {
    protected final ResourceLocation templateLocation;
    protected final Rotation rotation;
    private final IStructurePieceType pieceType;
    private static int componentType;
    @Nullable
    private ResourceLocation lootTable;

    public BasePiece(IStructurePieceType type, BlockPos pos, TemplateManager manager, ResourceLocation location, Rotation rotation) {
        super(type, componentType);
        this.pieceType = type;
        componentType++;
        this.templatePosition = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        this.templateLocation = location;
        this.rotation = rotation;
        setup(manager);
    }

    public BasePiece(ValidatedBuilder builder) {
        this(builder.type, builder.origin, builder.manager, builder.template, builder.rotation);
        this.lootTable = builder.lootTable;
    }

    public BasePiece(TemplateManager manager, CompoundNBT nbt) {
        super(Registry.STRUCTURE_PIECE.getOrDefault(new ResourceLocation(nbt.getString("pieceType"))), nbt);
        this.pieceType = Registry.STRUCTURE_PIECE.getOrDefault(new ResourceLocation(nbt.getString("pieceType")));
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
        tagCompound.putString("pieceType", Registry.STRUCTURE_PIECE.getKey(this.pieceType).toString());
        tagCompound.putString("Template", this.templateLocation.toString());
        tagCompound.putString("Rotation", this.rotation.name());
    }

    @Override
    public boolean func_230383_a_(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
        PlacementSettings placementSettings = new PlacementSettings().setRotation(this.rotation).setMirror(Mirror.NONE);
        BlockPos pos = new BlockPos(0, 1, 0);
        this.templatePosition.add(Template.transformedBlockPos(placementSettings, new BlockPos(-pos.getX(), 0, -pos.getZ())));
        return super.func_230383_a_(p_230383_1_, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        if("chest".equals(function)) {
            worldIn.setBlockState(pos, Blocks.CHEST.getDefaultState(), 2);
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof ChestTileEntity && lootTable != null) {
                ((ChestTileEntity)te).setLootTable(lootTable, rand.nextLong());
            }
        }
    }

    protected static IStructurePieceType registerType(ResourceLocation location, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, location, type);
    }

    public static class Builder {
        private final BlockPos origin;
        private final TemplateManager manager;
        private final IStructurePieceType type;
        private final ResourceLocation template;
        private Rotation rotation = Rotation.NONE;
        private ResourceLocation lootTable;

        public Builder(ResourceLocation template, BlockPos pos, TemplateManager manager, IStructurePieceType type) {
            this.template = template;
            this.manager = manager;
            this.origin = pos;
            this.type = type;
        }

        public Builder(ResourceLocation template, BlockPos pos, TemplateManager manager, IStructurePieceType type, Rotation rotation) {
            this(template, pos, manager, type);
            this.rotation = rotation;
        }

        public Builder setLootTable(ResourceLocation lootTable) {
            this.lootTable = lootTable;
            return this;
        }

        public ValidatedBuilder validate() {
            Preconditions.checkNotNull(type);
            Preconditions.checkNotNull(origin);
            Preconditions.checkNotNull(template);
            Preconditions.checkNotNull(manager);
            Preconditions.checkNotNull(rotation);
            return new ValidatedBuilder(this);
        }
    }

    public static class ValidatedBuilder {
        private final BlockPos origin;
        private final TemplateManager manager;
        private final IStructurePieceType type;
        private final ResourceLocation template;
        private final Rotation rotation;
        private final ResourceLocation lootTable;

        public ValidatedBuilder(Builder builder) {
            this.origin = builder.origin;
            this.manager = builder.manager;
            this.type = builder.type;
            this.template = builder.template;
            this.rotation = builder.rotation;
            this.lootTable = builder.lootTable;
        }
    }
}
