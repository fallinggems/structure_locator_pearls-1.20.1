package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArmaniteLvl2Item extends Item {
    public ArmaniteLvl2Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        // coté serveur
        if (!pLevel.isClientSide) {

            pLevel.explode(
                    pPlayer,
                    pPlayer.getX(),
                    pPlayer.getY() + 1.0,
                    pPlayer.getZ(),
                    3.5F,
                    Level.ExplosionInteraction.NONE
            );

            pPlayer.level().playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.ANVIL_PLACE,
                    SoundSource.PLAYERS,
                    0.8F,
                    0.6F
            );

            pPlayer.getCooldowns().addCooldown(this, 120);
        }

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // instance joueur et coté serveur
        if (!level.isClientSide && entity instanceof Player player) {

            // tick update de 1/4 de seconde
            if (player.tickCount % 5 != 0) return;

            // si en dessous ou à 1 coeurs
            if (player.getHealth() <= 2.0F) {
                // application resistance et slowness
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 0));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 0));
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
