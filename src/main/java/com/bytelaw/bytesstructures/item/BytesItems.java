package com.bytelaw.bytesstructures.item;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=BytesStructures.MODID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class BytesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BytesStructures.MODID);

    public static final RegistryObject<Item>
            ANCIENT_BRICKS = ITEMS.register("ancient_bricks", () -> new BlockItem(BytesBlocks.ANCIENT_BRICKS.get(), new Item.Properties().group(BytesStructures.GROUP))),
            ANCIENT_PILLAR = ITEMS.register("ancient_pillar", () -> new BlockItem(BytesBlocks.ANCIENT_PILLAR.get(), new Item.Properties().group(BytesStructures.GROUP))),
            ANCIENT_CHISELED_BRICKS = ITEMS.register("ancient_chiseled_bricks", () -> new BlockItem(BytesBlocks.ANCIENT_CHISELED_BRICKS.get(), new Item.Properties().group(BytesStructures.GROUP))),
            ANCIENT_BRICK_SLAB = ITEMS.register("ancient_brick_slab", () -> new BlockItem(BytesBlocks.ANCIENT_BRICK_SLAB.get(), new Item.Properties().group(BytesStructures.GROUP))),
            GUARD_SPAWN_EGG = ITEMS.register("guard_spawn_egg", () -> new ModdedSpawnEggItem(BytesEntities.GUARD, 0xe1e1e1, 0xbdbdbd, new Item.Properties().group(BytesStructures.GROUP))),
            BLACK_WALNUT_LEAVES = ITEMS.register("black_walnut_leaves", () -> new BlockItem(BytesBlocks.BLACK_WALNUT_LEAVES.get(), new Item.Properties().group(BytesStructures.GROUP)) {
                @Override
                public boolean isIn(Tag<Item> tagIn) {
                    return tagIn == ItemTags.LEAVES;
                }
            }),
            BLACK_WALNUT_LOG = ITEMS.register("black_walnut_log", () -> new BlockItem(BytesBlocks.BLACK_WALNUT_LOG.get(), new Item.Properties().group(BytesStructures.GROUP)) {
                @Override
                public boolean isIn(Tag<Item> tagIn) {
                    return tagIn == ItemTags.LOGS;
                }
            }),
            BLACK_WALNUT_SAPLING = ITEMS.register("black_walnut_sapling", () -> new BlockItem(BytesBlocks.BLACK_WALNUT_SAPLING.get(), new Item.Properties().group(BytesStructures.GROUP))),
            WALNUT = ITEMS.register("walnut", () -> new BlockItem(BytesBlocks.WALNUT.get(), new Item.Properties().group(BytesStructures.GROUP).food(new Food.Builder().hunger(2).fastToEat().saturation(0.5F).build()))),
            BLACK_WALNUT_PLANKS = ITEMS.register("black_walnut_planks", () -> new BlockItem(BytesBlocks.BLACK_WALNUT_PLANKS.get(), new Item.Properties().group(BytesStructures.GROUP)) {
                @Override
                public boolean isIn(Tag<Item> tagIn) {
                    return tagIn == ItemTags.PLANKS;
                }
            }),
            SLIMY_PATH = ITEMS.register("slimy_path", () -> new BlockItem(BytesBlocks.SLIMY_PATH.get(), new Item.Properties().group(BytesStructures.GROUP))),
            PURPUR_DOOR = ITEMS.register("purpur_door", () -> new BlockItem(BytesBlocks.PURPUR_DOOR.get(), new Item.Properties().group(BytesStructures.GROUP)));

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        ComposterBlock.CHANCES.put(BLACK_WALNUT_SAPLING.get(), 0.3F);
    }
}
