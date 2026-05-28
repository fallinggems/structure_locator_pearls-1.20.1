package net.gabriel.gabruhaddons.datagen;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GabruhAddonsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // register les modèles ici puis c'est tout
        simpleItem(ModItems.ARTHURITE);
        simpleItem(ModItems.ARTHURITE_LVL2);
        simpleItem(ModItems.ARTHURITE_LVL3);
        simpleItem(ModItems.MATHITE);
        simpleItem(ModItems.MATHITE_LVL2);
        simpleItem(ModItems.MATHITE_LVL3);
        simpleItem(ModItems.ARMANITE);
        simpleItem(ModItems.ARMANITE_LVL2);
        simpleItem(ModItems.ARMANITE_LVL3);
        simpleItem(ModItems.GABITE);
        simpleItem(ModItems.GABITE_LVL2);
        simpleItem(ModItems.GABITE_LVL3);
        simpleItem(ModItems.RUBENITE);
        simpleItem(ModItems.RUBENITE_LVL2);
        simpleItem(ModItems.RUBENITE_LVL3);

        simpleItem(ModItems.WIND_SHARD);
        simpleItem(ModItems.BURNING_SHARD);
        simpleItem(ModItems.STRONG_SHARD);
        simpleItem(ModItems.SPIDER_SHARD);
        simpleItem(ModItems.UNSTABLE_SHARD);

        simpleItem(ModItems.NETHER_EYE);
        simpleItem(ModItems.BASTION_EYE);
    }

    // creation commande
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(
                            GabruhAddonsMod.MOD_ID,
                            "item/" + item.getId().getPath()
                    ));

    }
}
