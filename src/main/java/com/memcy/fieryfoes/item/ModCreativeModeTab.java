package com.memcy.fieryfoes.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab FIERY_FOES_TAB = new CreativeModeTab("fiery_foes_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SPORE_HEAD_SPAWN_EGG.get());
        }
    };
}