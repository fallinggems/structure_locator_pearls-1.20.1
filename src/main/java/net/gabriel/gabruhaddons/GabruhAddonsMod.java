package net.gabriel.gabruhaddons;

import com.mojang.logging.LogUtils;
import net.gabriel.gabruhaddons.CreativeTab;
import net.gabriel.gabruhaddons.effect.ModEffects;
import net.gabriel.gabruhaddons.entity.ModEntities;
import net.gabriel.gabruhaddons.entity.client.MagmaZoneRenderer;
import net.gabriel.gabruhaddons.events.ModEvents;
import net.gabriel.gabruhaddons.item.ModItems;
import net.gabriel.gabruhaddons.loot.ModLootModifiers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GabruhAddonsMod.MOD_ID)
public class GabruhAddonsMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "gabruhaddons";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public GabruhAddonsMod(FMLJavaModLoadingContext context) {

        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEntities.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        CreativeTab.TABS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.MAGMA_ZONE.get(), MagmaZoneRenderer::new);
            EntityRenderers.register(ModEntities.NETHER_EYE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.BASTION_EYE.get(), ThrownItemRenderer::new);
        }
    }
}
