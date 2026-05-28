package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArmaniteItem extends Item {
    public ArmaniteItem(Properties pProperties) {
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
                    2.0F,
                    Level.ExplosionInteraction.NONE
            );

            pPlayer.getCooldowns().addCooldown(this, 80);
        }

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }
}
