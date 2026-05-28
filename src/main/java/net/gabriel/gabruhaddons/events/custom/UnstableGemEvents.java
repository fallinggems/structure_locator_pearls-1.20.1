package net.gabriel.gabruhaddons.events.custom;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GabruhAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UnstableGemEvents {

    // -------------------------
    // Explosion de Mort, lvl 2
    // -------------------------
    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {

        if (event.getEntity() instanceof Player player) {

            if (player.level().isClientSide) return;

            boolean mainHand = player.getMainHandItem().is(ModItems.ARMANITE_LVL2.get());
            boolean offHand  = player.getOffhandItem().is(ModItems.ARMANITE_LVL2.get());

            if (mainHand || offHand) {
                // explosion à la mort (comme si c'était la classe de la unstable gem II)

                player.level().explode(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        8.0F,
                        Level.ExplosionInteraction.NONE
                );
            }
        }
    }
}
