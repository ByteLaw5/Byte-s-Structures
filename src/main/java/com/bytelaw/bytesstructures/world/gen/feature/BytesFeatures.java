package com.bytelaw.bytesstructures.world.gen.feature;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.world.gen.feature.structure.*;
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

    public static final RegistryObject<AncientLootStructure> ANCIENT_LOOT_STRUCTURE = FEATURES.register("ancient_loot", AncientLootStructure::new);
    public static final RegistryObject<SlimeyDungeonStructure> SLIMEY_DUNGEON = FEATURES.register("slimey_dungeon", SlimeyDungeonStructure::new);
    public static final RegistryObject<EndHomeStructure> END_HOME = FEATURES.register("end_home", EndHomeStructure::new);

    public static final IStructurePieceType ANCIENT_LOOT_ROOM = registerPiece("ancient_loot_room", AncientLootStructurePieces.Piece::new);
    public static final IStructurePieceType SLIMEY_DUNGEON_PIECE = registerPiece("slimey_dungeon", SlimeyDungeonPieces.Piece::new);
    public static final IStructurePieceType END_HOME_PIECE = registerPiece("end_home", EndHomePieces.Piece::new);

    private static IStructurePieceType registerPiece(String name, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, BytesStructures.resource(name), type);
    }
}