package net.gabriel.gabruhaddons.events.custom;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.effect.ModEffects;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GabruhAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AiryGemEvents {

    // -----------------------------
    // Cancel dégats de chute, lvl 3
    // -----------------------------
    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        if (player.level().isClientSide) return;

        boolean hasItem =
                player.getMainHandItem().is(ModItems.GABITE_LVL3.get()) ||
                        player.getOffhandItem().is(ModItems.GABITE_LVL3.get());

        if (!hasItem) return;

        if (player.getPersistentData().getBoolean("gabruhaddons_onfall_fall_res")) {

            // annulation de l'évent "chute"
            event.setCanceled(true);
            // reset
            player.getPersistentData().putBoolean("gabruhaddons_onfall_fall_res", false);
        }
    }

    // ----------------------
    // Effet Airborne, lvl 3
    // ----------------------
    @SubscribeEvent
    public static void onHurt2(LivingHurtEvent event) {

        DamageSource source = event.getSource();

        if (event.getEntity().level().isClientSide) return;

        if (!(source.getEntity() instanceof LivingEntity attacker)) return;

        if (!attacker.hasEffect(ModEffects.AIRBORNE_EFFECT.get())) return;

        boolean hasAiryGem =
                attacker.getMainHandItem().is(ModItems.GABITE_LVL3.get()) ||
                        attacker.getOffhandItem().is(ModItems.GABITE_LVL3.get());

        if (!attacker.onGround() && !hasAiryGem) {

            event.setAmount(event.getAmount() * 1.25F); // 25% dégâts bonus AVANT reduction dégâts sans la gemme

            attacker.getPersistentData().putInt("gabruhaddons_propulsion_count", 0); // Reset les sauts lorsque Airborne actif

        } else if (!attacker.onGround() && hasAiryGem) {

            event.setAmount(event.getAmount() * 1.75F); // 75% dégâts bonus AVANT reduction dégâts avec la gemme

            attacker.getPersistentData().putInt("gabruhaddons_propulsion_count", 0); // Reset les sauts lorsque Airborne actif

        }
    }

    // --------------------------------------
    // Aerodynamic au contact de perle, lvl 3
    // --------------------------------------
    @SubscribeEvent
    public static void onPearlTeleport(EntityTeleportEvent.EnderPearl event) {

        if (event.getEntity() instanceof Player player) {

            if (player.level().isClientSide) return;

            boolean offHand  = player.getOffhandItem().is(ModItems.GABITE_LVL3.get());

            for (ItemStack stack : player.getInventory().items) {
                if (stack.is(ModItems.GABITE_LVL3.get()) || offHand) {

                    player.addEffect(new MobEffectInstance(ModEffects.AERODYNAMIC_EFFECT.get(), 100, 0, true, false, true));

                    break;
                }
            }
        }
    }
}
