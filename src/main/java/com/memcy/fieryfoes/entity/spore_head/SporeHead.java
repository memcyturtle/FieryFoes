package com.memcy.fieryfoes.entity.spore_head;

import com.memcy.fieryfoes.entity.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SporeHead extends Monster implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public SporeHead(EntityType<SporeHead> type, Level world) {
        super(type, world);
    }

    protected SporeHead(Level world) {
        this(EntityInit.SPORE_HEAD.get(), world);
    }

    public static final AttributeSupplier createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.23d)
                .add(Attributes.ATTACK_DAMAGE, 9)
                .add(Attributes.ARMOR, 4)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1).build();

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6f, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));

        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 10f));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.5d));

    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spore_thrower.walk2", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spore_thrower.idle", true));
        return PlayState.CONTINUE;
    }
    private PlayState attackPredicate(AnimationEvent<SporeHead> event) {
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spore_thrower.spore_throw", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }
    @Override
  public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "attackController",
                0, this::attackPredicate));
        data.addAnimationController(new AnimationController<>(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_STRAY_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.DOLPHIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

}