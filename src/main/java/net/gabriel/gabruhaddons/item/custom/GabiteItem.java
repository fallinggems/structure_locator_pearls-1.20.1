package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GabiteItem extends Item {
    public GabiteItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {

        // instance joueur (sécurité)
        if (pAttacker instanceof Player player) {

            // coté serveur (sécurité)
            if (!pTarget.level().isClientSide) {

                // non-effet du cooldown actif
                if (player.getCooldowns().isOnCooldown(this)) {
                    return super.hurtEnemy(pStack, pTarget, pAttacker);
                }

                // pour la direction (joueur vers cible)
                double dx = pTarget.getX() - player.getX();
                double dz = pTarget.getZ() - player.getZ();
                //
                double distance = Math.sqrt(dx * dx + dz * dz);
                if (distance != 0) {
                    dx /= distance;
                    dz /= distance;
                }

                // kb + puissance du kb
                pTarget.push(dx * 0.4, 0.4, dz * 0.4);

                // particules de nuage
                if (pTarget.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            ParticleTypes.CLOUD,
                            pTarget.getX(),
                            pTarget.getY() + 0.5,
                            pTarget.getZ(),
                            6,
                            0.2, 0.2, 0.2,
                            0.05
                    );
                }

                // son de projection
                pTarget.level().playSound(
                        null,
                        pTarget.getX(),
                        pTarget.getY(),
                        pTarget.getZ(),
                        SoundEvents.PHANTOM_FLAP,
                        SoundSource.PLAYERS,
                        1.0F,   // volume
                        1.0F    // pitch
                );

                // cooldown (éviter que ce soit trop op)
                player.getCooldowns().addCooldown(this, 60);
            }
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
