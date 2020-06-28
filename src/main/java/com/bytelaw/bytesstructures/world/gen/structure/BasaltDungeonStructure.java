package com.bytelaw.bytesstructures.world.gen.structure;

import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BasaltDungeonStructure extends BaseStructure<NoFeatureConfig> {
    public BasaltDungeonStructure() {
        super(new Builder<>("basalt_dungeon", NoFeatureConfig.field_236558_a_, Builder.basicInitFactory(BasaltDungeonPieces::addStructurePieces, 40)).separation(8, 12).decorationStage(GenerationStage.Decoration.UNDERGROUND_DECORATION));
        DimensionSettings.Preset.field_236124_d_.func_236137_b_().func_236108_a_().func_236197_a_(this);
    }
}
