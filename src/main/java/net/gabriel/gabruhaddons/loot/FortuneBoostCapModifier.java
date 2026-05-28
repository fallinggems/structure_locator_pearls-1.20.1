package net.gabriel.gabruhaddons.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.network.PacketListener;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class FortuneBoostCapModifier extends LootModifier {
    public static final Supplier<Codec<FortuneBoostCapModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, FortuneBoostCapModifier::new)));


    public FortuneBoostCapModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    // un global loot modifier (GLM) custom pour la Reinforced Gem III, la logique JAVA s'applique ici

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!(context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player player)) {
            return generatedLoot;
        }

        boolean hasGem = false;
        ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
        BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.MATHITE_LVL3.get())) {
                hasGem = true;
                break;
            }
        }

        if (!hasGem) {
            return generatedLoot;
        }

        if (tool == null) {
            return generatedLoot;
        }

        if (state == null) {
            return generatedLoot;
        }

        if (!state.is(Tags.Blocks.ORES)) {
            return generatedLoot;
        }

        int fortune = EnchantmentHelper.getTagEnchantmentLevel(
                Enchantments.BLOCK_FORTUNE,
                tool
        );

        if (fortune <= 0) {
            return generatedLoot;
        }

        for (ItemStack lootStack : generatedLoot) {

            int extra = player.getRandom().nextInt(fortune + 3);

            lootStack.grow(extra);
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
