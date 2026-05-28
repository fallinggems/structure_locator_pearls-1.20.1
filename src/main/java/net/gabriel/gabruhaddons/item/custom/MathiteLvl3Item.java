package net.gabriel.gabruhaddons.item.custom;

import net.gabriel.gabruhaddons.effect.ModEffects;
import net.gabriel.gabruhaddons.entity.ModEntities;
import net.gabriel.gabruhaddons.entity.custom.MagmaZoneEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MathiteLvl3Item extends Item {
    public MathiteLvl3Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (pPlayer.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        if (!pLevel.isClientSide) {

            pPlayer.getPersistentData().putInt("gabruhaddons_invulnerable_hits", 5);

            pPlayer.level().playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.ZOMBIE_VILLAGER_CONVERTED,
                    SoundSource.PLAYERS,
                    0.8F,
                    0.8F
            );

        }

        // officiellement 600 ticks mais pour test moins
        pPlayer.getCooldowns().addCooldown(this, 600);

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }
}
