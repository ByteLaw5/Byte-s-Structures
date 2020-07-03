package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BytesFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BytesStructures.MODID);

    public static final RegistryObject<TreeFeature> BLACK_WALNUT_TREE = FEATURES.register("black_walnut_tree", () -> new TreeFeature(BlackWalnutTreeConfig.field_236676_a_));
    public static final RegistryObject<TreeFeature> BLACK_WALNUT_BIG_TREE = FEATURES.register("black_walnut_big_tree", () -> new TreeFeature(BlackWalnutTreeConfig.field_236676_a_));
    public static final RegistryObject<LargeRockFeature> LARGE_ROCK = FEATURES.register("large_rock", LargeRockFeature::new);
}