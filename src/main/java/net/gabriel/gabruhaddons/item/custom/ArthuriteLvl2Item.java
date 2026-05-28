package net.gabriel.gabruhaddons.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArthuriteLvl2Item extends Item {
    public ArthuriteLvl2Item(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // instance joueur et coté serveur
        if (!level.isClientSide && entity instanceof Player player) {

            // tick update de 1/4 de seconde
            if (player.tickCount % 5 != 0) return;

            // si a 10 coeurs (pv max)
            if (player.getHealth() >= 20.0F) {
                // application resistance
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0, false, false, true));

                // particules de fumée aux pieds + tick update de 1/2 de seconde
                if (player.level() instanceof ServerLevel serverLevel && player.tickCount % 10 != 0) {
                    serverLevel.sendParticles(
                            ParticleTypes.LARGE_SMOKE,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            3,
                            0.2, 0.0, 0.2,
                            0.03
                    );
                }
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
