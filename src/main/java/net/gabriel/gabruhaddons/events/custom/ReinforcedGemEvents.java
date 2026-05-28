package net.gabriel.gabruhaddons.events.custom;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GabruhAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ReinforcedGemEvents {

    // -----------------------
    // Invulnérabilité, lvl 3
    // -----------------------
    @SubscribeEvent
    public static void onInvulnerability(LivingHurtEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        int hits = player.getPersistentData().getInt("gabruhaddons_invulnerable_hits");

        if (hits <= 0) return;

        hits--;

        if (hits <= 0) {
            player.getPersistentData().remove("gabruhaddons_invulnerable_hits");
        } else {
            player.getPersistentData().putInt("gabruhaddons_invulnerable_hits", hits);
        }

        event.setAmount(0);
        player.setHealth(Math.max(player.getHealth(), 1.0F));

        player.level().playSound(
                null,
                player.blockPosition(),
                SoundEvents.SHIELD_BLOCK,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

    // ----------------------
    // Trompe la mort, lvl 3
    // ----------------------
    @SubscribeEvent
    public static void luckyDeath(LivingHurtEvent event) {

        // voir si je fais pas LivingDeathEvent plutot, ce que paraitrait plus logique

        if (!(event.getEntity() instanceof Player player)) return;

        if (player.level().isClientSide) return;

        if (player.getMainHandItem().is(Items.TOTEM_OF_UNDYING)
                || player.getOffhandItem().is(Items.TOTEM_OF_UNDYING)) {
            return;
        }

        boolean hasItem = false;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.MATHITE_LVL3.get())) {
                hasItem = true;
                break;
            }
        }

        if (!hasItem) return;

        float finalHealth = player.getHealth() - event.getAmount();

        if (finalHealth > 0) return;

        if (player.getRandom().nextFloat() > 0.33f) return;

        event.setAmount(0);
        player.setHealth(1.0F);
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0));

        player.level().playSound(
                null,
                player.blockPosition(),
                SoundEvents.TOTEM_USE,
                SoundSource.PLAYERS,
                1.0F,
                0.8F
        );
    }

    // -------------------------
    // Chance double exp, lvl 3
    // -------------------------
    @SubscribeEvent
    public static void reinforcedXpMultiplier(PlayerXpEvent.XpChange event) {

        if (event.getEntity().level().isClientSide) {
            return;
        }

        Player player = event.getEntity();

        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.MATHITE_LVL3.get())) {

                if (player.getRandom().nextFloat() < 0.33f) {
                    event.setAmount(event.getAmount() * 2);
                } else if (player.getRandom().nextFloat() < 0.66f) {
                    event.setAmount(event.getAmount() * 3);
                }

                return;
            }
        }
    }

    // ----------------------
    // Cap +2 Looting, lvl 3
    // ----------------------
    @SubscribeEvent
    public static void lootingCap(LootingLevelEvent event) {

        if (event.getEntity().level().isClientSide) {
            return;
        }

        DamageSource source = event.getDamageSource();

        if (!(source.getEntity() instanceof Player player)) {
            return;
        }

        if (event.getLootingLevel() <= 0) {
            return;
        }

        for (ItemStack stack : player.getInventory().items) {

            if (stack.is(ModItems.MATHITE_LVL3.get())) {

                event.setLootingLevel(event.getLootingLevel() + 2);
                return;
            }
        }

    }
}
