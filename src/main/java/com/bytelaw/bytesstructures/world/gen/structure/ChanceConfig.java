package com.bytelaw.bytesstructures.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class ChanceConfig implements IFeatureConfig {
    public final int chance;
    public static final Codec<ChanceConfig> CONFIG = RecordCodecBuilder.create((instance) -> instance.group(Codec.INT.fieldOf("chance").forGetter((config) -> config.chance)).apply(instance, ChanceConfig::new));

    public ChanceConfig(int chance) {
        this.chance = chance;
    }

}