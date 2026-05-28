package net.gabriel.structurelocatorpearls;

import net.gabriel.structurelocatorpearls.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StructureLocatorPearlsMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SLP_TAB = TABS.register("structure_locator_pearls_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.structure_locator_pearls_tab"))
                    .icon(ModItems.NETHER_EYE.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        output.accept(ModItems.NETHER_EYE.get());
                        output.accept(ModItems.BASTION_EYE.get());
                    })
                    .build()
    );

}
