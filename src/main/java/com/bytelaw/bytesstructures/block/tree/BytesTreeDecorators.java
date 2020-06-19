package com.bytelaw.bytesstructures.block.tree;

import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.bytelaw.bytesstructures.RegUtil.createRegistry;

public class BytesTreeDecorators {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = createRegistry(ForgeRegistries.TREE_DECORATOR_TYPES);

    public static final RegistryObject<TreeDecoratorType<WalnutTreeDecorator>> WALNUT_TREE_DECORATOR = TREE_DECORATORS.register("walnut_tree_decorator", () -> new TreeDecoratorType<>(WalnutTreeDecorator::new));

    public static void init() {}
}