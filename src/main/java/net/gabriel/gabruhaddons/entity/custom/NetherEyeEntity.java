package net.gabriel.gabruhaddons.entity.custom;

import net.gabriel.gabruhaddons.entity.ModEntities;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NetherEyeEntity extends EyeOfEnder {

    public NetherEyeEntity(EntityType<? extends EyeOfEnder> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NetherEyeEntity(Level level, double x, double y, double z) {
        super(ModEntities.NETHER_EYE.get(), level);
        this.setPos(x, y, z);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.NETHER_EYE.get());
    }
}
