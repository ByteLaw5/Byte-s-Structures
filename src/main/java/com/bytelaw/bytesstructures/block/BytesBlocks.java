package com.bytelaw.bytesstructures.block;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BytesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BytesStructures.MODID);

    public static final RegistryObject<Block>
            ANCIENT_BRICKS = BLOCKS.register("ancient_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.75F, 6.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
            ANCIENT_PILLAR = BLOCKS.register("ancient_pillar", () -> new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.75F, 6.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
            ANCIENT_CHISELED_BRICKS = BLOCKS.register("ancient_chiseled_bricks", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.75F, 6.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
            ANCIENT_BRICK_SLAB = BLOCKS.register("ancient_brick_slab", () -> new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.75F, 6.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE))),
            BLACK_WALNUT_LEAVES = BLOCKS.register("black_walnut_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().sound(SoundType.PLANT).notSolid().hardnessAndResistance(0.2F))),
            BLACK_WALNUT_LOG = BLOCKS.register("black_walnut_log", () -> new LogBlock(MaterialColor.BLACK, Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F).sound(SoundType.WOOD))),
            BLACK_WALNUT_SAPLING = BLOCKS.register("black_walnut_sapling", () -> new BlackWalnutSaplingBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT))),
            WALNUT = BLOCKS.register("walnut", () -> new WalnutBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
}
