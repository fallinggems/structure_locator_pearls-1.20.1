package net.gabriel.gabruhaddons.item;


import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GabruhAddonsMod.MOD_ID);

    // ajout items ici et c'est tout

    // GEMMES
    public static final RegistryObject<Item> ARTHURITE = ITEMS.register("arthurite",
            () -> new ArthuriteItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> ARTHURITE_LVL2 = ITEMS.register("arthurite_lvl2",
            () -> new ArthuriteLvl2Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> ARTHURITE_LVL3 = ITEMS.register("arthurite_lvl3",
            () -> new ArthuriteLvl3Item(
                    Tiers.DIAMOND, 5, -2.8F,
                    new Item.Properties()
                            .durability(0)
                            .fireResistant()
            ));
    public static final RegistryObject<Item> MATHITE = ITEMS.register("mathite",
            () -> new MathiteItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> MATHITE_LVL2 = ITEMS.register("mathite_lvl2",
            () -> new MathiteLvl2Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> MATHITE_LVL3 = ITEMS.register("mathite_lvl3",
            () -> new MathiteLvl3Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> ARMANITE = ITEMS.register("armanite",
            () -> new ArmaniteItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> ARMANITE_LVL2 = ITEMS.register("armanite_lvl2",
            () -> new ArmaniteLvl2Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> ARMANITE_LVL3 = ITEMS.register("armanite_lvl3",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> GABITE = ITEMS.register("gabite",
            () -> new GabiteItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> GABITE_LVL2 = ITEMS.register("gabite_lvl2",
            () -> new GabiteLvl2Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> GABITE_LVL3 = ITEMS.register("gabite_lvl3",
            () -> new GabiteLvl3Item(
                    Tiers.DIAMOND, 2, -2.4F,
                    new Item.Properties()
                            .durability(0)
                            .fireResistant()
            ));

    public static final RegistryObject<Item> RUBENITE = ITEMS.register("rubenite",
            () -> new RubeniteItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> RUBENITE_LVL2 = ITEMS.register("rubenite_lvl2",
            () -> new RubeniteLvl2Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> RUBENITE_LVL3 = ITEMS.register("rubenite_lvl3",
            () -> new RubeniteLvl3Item(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
            ));

    // FRAGMENTS
    public static final RegistryObject<Item> WIND_SHARD = ITEMS.register("wind_shard",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> BURNING_SHARD = ITEMS.register("burning_shard",
            () -> new BurningShardItem(new Item.Properties()
                    .stacksTo(16)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> STRONG_SHARD = ITEMS.register("strong_shard",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> SPIDER_SHARD = ITEMS.register("spider_shard",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> UNSTABLE_SHARD = ITEMS.register("unstable_shard",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .fireResistant()
            ));

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
