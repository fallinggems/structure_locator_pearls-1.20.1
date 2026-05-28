package net.gabriel.structurelocatorpearls.datagen;

import net.gabriel.structurelocatorpearls.StructureLocatorPearlsMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, StructureLocatorPearlsMod.MOD_ID);
    }

    @Override
    protected void start() {

    }
}
