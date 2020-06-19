package com.bytelaw.bytesstructures.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
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
    }

    private double getMovementSpeed() {
        return getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(0.5D);
        getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0D);
        getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
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
