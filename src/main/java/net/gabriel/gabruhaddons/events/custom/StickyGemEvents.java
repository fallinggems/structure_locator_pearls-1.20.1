package net.gabriel.gabruhaddons.events.custom;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.effect.ModEffects;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GabruhAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StickyGemEvents {

    // -----------------------------
    // Toucher de Levitation, lvl 2
    // -----------------------------
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {

        DamageSource source = event.getSource();

        if (event.getEntity().level().isClientSide) return;

        if (source.getEntity() instanceof LivingEntity attacker) {

            if (attacker.hasEffect(ModEffects.LEVITATION_TOUCH_EFFECT.get())) {
                // effet de la Levitation Touch (comme si ce serait sa propre classe)

                // vocabulaire pour avoir attacker et target
                LivingEntity target = event.getEntity();

                // UN JOUR faire un amplifier de lvl de status effect (inspiré de fabric et gpt)

                // vec mouvement target de base
                Vec3 initVec = target.getDeltaMovement();

                // direction attacker -> target, enlevé -> pas besoin
                Vec3 direction = new Vec3(
                        target.getX() - attacker.getX(),
                        0,
                        target.getZ() - attacker.getZ()).normalize();

                // def et paramètres vec "propulsion"
                Vec3 motion = new Vec3(0, Math.max(initVec.y, 0.9), 0);

                // IMPORTANT pour que Y marche au sol
                target.setOnGround(false);

                // commande mouvement "propulsion"
                target.setDeltaMovement(motion);

                // verifications par sécurité et sync
                target.hurtMarked = true;
                target.hasImpulse = true;

                // son joué sur target
                target.level().playSound(
                        null,
                        target.getX(),
                        target.getY(),
                        target.getZ(),
                        SoundEvents.SHULKER_SHOOT,
                        SoundSource.PLAYERS,
                        0.7F,
                        1.5F);
            }
        }
    }

    // -------------------------
    // Toucher de poison, lvl 3
    // -------------------------
    @SubscribeEvent
    public static void onPoisonAspect(LivingHurtEvent event) {

        DamageSource source = event.getSource();

        if (event.getEntity().level().isClientSide) return;

        if (source.getEntity() instanceof LivingEntity attacker) {

            if (attacker.hasEffect(ModEffects.POISON_TOUCH_EFFECT.get())) {

                LivingEntity target = event.getEntity();

                target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));

            }
        }
    }

    // ------------------------
    // Passive venimeux, lvl 3
    // ------------------------
    @SubscribeEvent
    public static void onToxinHurt(LivingHurtEvent event) {

        DamageSource source = event.getSource();
        LivingEntity target = event.getEntity();

        if (target.level().isClientSide) return;

        if (!(source.getEntity() instanceof Player attacker))
            return;

        boolean hasGem =
                attacker.getMainHandItem().is(ModItems.RUBENITE_LVL3.get())
                        || attacker.getOffhandItem().is(ModItems.RUBENITE_LVL3.get())
                        || attacker.getInventory().items.stream()
                        .anyMatch(stack -> stack.is(ModItems.RUBENITE_LVL3.get()));

        if (!hasGem) return;

        int random = attacker.getRandom().nextInt(100);

        if (random < 20) {

            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));

        } else if (random < 40) {

            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));

        } else if (random < 60) {

            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));

        }

    }

    // -----------------
    // Lifesteal, lvl 3
    // -----------------
    @SubscribeEvent
    public static void onLifeSteal(LivingDamageEvent event) {

        DamageSource source = event.getSource();
        LivingEntity target = event.getEntity();

        if (event.getEntity().level().isClientSide) return;

        if (!(source.getEntity() instanceof Player attacker))
            return;

        boolean hasGem =
                attacker.getMainHandItem().is(ModItems.RUBENITE_LVL3.get())
                        || attacker.getOffhandItem().is(ModItems.RUBENITE_LVL3.get())
                        || attacker.getInventory().items.stream()
                        .anyMatch(stack -> stack.is(ModItems.RUBENITE_LVL3.get()));

        if (!hasGem) return;

        float lifeStealPercent;

        float damagePostProcess = event.getAmount();

        if (target instanceof Player) {
            lifeStealPercent = 0.5F;
        }
        else {
            lifeStealPercent = 0.25F;
        }

        float lifeStealAmount = damagePostProcess * lifeStealPercent;

        attacker.heal(lifeStealAmount);

    }
}
