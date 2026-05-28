package net.gabriel.structurelocatorpearls.item;


import net.gabriel.structurelocatorpearls.StructureLocatorPearlsMod;
import net.gabriel.structurelocatorpearls.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StructureLocatorPearlsMod.MOD_ID);

    public static final RegistryObject<Item> NETHER_EYE = ITEMS.register("nether_eye",
            () -> new NetherEyeItem(new Item.Properties()
                    .stacksTo(16)
            ));
    public static final RegistryObject<Item> BASTION_EYE = ITEMS.register("bastion_eye",
            () -> new BastionEyeItem(new Item.Properties()
                    .stacksTo(16)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
