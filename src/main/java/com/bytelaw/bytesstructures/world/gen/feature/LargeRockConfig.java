package com.bytelaw.bytesstructures.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class LargeRockConfig implements IFeatureConfig {
    public final int initialRadius, decremention, height;
    public final BlockPlacer placer;
    public static final Codec<LargeRockConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("initialRadius").forGetter(config -> config.initialRadius),
            Codec.INT.fieldOf("decremention").forGetter(config -> config.decremention),
            Codec.INT.fieldOf("height").forGetter(config -> config.height),
            BlockPlacer.field_236435_a_.fieldOf("placer").forGetter(config -> config.placer)).apply(instance, LargeRockConfig::new));

    public LargeRockConfig(int initialRadius, int decremention, int height, BlockPlacer placer) {
        this.placer = placer;
        this.initialRadius = initialRadius;
        this.decremention = decremention;
        this.height = height;
    }
}
