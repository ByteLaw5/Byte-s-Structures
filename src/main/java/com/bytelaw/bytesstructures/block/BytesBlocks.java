package com.bytelaw.bytesstructures.block;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.bytelaw.bytesstructures.RegUtil.createRegistry;

public class BytesBlocks {
    public static final DeferredRegister<Block> BLOCKS = createRegistry(ForgeRegistries.BLOCKS);

    public static final RegistryObject<Block>
            ANCIENT_BRICKS = BLOCKS.register("ancient_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.75F, 7.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
            ANCIENT_PILLAR = BLOCKS.register("ancient_pillar", () -> new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.75F, 7.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
            ANCIENT_CHISELED_BRICKS = BLOCKS.register("ancient_chiseled_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.75F, 7.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE))),
            ANCIENT_BRICK_SLAB = BLOCKS.register("ancient_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.75F, 7.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

    public static void init() {}
}
