package com.bytelaw.bytesstructures.item;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.util.ResourceLocation;

public class BytesLootTables {
    public static final ResourceLocation CHESTS_LOOT = loc("chests/ancient_loot");

    private static ResourceLocation loc(String path) {
        return BytesStructures.resource(path);
    }
}
