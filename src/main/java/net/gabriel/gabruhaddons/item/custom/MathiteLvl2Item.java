package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MathiteLvl2Item extends Item {
    public MathiteLvl2Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // instance joueur et coté serveur
        if (!level.isClientSide && entity instanceof Player player) {

            // tick update de 1/4 de seconde
            if (player.tickCount % 5 != 0) return;

            // si en dessous ou à 3 coeurs
            if (player.getHealth() <= 6.0F) {
                // application resistance
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 0));
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
