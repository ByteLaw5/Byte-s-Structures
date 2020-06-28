package com.bytelaw.bytesstructures.world.gen.structure;

public class SlimeyDungeonStructure extends BaseStructure<ChanceConfig> {
    public SlimeyDungeonStructure() {
        super(new Builder<>("slimey_dungeon", ChanceConfig.CODEC, Builder.basicInitFactory(SlimeyDungeonPieces::addStructurePieces, Builder.chancePredicate())).separation(15, 35));
    }
}
