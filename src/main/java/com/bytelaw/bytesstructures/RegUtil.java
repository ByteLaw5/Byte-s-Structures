package com.bytelaw.bytesstructures;

import com.google.common.collect.Lists;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.bytelaw.bytesstructures.BytesStructures.MODID;

public class RegUtil {
    private static final Logger LOGGER = LogManager.getLogger("BytesRegistry");
    static final List<DeferredRegister<?>> DEFERRED_REGISTERS = Lists.newArrayList();

    public static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> createRegistry(IForgeRegistry<T> registry) {
        DeferredRegister<T> deferred =  DeferredRegister.create(registry, MODID);
        DEFERRED_REGISTERS.add(deferred);
        LOGGER.debug("Created registry for type {}", registry.getRegistrySuperType().getSimpleName());
        return deferred;
    }
}
