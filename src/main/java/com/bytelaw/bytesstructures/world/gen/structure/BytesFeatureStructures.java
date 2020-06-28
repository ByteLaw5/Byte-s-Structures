package com.bytelaw.bytesstructures.world.gen.structure;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BytesFeatureStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, BytesStructures.MODID);

    public static final RegistryObject<AncientLootStructure> ANCIENT_LOOT_STRUCTURE = register("ancient_loot", AncientLootStructure::new);
    public static final RegistryObject<SlimeyDungeonStructure> SLIMEY_DUNGEON = register("slimey_dungeon", SlimeyDungeonStructure::new);
    public static final RegistryObject<EndHomeStructure> END_HOME = register("end_home", EndHomeStructure::new);
    public static final RegistryObject<BasaltDungeonStructure> BASALT_DUNGEON = register("basalt_dungeon", BasaltDungeonStructure::new);

    private static <CF extends IFeatureConfig, T extends Structure<CF>> RegistryObject<T> register(String name, Supplier<T> sup) {
        T structure = sup.get();
        Structure.field_236365_a_.put(structure.getStructureName(), structure);
        return STRUCTURES.register(name, sup);
    }
}
