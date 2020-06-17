package com.bytelaw.bytesstructures.item;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.block.BytesBlocks;
import net.minecraft.item.BlockItem;
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
            ANCIENT_BRICK_SLAB = ITEMS.register("ancient_brick_slab", () -> new BlockItem(BytesBlocks.ANCIENT_BRICK_SLAB.get(), new Item.Properties().group(BytesStructures.GROUP)));

    public static void init() {}
}
