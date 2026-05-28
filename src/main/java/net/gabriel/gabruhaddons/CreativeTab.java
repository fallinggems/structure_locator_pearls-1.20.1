package net.gabriel.gabruhaddons;

import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GabruhAddonsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GABRUH_TAB = TABS.register("gabruh_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.gabruh_tab"))
                    .icon(ModItems.ARTHURITE_LVL2.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        output.accept(ModItems.GABITE.get());
                        output.accept(ModItems.GABITE_LVL2.get());
                        output.accept(ModItems.GABITE_LVL3.get());
                        output.accept(ModItems.ARTHURITE.get());
                        output.accept(ModItems.ARTHURITE_LVL2.get());
                        output.accept(ModItems.ARTHURITE_LVL3.get());
                        output.accept(ModItems.MATHITE.get());
                        output.accept(ModItems.MATHITE_LVL2.get());
                        output.accept(ModItems.MATHITE_LVL3.get());
                        output.accept(ModItems.RUBENITE.get());
                        output.accept(ModItems.RUBENITE_LVL2.get());
                        output.accept(ModItems.RUBENITE_LVL3.get());
                        output.accept(ModItems.ARMANITE.get());
                        output.accept(ModItems.ARMANITE_LVL2.get());
                        output.accept(ModItems.ARMANITE_LVL3.get());

                        output.accept(ModItems.WIND_SHARD.get());
                        output.accept(ModItems.BURNING_SHARD.get());
                        output.accept(ModItems.STRONG_SHARD.get());
                        output.accept(ModItems.SPIDER_SHARD.get());
                        output.accept(ModItems.UNSTABLE_SHARD.get());
                    })
                    .build()
    );

}
