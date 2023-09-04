package com.memcy.fieryfoes.item;

import com.memcy.fieryfoes.FieryFoes;
import com.memcy.fieryfoes.entity.EntityInit;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FieryFoes.MOD_ID);

    public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, FieryFoes.MOD_ID);


    public static final RegistryObject<Item> SPORE_HEAD_SPAWN_EGG = ITEMS.register("spore_head_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SPORE_HEAD, 0x948e8d, 0x3b3635,
                    new Item.Properties().tab(ModCreativeModeTab.FIERY_FOES_TAB)));

    public static final RegistryObject<Item> BLAZE_RUNT_SPAWN_EGG = ITEMS.register("blaze_runt_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.BLAZE_RUNT, 0x948e8d, 0x3b3635,
                    new Item.Properties().tab(ModCreativeModeTab.FIERY_FOES_TAB)));

// didnt i taught you how to do that. Are you being serious. I told you, go to the file explorer,
// look for where you kept it, hen cop the json by ctrl and then go to the intellij and paste it, and ez


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        SPAWN_EGGS.register(eventBus);
    }
}