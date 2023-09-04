package com.memcy.fieryfoes;

import com.memcy.fieryfoes.entity.EntityInit;
import com.memcy.fieryfoes.entity.blaze_runt.BlazeRunt;
import com.memcy.fieryfoes.entity.blaze_runt.BlazeRuntModel;
import com.memcy.fieryfoes.entity.spore_head.SporeHead;
import com.memcy.fieryfoes.entity.spore_head.SporeHeadModel;
import com.memcy.fieryfoes.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.memcy.fieryfoes.FieryFoes.MOD_ID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class FieryFoes {
    // Directly reference a slf4j logger
    public static ResourceLocation modLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "fieryfoes";

    public FieryFoes() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::registerEntityAttributes);
        modEventBus.addListener(this::clientSetup);
        GeckoLib.initialize();
        ModItems.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
    }

    private void clientSetup(FMLClientSetupEvent event) {

        EntityRenderers.register(EntityInit.SPORE_HEAD.get(), makeRenderer(new SporeHeadModel()));
        EntityRenderers.register(EntityInit.BLAZE_RUNT.get(), makeRenderer(new BlazeRuntModel()));
    }

    public static <T extends LivingEntity & IAnimatable> EntityRendererProvider<T> makeRenderer(AnimatedGeoModel model) {
        return m -> new HelperGeoRenderer<>(m, model);
    }

    public static class HelperGeoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {
        //example
        public HelperGeoRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel modelProvider) {
            super(renderManager, modelProvider);
        }
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.SPORE_HEAD.get(), SporeHead.createAttributes());
        // HEY
        event.put(EntityInit.BLAZE_RUNT.get(), BlazeRunt.createAttributes());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}

