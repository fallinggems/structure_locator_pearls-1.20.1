package net.gabriel.gabruhaddons.item.custom;

import net.gabriel.gabruhaddons.effect.ModEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GabiteLvl3Item extends SwordItem {
    public GabiteLvl3Item(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        // coté serveur
        if (!pLevel.isClientSide) {

            int AG3count = pPlayer.getPersistentData().getInt("gabruhaddons_propulsion_count");

            if (AG3count >= 2) {
                return InteractionResultHolder.fail(stack);
            }

            // Boolean si Airborne actif
            boolean Airborne = pPlayer.hasEffect(ModEffects.AIRBORNE_EFFECT.get());

            // regard horizontal
            Vec3 look = pPlayer.getLookAngle();
            Vec3 XZlook = new Vec3(look.x,0, look.z).normalize();

            // motion horizontal
            Vec3 motion = pPlayer.getDeltaMovement();
            //Vec3 XZmotion = new Vec3(motion.x, 0, motion.z);

            // vitesse actuelle
            //double speed = XZmotion.length();

            // pour annuler la propulsion X.Z en sneak
            boolean isSneaking = pPlayer.isCrouching();

            // force de propulsion X.Z bonus (au saut NATUREL)
            double XZbonusBoost;

            // force de propulsion Y
            double Ypower = 0.8;

            if (Airborne) {
                XZbonusBoost = 0.45; // 0.35 originellement
            } else {
                XZbonusBoost = 0.30; // 0.25 originellement
            }

            Vec3 newMotion = motion;

            if (!isSneaking) {
                newMotion = motion.add(XZlook.scale(XZbonusBoost));
            }

            // application final
            pPlayer.setDeltaMovement(
                    newMotion.x,
                    Ypower,
                    newMotion.z
            );

            pPlayer.hurtMarked = true;
            pPlayer.fallDistance = 0;

            pPlayer.getPersistentData().putBoolean("gabruhaddons_onfall_fall_res", true);
            pPlayer.getPersistentData().putInt("gabruhaddons_propulsion_count", AG3count + 1);
            if (Airborne) {
                pPlayer.getPersistentData().putInt("gabruhaddons_particle_trail_timer", 20);
            }

            // particules lorsque utilisé SANS Airborne
            if (pPlayer.level() instanceof ServerLevel serverLevel && !Airborne) {
                serverLevel.sendParticles(
                        ParticleTypes.CLOUD,
                        pPlayer.getX(),
                        pPlayer.getY() + 0.5,
                        pPlayer.getZ(),
                        6,
                        0.3, 0.3, 0.3,
                        0.05
                );
            }

            // son lors propulsion
            if (!Airborne) {
                pPlayer.level().playSound(
                        null,
                        pPlayer.getX(),
                        pPlayer.getY(),
                        pPlayer.getZ(),
                        SoundEvents.PHANTOM_FLAP,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.4F
                );
            } else {
                pPlayer.level().playSound(
                        null,
                        pPlayer.getX(),
                        pPlayer.getY(),
                        pPlayer.getZ(),
                        SoundEvents.GLOW_SQUID_AMBIENT,
                        SoundSource.PLAYERS,
                        1.4F,
                        1.4F
                );
                pPlayer.level().playSound(
                        null,
                        pPlayer.getX(),
                        pPlayer.getY(),
                        pPlayer.getZ(),
                        SoundEvents.GLOW_SQUID_DEATH,
                        SoundSource.PLAYERS,
                        0.1F,
                        1.4F
                );
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {

        if (pAttacker instanceof Player player) {

            if (!player.level().isClientSide) {

                if (!player.onGround() && player.getDeltaMovement().y < -0.4F) {

                    boolean Airborne = player.hasEffect(ModEffects.AIRBORNE_EFFECT.get());

                    Vec3 motion = player.getDeltaMovement();
                    player.setDeltaMovement(
                            motion.x,
                            1.2,
                            motion.z
                    );
                    player.addEffect(new MobEffectInstance(ModEffects.AIRBORNE_EFFECT.get(), 140, 0, true, false ,true));
                    player.hurtMarked = true;
                    player.fallDistance = 0;

                    // particules cible hit
                    if (player.level() instanceof ServerLevel serverLevel && Airborne) {
                        // Motif : Cercle Précis
                        double pTotemRadius = 1.3;
                        int pTotemPoints = 24;

                        for (int i = 0; i < pTotemPoints; i++) {

                            double angle = 2 * Math.PI * i / pTotemPoints;

                            double x = pTarget.getX() + Math.cos(angle) * pTotemRadius;
                            double z = pTarget.getZ() + Math.sin(angle) * pTotemRadius;
                            double y = pTarget.getY() + 1.0;

                            serverLevel.sendParticles(
                                    ParticleTypes.TOTEM_OF_UNDYING,
                                    x,
                                    y + 0.3,
                                    z,
                                    1,
                                    0, 0, 0,
                                    0
                            );
                        }
                        // Motif : Explosion Centrée Rapide
                        Vec3 pImpCenter = pTarget.position().add(0, 1.0, 0);
                        int pImpPoints = 16;

                        for (int i = 0; i < pImpPoints; i++) {

                            double angle = 2 * Math.random() * Math.PI * 2;

                            double horizontal = 0.20 + Math.random() * 0.15; // Ampleur

                            double dx = Math.cos(angle) * horizontal;
                            double dz = Math.sin(angle) * horizontal;

                            double dy = (Math.random() - 0.5) * 0.15;

                            serverLevel.sendParticles(
                                    ParticleTypes.END_ROD,
                                    pImpCenter.x,
                                    pImpCenter.y + 0.3,
                                    pImpCenter.z,
                                    0,
                                    dx, dy, dz,
                                    1.0
                            );
                        }
                    } else if (player.level() instanceof ServerLevel serverLevel && !Airborne) {
                        serverLevel.sendParticles(
                                ParticleTypes.CLOUD,
                                pTarget.getX(),
                                pTarget.getY() + 0.5,
                                pTarget.getZ(),
                                6,
                                0.3, 0.3, 0.3,
                                0.10
                        );
                    }

                    // sfx hit avec Airborne
                    if (Airborne) {
                        player.level().playSound(
                                null,
                                pTarget.getX(),
                                pTarget.getY(),
                                pTarget.getZ(),
                                SoundEvents.TOTEM_USE,
                                SoundSource.PLAYERS,
                                0.3F,
                                1.5F
                        );
                        player.level().playSound(
                                null,
                                pTarget.getX(),
                                pTarget.getY(),
                                pTarget.getZ(),
                                SoundEvents.GLOW_SQUID_SQUIRT,
                                SoundSource.PLAYERS,
                                0.7F,
                                1.2F
                        );
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (pLevel.isClientSide) return;
        if (!(pEntity instanceof Player player)) return;

        int trailTimer = player.getPersistentData().getInt("gabruhaddons_particle_trail_timer");

        if (trailTimer <= 0) return;

        if (player.onGround()) return;

        player.getPersistentData().putInt("gabruhaddons_particle_trail_timer", trailTimer -1);

        if (player.tickCount % 2 == 0) {

            ServerLevel serverLevel = (ServerLevel) pLevel;

            double offsetX = (Math.random() - 0.5) * 0.2;
            double offsetY = (Math.random() - 0.5) * 0.2;
            double offsetZ = (Math.random() - 0.5) * 0.2;

            serverLevel.sendParticles(
                    ParticleTypes.END_ROD,
                    player.getX() + offsetX,
                    player.getY() + offsetY,
                    player.getZ() + offsetZ,
                    1,
                    0,
                    0,
                    0,
                    0
            );
        }
    }
}
