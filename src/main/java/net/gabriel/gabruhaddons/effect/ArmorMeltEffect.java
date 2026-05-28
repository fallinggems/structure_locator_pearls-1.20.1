package net.gabriel.gabruhaddons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class ArmorMeltEffect extends MobEffect {
    public ArmorMeltEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

        this.addAttributeModifier(
                Attributes.ARMOR,
                "5c09c40a-02e7-48c6-b581-46d88842a0c5",
                -0.25,
                AttributeModifier.Operation.MULTIPLY_TOTAL
                // - 25% de point d'armures totaux
        );

        this.addAttributeModifier(
                Attributes.ARMOR_TOUGHNESS,
                "2267e0b1-ea96-407b-acc0-608c06af3796",
                -0.25,
                AttributeModifier.Operation.MULTIPLY_TOTAL
                // - 25% de point de robustesse totaux
        );

    }
}
