package com.bytelaw.bytesstructures.item;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.bytelaw.bytesstructures.RegUtil.createRegistry;

public class BytesItems {
    public static final DeferredRegister<Item> ITEMS = createRegistry(ForgeRegistries.ITEMS);
    public static final RegistryObject<Item>
            ANCIENT_BRICKS = ITEMS.register("ancient_bricks", () -> new BlockItem(BytesBlocks.ANCIENT_BRICKS.get(), new Item.Properties().group(BytesStructures.GROUP))),
            ANCIENT_PILLAR = ITEMS.register("ancient_pillar", () -> new BlockItem(BytesBlocks.ANCIENT_PILLAR.get(), new Item.Properties().group(BytesStructures.GROUP))),
            ANCIENT_CHISELED_BRICKS = ITEMS.register("ancient_chiseled_bricks", () -> new BlockItem(BytesBlocks.ANCIENT_CHISELED_BRICKS.get(), new Item.Properties().group(BytesStructures.GROUP))),
            ANCIENT_BRICK_SLAB = ITEMS.register("ancient_brick_slab", () -> new BlockItem(BytesBlocks.ANCIENT_BRICK_SLAB.get(), new Item.Properties().group(BytesStructures.GROUP))),
            GUARD_SPAWN_EGG = ITEMS.register("guard_spawn_egg", () -> new ModdedSpawnEggItem(BytesEntities.GUARD, 0xe1e1e1, 0xbdbdbd, new Item.Properties().group(BytesStructures.GROUP))),
            BLACK_WALNUT_LEAVES = ITEMS.register("black_walnut_leaves", () -> new BlockItem(BytesBlocks.BLACK_WALNUT_LEAVES.get(), new Item.Properties().group(BytesStructures.GROUP))),
            BLACK_WALNUT_LOG = ITEMS.register("black_walnut_log", () -> new BlockItem(BytesBlocks.BLACK_WALNUT_LOG.get(), new Item.Properties().group(BytesStructures.GROUP))),
            BLACK_WALNUT_SAPLING = ITEMS.register("black_walnut_sapling", () -> new BlockItem(BytesBlocks.BLACK_WALNUT_SAPLING.get(), new Item.Properties().group(BytesStructures.GROUP))),
            WALNUT = ITEMS.register("walnut", () -> new BlockItem(BytesBlocks.WALNUT.get(), new Item.Properties().group(BytesStructures.GROUP).food(new Food.Builder().hunger(2).fastToEat().saturation(0.5F).build())));

    public static void init() {}
}
