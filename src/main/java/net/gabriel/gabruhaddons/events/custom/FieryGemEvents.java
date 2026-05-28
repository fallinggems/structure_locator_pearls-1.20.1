package net.gabriel.gabruhaddons.events.custom;

import com.google.common.collect.Multimap;
import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.effect.ModEffects;
import net.gabriel.gabruhaddons.util.DelayedActivation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GabruhAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FieryGemEvents {

    // -----------------------
    // Effet Ferocity, lvl 3
    // -----------------------
    @SubscribeEvent
    public static void onHurtFerocity(LivingHurtEvent event) {

        // entité source des dégâts -> "attacker"
        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) return;

        if (!attacker.hasEffect(ModEffects.FEROCITY_EFFECT.get())) return;

        // pour ne pas loop à l'infini, Data -> coup fero appliqué
        if (attacker.getPersistentData().getBoolean("gabruhaddons_fero_hit")) {

            attacker.getPersistentData().putBoolean("gabruhaddons_fero_hit", false);
            return;
        }

        // entité cible -> "target"
        LivingEntity target = event.getEntity();

        float selectedWeaponDamage = getWeaponDamage(attacker);

        if (attacker.getRandom().nextFloat() < 0.5F) {

            DelayedActivation.schedule(5, () -> {

                if (!attacker.isAlive() || !target.isAlive())
                    return;

                target.invulnerableTime = 0;

                attacker.getPersistentData().putBoolean("gabruhaddons_fero_hit", true);

                target.hurt(
                        attacker.damageSources().playerAttack((Player) attacker),
                        selectedWeaponDamage
                );

                target.level().playSound(
                        null,
                        target.blockPosition(),
                        SoundEvents.ZOMBIE_ATTACK_IRON_DOOR,
                        SoundSource.PLAYERS,
                        0.8F,
                        1.7F
                );

                if (target.level() instanceof ServerLevel serverLevel) {

                    serverLevel.sendParticles(
                            ParticleTypes.FLASH,

                            target.getX(),
                            target.getY() + 1.0,
                            target.getZ(),

                            1,      // count
                            0,    // offset x
                            0,    // offset y
                            0,    // offset z
                            0   // speed
                    );
                }
            });
        }
    }

    private static float getWeaponDamage(LivingEntity attacker) {

        ItemStack weapon = attacker.getMainHandItem();

        Multimap<Attribute, AttributeModifier> modifiers =
                weapon.getAttributeModifiers(EquipmentSlot.MAINHAND);

        double damage = 0;

        for (AttributeModifier modifier :
                modifiers.get(Attributes.ATTACK_DAMAGE)) {

            if (modifier.getOperation() ==
                    AttributeModifier.Operation.ADDITION) {

                damage += modifier.getAmount();
            }
        }

        return (float) damage;
    }

    // -----------------
    // Effet Hot, lvl 3
    // -----------------
    @SubscribeEvent
    public static void hotMultiplier(LivingHurtEvent event) {

        DamageSource source = event.getSource();

        LivingEntity victim = event.getEntity();

        if (victim.level().isClientSide) return;

        if (!victim.hasEffect(ModEffects.HOT_EFFECT.get())) return;

        if (
                source.is(DamageTypes.IN_FIRE)
                        || source.is(DamageTypes.ON_FIRE)
                        || source.is(DamageTypes.LAVA)
                        || source.is(DamageTypes.HOT_FLOOR)
        ) {
            event.setAmount(event.getAmount() * 2.0F);
        }
    }
}
