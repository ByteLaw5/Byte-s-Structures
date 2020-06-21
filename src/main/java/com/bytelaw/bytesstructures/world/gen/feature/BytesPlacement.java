package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BytesPlacement {
    public static final DeferredRegister<Placement<?>> PLACEMENTS = DeferredRegister.create(ForgeRegistries.DECORATORS, BytesStructures.MODID);

    public static final RegistryObject<ChancePlacement> CHANCE = PLACEMENTS.register("chance_placement", () -> new ChancePlacement(ChanceConfig::deserialize));
}
