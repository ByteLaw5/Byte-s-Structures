package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.world.gen.feature.structure.AncientLootStructure;
import com.bytelaw.bytesstructures.world.gen.feature.structure.AncientLootStructurePieces;
import com.bytelaw.bytesstructures.world.gen.feature.structure.ChanceConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BytesFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BytesStructures.MODID);

    public static final RegistryObject<BlackWalnutTreeFeature> BLACK_WALNUT_TREE = FEATURES.register("black_walnut_tree", () -> new BlackWalnutTreeFeature(BlackWalnutTreeFeatureConfig::deserialize));
    public static final RegistryObject<BlackWalnutBigTreeFeature> BLACK_WALNUT_BIG_TREE = FEATURES.register("black_walnut_big_tree", () -> new BlackWalnutBigTreeFeature(BlackWalnutBigTreeFeatureConfig::deserialize));

    public static final RegistryObject<AncientLootStructure> ANCIENT_LOOT_STRUCTURE = FEATURES.register("ancient_loot", () -> new AncientLootStructure(ChanceConfig::deserialize));

    public static final IStructurePieceType ANCIENT_LOOT_ROOM = registerPiece("ancient_loot_room", AncientLootStructurePieces.Piece::new);

    private static IStructurePieceType registerPiece(String name, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, BytesStructures.resource(name), type);
    }
}