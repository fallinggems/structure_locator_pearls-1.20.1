package net.gabriel.gabruhaddons.events.custom;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.item.ModItems;
import net.gabriel.gabruhaddons.util.DelayedActivation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GabruhAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TickEvents {

    // IMPORTANT!! REGROUPER POUR INVENTORY TICK CAR LOURD
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;

        if (player.level().isClientSide) return;

        // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        //                               EVENTS ICI :
        // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

        // ------------------------------------
        // Annulation Fall Damage (QUE Airy Gem II)
        // ------------------------------------
        int AG2timer = player.getPersistentData().getInt("gabruhaddons_fall_res");

        // immunité lorsque "fall res timer" actif via Item Class
        if (AG2timer > 0) {

            player.fallDistance = 0;

            if (player.onGround()) {
                player.getPersistentData().putInt("gabruhaddons_fall_res", 0);
            } else {
                player.getPersistentData().putInt("gabruhaddons_fall_res", AG2timer -1);
            }
        }

        // -------------------------------------------------
        // Propulsion Count & =/= Fall Damage (Airy Gem III)
        // -------------------------------------------------

        // boolean item main droite/gauche
        boolean AG3hasItem =
                player.getMainHandItem().is(ModItems.GABITE_LVL3.get()) ||
                        player.getOffhandItem().is(ModItems.GABITE_LVL3.get());

        // reset jump count quand au sol avec item
        if (AG3hasItem) {
            if (player.onGround()) {
                player.getPersistentData().putInt("gabruhaddons_propulsion_count", 0);
            }
        }

        // clear "fall res" quand pas l'item
        if (!AG3hasItem) {
            player.getPersistentData().putBoolean("gabruhaddons_onfall_fall_res", false);
        }
    }

    // ------------------------
    // DELAYED ACTIVATION CLASS
    // ------------------------
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            DelayedActivation.tick();
        }
    }
}
