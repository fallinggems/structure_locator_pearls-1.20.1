package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MathiteItem extends Item {
    public MathiteItem(Properties pProperties) {
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

            // effet de resistance
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0));

            // sfx d'application de l'effet
            pPlayer.level().playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.ZOMBIE_VILLAGER_CONVERTED,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            // cooldown
            pPlayer.getCooldowns().addCooldown(this, 400);
        }
        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }
}
