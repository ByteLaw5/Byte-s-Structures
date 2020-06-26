package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BytesFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BytesStructures.MODID);

//    public static final RegistryObject<BlackWalnutTreeFeature> BLACK_WALNUT_TREE = FEATURES.register("black_walnut_tree", () -> new BlackWalnutTreeFeature(BlackWalnutTreeFeatureConfig::deserialize));
//    public static final RegistryObject<BlackWalnutBigTreeFeature> BLACK_WALNUT_BIG_TREE = FEATURES.register("black_walnut_big_tree", () -> new BlackWalnutBigTreeFeature(BlackWalnutBigTreeFeatureConfig::deserialize));
}