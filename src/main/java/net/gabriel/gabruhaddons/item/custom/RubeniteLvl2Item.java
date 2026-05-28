package net.gabriel.gabruhaddons.item.custom;

import net.gabriel.gabruhaddons.effect.ModEffects;
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

public class RubeniteLvl2Item extends Item {
    public RubeniteLvl2Item(Properties pProperties) {
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

                pPlayer.addEffect(new MobEffectInstance(ModEffects.LEVITATION_TOUCH_EFFECT.get(), 600, 0));

                pPlayer.level().playSound(
                        null,
                        pPlayer.getX(),
                        pPlayer.getY(),
                        pPlayer.getZ(),
                        SoundEvents.ZOMBIE_VILLAGER_CONVERTED,
                        SoundSource.PLAYERS,
                        0.7F,
                        1.4F
                );

                pPlayer.getCooldowns().addCooldown(this, 600);

        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // instance joueur et coté serveur
        if (!level.isClientSide && entity instanceof Player player) {

            // tick update de 1/4 de seconde
            if (player.tickCount % 4 != 0) return;

            if (!player.isCrouching()) return;

            boolean inHotBar = slotId >= 0 && slotId <= 8;
            boolean inOffHand = player.getOffhandItem() == stack;
            if (!inHotBar && !inOffHand) return;

            player.addEffect(new MobEffectInstance(ModEffects.SPIDER_TOUCH_EFFECT.get(), 8, 0));

        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
