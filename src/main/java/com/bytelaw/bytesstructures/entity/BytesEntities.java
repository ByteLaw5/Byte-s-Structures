package com.bytelaw.bytesstructures.entity;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.item.ModdedSpawnEggItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.bytelaw.bytesstructures.RegUtil.createRegistry;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BytesEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = createRegistry(ForgeRegistries.ENTITIES);

    public static final RegistryObject<EntityType<GuardEntity>> GUARD = register("guard", EntityType.Builder.create(GuardEntity::new, EntityClassification.CREATURE));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(BytesStructures.resource(name).toString()));
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void onEntityRegisterEvent(RegistryEvent.Register<EntityType<?>> event) {
        ModdedSpawnEggItem.initUnaddedEggs();
    }

    public static void init() {}
}
