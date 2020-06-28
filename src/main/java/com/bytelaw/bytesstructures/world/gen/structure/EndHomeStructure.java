package com.bytelaw.bytesstructures.world.gen.structure;

public class EndHomeStructure extends BaseStructure<ChanceConfig> {
    public EndHomeStructure() {
        super(new Builder<>("end_home", ChanceConfig.CODEC, Builder.basicInitFactory(EndHomePieces::addStructurePieces)).separation(20, 70));
    }
}
