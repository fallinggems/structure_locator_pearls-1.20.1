package net.gabriel.gabruhaddons.item.custom;

import net.gabriel.gabruhaddons.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RubeniteItem extends Item {
    public RubeniteItem(Properties pProperties) {
        super(pProperties);
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
