package com.bytelaw.bytesstructures.client;

import com.bytelaw.bytesstructures.block.BytesBlocks;
import com.bytelaw.bytesstructures.entity.BytesEntities;
import com.bytelaw.bytesstructures.item.BytesItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class Client {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state, lightReader, pos, i) -> FoliageColors.get(0.5D, 1.0D), BytesBlocks.BLACK_WALNUT_LEAVES.get());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, i) -> event.getBlockColors().getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, i), BytesItems.BLACK_WALNUT_LEAVES.get());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void client(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(BytesEntities.GUARD.get(), GuardRenderer::new);
        RenderTypeLookup.setRenderLayer(BytesBlocks.BLACK_WALNUT_LEAVES.get(), RenderType.getCutoutMipped());
//        RenderTypeLookup.setRenderLayer(BytesBlocks.BLACK_WALNUT_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BytesBlocks.WALNUT.get(), RenderType.getCutout());
    }
}
