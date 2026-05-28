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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RubeniteLvl3Item extends Item {
    public RubeniteLvl3Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        // coté serveur
        if (!pLevel.isClientSide) {

            // non-effet du cooldown actif
            if (pPlayer.getCooldowns().isOnCooldown(this)) {
                return super.use(pLevel, pPlayer, pUsedHand);
            }

            // regard du joueur + normaliser
            Vec3 look = pPlayer.getLookAngle().normalize();

            // puissance propulsion
            double XZpower = 0.50D;
            double Ypower = 0.50D;

            // application final
            pPlayer.setDeltaMovement(
                    look.x * XZpower,
                    Ypower,
                    look.z * XZpower
            );

            // particules (placeholder)
            if (pPlayer.level() instanceof ServerLevel serverLevel) {
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

            // son (placeholder)
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

            pPlayer.hurtMarked = true;
            pPlayer.fallDistance = 0;

            pPlayer.getCooldowns().addCooldown(this, 40);

        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // instance joueur et coté serveur
        if (!level.isClientSide && entity instanceof Player player) {

            // tick update de 1/4 de seconde
            if (player.tickCount % 4 != 0) return;

            player.addEffect(new MobEffectInstance(ModEffects.POISON_TOUCH_EFFECT.get(), 110, 1, false, false, false));

            if (player.isCrouching()) {

                player.addEffect(new MobEffectInstance(ModEffects.SPIDER_TOUCH_EFFECT.get(), 8, 1));

            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
