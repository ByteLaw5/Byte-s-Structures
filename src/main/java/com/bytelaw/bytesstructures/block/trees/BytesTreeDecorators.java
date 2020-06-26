package com.bytelaw.bytesstructures.block.trees;

import com.bytelaw.bytesstructures.BytesStructures;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BytesTreeDecorators {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, BytesStructures.MODID);

    public static final RegistryObject<TreeDecoratorType<WalnutTreeDecorator>> WALNUT = TREE_DECORATORS.register("walnut_tree_decorator", () -> new TreeDecoratorType<>(WalnutTreeDecorator.CODEC));
}
