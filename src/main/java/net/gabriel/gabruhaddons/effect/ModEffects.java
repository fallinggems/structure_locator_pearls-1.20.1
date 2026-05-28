package net.gabriel.gabruhaddons.effect;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.jar.Attributes;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GabruhAddonsMod.MOD_ID);

    // créer les effets customs ici avant de passer sur la classe de ce qu'il fait !
    public static final RegistryObject<MobEffect> SPIDER_TOUCH_EFFECT = MOB_EFFECTS.register("spider_touch",
            () -> new SpiderTouchEffect(MobEffectCategory.BENEFICIAL, 0x1b393b));
    public static final RegistryObject<MobEffect> LEVITATION_TOUCH_EFFECT = MOB_EFFECTS.register("levitation_touch",
            () -> new LevitationTouchEffect(MobEffectCategory.BENEFICIAL, 0xdbf3ff));
    public static final RegistryObject<MobEffect> POISON_TOUCH_EFFECT = MOB_EFFECTS.register("poison_touch",
            () -> new PoisonTouchEffect(MobEffectCategory.BENEFICIAL, 0x27413b));
    public static final RegistryObject<MobEffect> AERODYNAMIC_EFFECT = MOB_EFFECTS.register("aerodynamic",
            () -> new AerodynamicEffect(MobEffectCategory.BENEFICIAL, 0x7a81c1));
    public static final RegistryObject<MobEffect> AIRBORNE_EFFECT = MOB_EFFECTS.register("airborne",
            () -> new AirborneEffect(MobEffectCategory.BENEFICIAL, 0x928bce));
    public static final RegistryObject<MobEffect> HOT_EFFECT = MOB_EFFECTS.register("hot",
            () -> new HotEffect(MobEffectCategory.HARMFUL, 0x47161c));
    public static final RegistryObject<MobEffect> ARMOR_MELT_EFFECT = MOB_EFFECTS.register("armor_melt",
            () -> new ArmorMeltEffect(MobEffectCategory.HARMFUL, 0x02878c));
    public static final RegistryObject<MobEffect> CONSUMED_EFFECT = MOB_EFFECTS.register("consumed",
            () -> new ConsumedEffect(MobEffectCategory.HARMFUL, 0x313533));
    public static final RegistryObject<MobEffect> FEROCITY_EFFECT = MOB_EFFECTS.register("ferocity",
            () -> new FerocityEffect(MobEffectCategory.BENEFICIAL, 0x781c1d));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
