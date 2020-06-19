package com.bytelaw.bytesstructures.feature;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.feature.structure.TestStructure;
import com.bytelaw.bytesstructures.feature.structure.TestStructurePieces;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.bytelaw.bytesstructures.RegUtil.createRegistry;

public class BytesFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = createRegistry(ForgeRegistries.FEATURES);

    public static final RegistryObject<TestStructure> TEST_STRUCTURE = FEATURES.register("test_structure", () -> new TestStructure(ProbabilityConfig::deserialize));

    public static final RegistryObject<BlackWalnutTreeFeature> BLACK_WALNUT_TREE = FEATURES.register("black_walnut_tree", () -> new BlackWalnutTreeFeature(BlackWalnutTreeFeatureConfig::deserialize));

    public static final IStructurePieceType TEST_STRUCTURE_ROOM = registerPiece("teststructureroom", TestStructurePieces.Piece::new);
    public static final IStructurePieceType TEST_STRUCTURE_ANCIENT_ROOM = registerPiece("teststructureroom2", TestStructurePieces.AncientPiece::new);

    private static IStructurePieceType registerPiece(String name, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, BytesStructures.resource(name), type);
    }

    public static void init() {}
}