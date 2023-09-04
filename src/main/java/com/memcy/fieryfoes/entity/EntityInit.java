package com.memcy.fieryfoes.entity;

import com.memcy.fieryfoes.FieryFoes;
import com.memcy.fieryfoes.entity.blaze_runt.BlazeRunt;
import com.memcy.fieryfoes.entity.spore_head.SporeHead;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, FieryFoes.MOD_ID);
    public static final RegistryObject<EntityType<SporeHead>> SPORE_HEAD = register("spore_head", EntityType.Builder.of(SporeHead::new,
            MobCategory.MONSTER).sized(2f, 7f));
    public static final RegistryObject<EntityType<BlazeRunt>> BLAZE_RUNT = register("blaze_runt", EntityType.Builder.of(BlazeRunt::new,
            MobCategory.MONSTER));
    public static final <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder){
        return ENTITIES.register(name, () -> builder.build(FieryFoes.modLoc(name).toString()));
    }
}