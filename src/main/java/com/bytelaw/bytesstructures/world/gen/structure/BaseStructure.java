package com.bytelaw.bytesstructures.world.gen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.List;

public abstract class BaseStructure<C extends IFeatureConfig> extends Structure<C> {
    protected final IStartInitFactory<C> factory;
    protected final GenerationStage.Decoration decoration;
    private final int maxSeparation;
    private final int minSeparation;
    protected final String name;

    public BaseStructure(Builder<C> builder) {
        super(builder.codec);
        this.factory = builder.startFactory;
        this.decoration = builder.decoration;
        this.minSeparation = builder.minSeparation;
        this.maxSeparation = builder.maxSeparation;
        this.name = builder.name;
    }

    @Override
    public ChunkPos func_236392_a_(StructureSeparationSettings p_236392_1_, long p_236392_2_, SharedSeedRandom p_236392_4_, int p_236392_5_, int p_236392_6_) {
        int i = maxSeparation;
        int j = minSeparation;
        int k = Math.floorDiv(p_236392_5_, i);
        int l = Math.floorDiv(p_236392_6_, i);
        p_236392_4_.setLargeFeatureSeedWithSalt(p_236392_2_, k, l, p_236392_1_.func_236673_c_());
        int i1;
        int j1;
        if (this.func_230365_b_()) {
            i1 = p_236392_4_.nextInt(i - j);
            j1 = p_236392_4_.nextInt(i - j);
        } else {
            i1 = (p_236392_4_.nextInt(i - j) + p_236392_4_.nextInt(i - j)) / 2;
            j1 = (p_236392_4_.nextInt(i - j) + p_236392_4_.nextInt(i - j)) / 2;
        }

        return new ChunkPos(k * i + i1, l * i + j1);
    }

    @Override
    public String getStructureName() {
        return name;
    }

    @Override
    public IStartFactory<C> getStartFactory() {
        return Start::new;
    }

    @Override
    public GenerationStage.Decoration func_236396_f_() {
        return decoration;
    }

    private static class Start<C extends IFeatureConfig, T extends BaseStructure<C>> extends StructureStart<C> {
        private final T structure;

        @SuppressWarnings("unchecked")
        public Start(Structure<C> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
            this.structure = (T)p_i225876_1_;
        }

        @Override
        public void func_230364_a_(ChunkGenerator p_230364_1_, TemplateManager p_230364_2_, int p_230364_3_, int p_230364_4_, Biome p_230364_5_, C p_230364_6_) {
            structure.factory.init(p_230364_1_, p_230364_2_, p_230364_3_, p_230364_4_, p_230364_5_, p_230364_6_, this.components, this.rand);
            this.recalculateStructureSize();
        }
    }

    @FunctionalInterface
    public interface IStartInitFactory<C extends IFeatureConfig> {
        void init(ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome, C config, List<StructurePiece> components, SharedSeedRandom random);
    }

    public static class Builder<C extends IFeatureConfig> {
        private GenerationStage.Decoration decoration = GenerationStage.Decoration.SURFACE_STRUCTURES;
        private final Codec<C> codec;
        private final IStartInitFactory<C> startFactory;
        private int minSeparation = 10, maxSeparation = 30;
        private final String name;

        public Builder(String name, Codec<C> configCodec, IStartInitFactory<C> startFactory) {
            this.name = "bytesstructures:" + name;
            this.codec = configCodec;
            this.startFactory = startFactory;
        }

        public Builder<C> decorationStage(GenerationStage.Decoration stage) {
            this.decoration = stage;
            return this;
        }

        //I think this is counted as CHUNKS as doing 700 made it super far
        public Builder<C> separation(int min, int max) {
            this.minSeparation = min;
            this.maxSeparation = max;
            return this;
        }
    }
}
