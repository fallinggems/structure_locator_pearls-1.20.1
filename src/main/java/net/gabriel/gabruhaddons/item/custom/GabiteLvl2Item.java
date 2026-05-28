package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GabiteLvl2Item extends Item {
    public GabiteLvl2Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        // coté serveur
        if (!pLevel.isClientSide) {

            // non-effet du cooldown actif
            if (pPlayer.getCooldowns().isOnCooldown(this)) {
                return InteractionResultHolder.fail(stack);
            }

            pPlayer.getPersistentData().putInt("gabruhaddons_fall_res", 60);

            // projection vers le ciel (vitesse actuelle + vitesse du push)
            //pPlayer.push(0, 1.2, 0);
            Vec3 motion = pPlayer.getDeltaMovement();
            pPlayer.setDeltaMovement(
                    motion.x,
                    1.2,
                    motion.z
            );
            pPlayer.hurtMarked = true;
            pPlayer.fallDistance = 0;

            // particules de nuage
            if (pPlayer.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.CLOUD,
                        pPlayer.getX(),
                        pPlayer.getY() + 0.5,
                        pPlayer.getZ(),
                        6,
                        0.2, 0.2, 0.2,
                        0.05
                );
            }

            // sfx d'application de l'effet
            pPlayer.level().playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.PHANTOM_FLAP,
                    SoundSource.PLAYERS,
                    1.0F,
                    0.9F
            );

            // cooldown
            pPlayer.getCooldowns().addCooldown(this, 100);
        }
        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }
}
