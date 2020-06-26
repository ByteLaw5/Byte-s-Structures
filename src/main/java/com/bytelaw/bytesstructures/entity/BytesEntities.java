package com.bytelaw.bytesstructures.entity;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.item.ModdedSpawnEggItem;
import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Map;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BytesEntities {
    public static final Map<EntityType<? extends LivingEntity>, AttributeModifierMap> ATTRIBUTES = Maps.newHashMap();
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, BytesStructures.MODID);

    public static final RegistryObject<EntityType<GuardEntity>> GUARD = register("guard", GuardEntity::new, EntityClassification.CREATURE, GuardEntity.getAttributes());

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.IFactory<T> factory, EntityClassification classification, @Nullable AttributeModifierMap attributes) {
        EntityType.Builder<T> builder = EntityType.Builder.create(factory, classification);
        return register(name, builder, attributes);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder, @Nullable AttributeModifierMap attributes) {
        EntityType<T> ret = builder.build(BytesStructures.resource(name).toString());
        if(attributes != null) {//This means that the entity is a living entity
            ATTRIBUTES.put((EntityType<? extends LivingEntity>)ret, attributes);
        }
        return ENTITIES.register(name, () -> ret);
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void onEntityRegisterEvent(RegistryEvent.Register<EntityType<?>> event) {
        ModdedSpawnEggItem.initUnaddedEggs();
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void onAttributesRegisterEvent(RegistryEvent.Register<Attribute> event) {
        BytesEntities.ATTRIBUTES.forEach(GlobalEntityTypeAttributes::put);
    }
}
