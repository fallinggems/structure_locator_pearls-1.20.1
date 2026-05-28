package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BurningShardItem extends Item {
    public BurningShardItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level level, Entity entity, int pSlotId, boolean pIsSelected) {

        // instance joueur et coté serveur
        if (!level.isClientSide && entity instanceof Player player) {

            if (player.tickCount % 20 != 0) return;

            player.setSecondsOnFire(2);

        }

        super.inventoryTick(pStack, level, entity, pSlotId, pIsSelected);
    }
}
