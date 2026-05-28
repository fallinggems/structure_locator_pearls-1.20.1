package net.gabriel.gabruhaddons.item.custom;

import net.gabriel.gabruhaddons.effect.ModEffects;
import net.gabriel.gabruhaddons.entity.ModEntities;
import net.gabriel.gabruhaddons.entity.custom.MagmaZoneEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ArthuriteLvl3Item extends SwordItem {
    public ArthuriteLvl3Item(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (pPlayer.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        if (!pLevel.isClientSide) {

            MagmaZoneEntity zone = new MagmaZoneEntity(ModEntities.MAGMA_ZONE.get(), pLevel);

            zone.setPos(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ());

            zone.setOwnerUUID(pPlayer.getUUID());

            pLevel.addFreshEntity(zone);

            // effet "Consumed" (placeholder pour le moment)
            pPlayer.addEffect(new MobEffectInstance(ModEffects.CONSUMED_EFFECT.get(), 200, 0));

            // son (sceau de lave)
            pPlayer.level().playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.BUCKET_EMPTY_LAVA,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        }

        // cooldown (voir la durée, 400 ticks officiellement)
        pPlayer.getCooldowns().addCooldown(this, 400);

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {

        if (pAttacker instanceof Player player) {

            if (!player.level().isClientSide) {

                if (Math.random() < 0.34) {

                    // ZONE DE VAPEUR
                    double steamRad = 1.5;
                    double steamSqrRad = steamRad * steamRad;

                    // zone de recherche (cubique)
                    AABB steamArea = pTarget.getBoundingBox().inflate(steamRad);

                    // récupération des entités proches
                    List<LivingEntity> steamTargets = pTarget.level().getEntitiesOfClass(
                            LivingEntity.class,
                            steamArea
                    );

                    float steamDamage = 6.0F;

                    // dégâts de la zone de vapeur
                    for (LivingEntity entity : steamTargets) {

                        if (entity == pAttacker) continue;

                        if (entity.distanceToSqr(pTarget) > steamSqrRad) continue;

                        if (pAttacker instanceof Player player1) {

                            entity.hurt(
                                    player1.damageSources().playerAttack(player1),
                                    steamDamage
                            );
                        }
                    }

                    // ZONE DE SET ON FIRE
                    double fireRad = 5.0;
                    double fireSqrRad = fireRad * fireRad;

                    // zone de recherche (cubique)
                    AABB fireArea = pTarget.getBoundingBox().inflate(fireRad);

                    // récupération des entités proches
                    List<LivingEntity> fireTargets = pTarget.level().getEntitiesOfClass(
                            LivingEntity.class,
                            fireArea
                    );

                    // "dégâts" de la zone de feu
                    for (LivingEntity entity : fireTargets) {

                        if (entity == pAttacker) continue;

                        if (entity.distanceToSqr(pTarget) > fireSqrRad) continue;

                        entity.setSecondsOnFire(10);

                    }

                    // particules cible hit zone de feu (fumée)
                    if (player.level() instanceof ServerLevel serverLevel) {
                        double pFireRadius = 5.0;
                        int pFirePoints = 48;
                        int pTrueFirePoints = 5;
                        // Motif : Grand Cercle
                        for (int i = 0; i < pFirePoints; i++) {

                            double angle = 2 * Math.PI * i / pFirePoints;

                            double x = pTarget.getX() + Math.cos(angle) * pFireRadius;
                            double z = pTarget.getZ() + Math.sin(angle) * pFireRadius;
                            double y = pTarget.getY() + 0.5;

                            serverLevel.sendParticles(
                                    ParticleTypes.LARGE_SMOKE,
                                    x,
                                    y,
                                    z,
                                    1,
                                    0.2, 0.2, 0.2,
                                    0
                            );
                        }
                        // Motif : Grand Cercle
                        for (int i = 0; i < pTrueFirePoints; i++) {

                            double angle = 2 * Math.PI * i / pTrueFirePoints;

                            double x = pTarget.getX() + Math.cos(angle) * pFireRadius;
                            double z = pTarget.getZ() + Math.sin(angle) * pFireRadius;
                            double y = pTarget.getY() + 0.5;

                            serverLevel.sendParticles(
                                    ParticleTypes.LAVA,
                                    x,
                                    y,
                                    z,
                                    1,
                                    0, 0, 0,
                                    0
                            );
                        }
                        // Motif : Pouf de Vapeur
                        serverLevel.sendParticles(
                                ParticleTypes.SMOKE,
                                pTarget.getX(),
                                pTarget.getY(),
                                pTarget.getZ(),
                                12,
                                0.5, 0.3, 0.5,
                                0.05
                        );
                    }

                    // son (fire extinguish)
                    pTarget.level().playSound(
                            null,
                            pTarget.getX(),
                            pTarget.getY(),
                            pTarget.getZ(),
                            SoundEvents.FIRE_EXTINGUISH,
                            SoundSource.PLAYERS,
                            0.7F,
                            0.8F
                    );

                    // son (son de boule de feu)
                    pTarget.level().playSound(
                            null,
                            pTarget.getX(),
                            pTarget.getY(),
                            pTarget.getZ(),
                            SoundEvents.BLAZE_DEATH,
                            SoundSource.PLAYERS,
                            0.3F,
                            0.8F
                    );
                }
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if (level.isClientSide) return;
        if (!(entity instanceof Player player)) return;

        if (player.tickCount % 5 != 0) return;

        // Burning Aura I
        if (player.getHealth() >= 14.0F) {

            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30, 0, false, false, false));

            // PARTICULES
            // Motif : Aura de Fumée (Pieds)
            if (player.level() instanceof ServerLevel serverLevel && player.tickCount % 10 != 0) {
                serverLevel.sendParticles(
                        ParticleTypes.LARGE_SMOKE,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        3,
                        0.2, 0.0, 0.2,
                        0.03
                );
            }
        }

        // Burning Aura II
        if (player.getHealth() >= player.getMaxHealth()) {

            // FERO
            player.addEffect(new MobEffectInstance(ModEffects.FEROCITY_EFFECT.get(), 30, 0, false, false, false));

            // PARTICULES
            // Motif : Point Ardent (equivalent de particules sans cercle)
            if (player.level() instanceof ServerLevel serverLevel) {

                double radius = 0.5;
                int points = 5;

                double centerX = player.getX();
                double centerY = player.getY() + 0.1; // hauteur Y
                double centerZ = player.getZ();

                for (int i = 0; i < points; i++) {

                    double angle = 2 * Math.PI * i / points;

                    double x = centerX + Math.cos(angle) * radius;
                    double z = centerZ + Math.sin(angle) * radius;

                    serverLevel.sendParticles(
                            ParticleTypes.SOUL_FIRE_FLAME,
                            x,
                            centerY,
                            z,
                            1,
                            0.2,
                            0.2,
                            0.2,
                            0
                    );
                }
            }
        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
