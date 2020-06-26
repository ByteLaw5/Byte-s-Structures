package com.bytelaw.bytesstructures.world.gen.feature.structure;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class ChanceConfig implements IFeatureConfig {
    public final int chance;

    public ChanceConfig(int chance) {
        this.chance = chance;
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("chance"), ops.createInt(chance))));
    }

    public static <T> ChanceConfig deserialize(Dynamic<T> dynamic) {
        int chance = dynamic.get("chance").asInt(0);
        return new ChanceConfig(chance);
    }
}
