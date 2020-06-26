package com.bytelaw.bytesstructures.world.gen.feature.structure;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BytesFeatureStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, com.bytelaw.bytesstructures.BytesStructures.MODID);

    public static final RegistryObject<AncientLootStructure> ANCIENT_LOOT_STRUCTURE = STRUCTURES.register("ancient_loot", AncientLootStructure::new);
    public static final RegistryObject<SlimeyDungeonStructure> SLIMEY_DUNGEON = STRUCTURES.register("slimey_dungeon", SlimeyDungeonStructure::new);
    public static final RegistryObject<EndHomeStructure> END_HOME = STRUCTURES.register("end_home", EndHomeStructure::new);

    public static final IStructurePieceType ANCIENT_LOOT_ROOM = registerPiece("ancient_loot_room", AncientLootStructurePieces.Piece::new);
    public static final IStructurePieceType SLIMEY_DUNGEON_PIECE = registerPiece("slimey_dungeon", SlimeyDungeonPieces.Piece::new);
    public static final IStructurePieceType END_HOME_PIECE = registerPiece("end_home", EndHomePieces.Piece::new);

    private static IStructurePieceType registerPiece(String name, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, com.bytelaw.bytesstructures.BytesStructures.resource(name), type);
    }
}
