package net.gabriel.gabruhaddons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class ConsumedEffect extends MobEffect {
    public ConsumedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "225845ef-f28b-4dd8-813b-b39e3331f9f8",
                -0.10,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                "a48323da-f6fc-41cf-ae25-67b21200e80c",
                -0.10,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.addAttributeModifier(
                Attributes.ARMOR,
                "8e00e3e1-59ad-4858-8660-56904cd11180",
                -0.10,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.addAttributeModifier(
                Attributes.ARMOR_TOUGHNESS,
                "39322d12-7c0f-4889-bdaa-30568e5bfa21",
                -0.10,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

    }
}
