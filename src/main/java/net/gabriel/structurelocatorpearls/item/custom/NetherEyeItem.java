package net.gabriel.structurelocatorpearls.item.custom;

import net.gabriel.structurelocatorpearls.entity.custom.NetherEyeEntity;
import net.gabriel.structurelocatorpearls.util.StructureModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NetherEyeItem extends Item {
    public NetherEyeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {

            if (pLevel.dimension() != Level.NETHER) {
                return InteractionResultHolder.fail(stack);
            }

            ServerLevel serverLevel = (ServerLevel) pLevel;

            BlockPos structurePos = serverLevel.findNearestMapStructure(
                    StructureModTags.Structures.NETHER_FORTRESS_LOCATOR,
                    pPlayer.blockPosition(),
                    100,
                    false
            );

            if (structurePos != null) {

                NetherEyeEntity eye = new NetherEyeEntity(
                        pLevel,
                        pPlayer.getX(),
                        pPlayer.getY() + 1.5D,
                        pPlayer.getZ()
                );

                eye.signalTo(structurePos);

                pLevel.addFreshEntity(eye);

                if (!pPlayer.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
        }

        return InteractionResultHolder.success(stack);
    }
}
