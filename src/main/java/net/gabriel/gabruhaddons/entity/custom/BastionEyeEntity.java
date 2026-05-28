package net.gabriel.gabruhaddons.entity.custom;

import net.gabriel.gabruhaddons.entity.ModEntities;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BastionEyeEntity extends EyeOfEnder {

    public BastionEyeEntity(EntityType<? extends EyeOfEnder> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BastionEyeEntity(Level level, double x, double y, double z) {
        super(ModEntities.BASTION_EYE.get(), level);
        this.setPos(x, y, z);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.BASTION_EYE.get());
    }
}
