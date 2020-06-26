package com.bytelaw.bytesstructures.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GuardEntity extends CreatureEntity {
    public GuardEntity(EntityType<? extends GuardEntity> type, World worldIn) {
        super(type, worldIn);
        this.experienceValue = 25;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, getMovementSpeed(), true));
        goalSelector.addGoal(5, new LookRandomlyGoal(this));
        goalSelector.addGoal(6, new RandomWalkingGoal(this, getMovementSpeed(), 10));
        goalSelector.addGoal(7, new LookAtGoal(this, LivingEntity.class, 10.0F, 0.1F));
        targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(GuardEntity.class));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
        targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, SlimeEntity.class, true));
        targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, GolemEntity.class, 15, true, false, (entity) -> !(entity instanceof IronGolemEntity)));
    }

    private double getMovementSpeed() {
        return getAttribute(Attributes.field_233821_d_).getValue();
    }

    public static AttributeModifierMap getAttributes() {
        return MobEntity.func_233666_p_()
                .func_233815_a_(Attributes.field_233818_a_, 30.0D) //Health
                .func_233815_a_(Attributes.field_233819_b_, 50.0D) //Follow Range
                .func_233815_a_(Attributes.field_233823_f_, 5.0D) //Attack Damage
                .func_233815_a_(Attributes.field_233821_d_, 0.635D) //Movement Speed
                .func_233815_a_(Attributes.field_233826_i_, 7.0D) //Armor
                .func_233815_a_(Attributes.field_233827_j_, 1.0D) //Armor Toughness
                .func_233815_a_(Attributes.field_233825_h_, 1.0D) //Attack Speed
                .func_233813_a_();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }
}
