package net.gabriel.structurelocatorpearls.datagen;

import net.gabriel.structurelocatorpearls.StructureLocatorPearlsMod;
import net.gabriel.structurelocatorpearls.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StructureLocatorPearlsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // register les modèles ici puis c'est tout
        simpleItem(ModItems.NETHER_EYE);
        simpleItem(ModItems.BASTION_EYE);
    }

    // creation commande
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(
                            StructureLocatorPearlsMod.MOD_ID,
                            "item/" + item.getId().getPath()
                    ));

    }
}
