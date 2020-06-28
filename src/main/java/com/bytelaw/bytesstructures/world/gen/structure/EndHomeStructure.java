package com.bytelaw.bytesstructures.world.gen.structure;

import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;

public class EndHomeStructure extends BaseStructure<ChanceConfig> {
    public EndHomeStructure() {
        super(new Builder<>("end_home", ChanceConfig.CODEC, Builder.basicInitFactory(EndHomePieces::addStructurePieces, Builder.chancePredicate(), Heightmap.Type.WORLD_SURFACE_WG)).separation(8, 10).decorationStage(GenerationStage.Decoration.UNDERGROUND_STRUCTURES));
        DimensionSettings.Preset.field_236125_e_.func_236137_b_().func_236108_a_().func_236197_a_(this);
    }
}
