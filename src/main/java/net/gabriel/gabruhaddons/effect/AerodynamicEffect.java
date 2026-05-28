package net.gabriel.gabruhaddons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;


public class AerodynamicEffect extends MobEffect {
    public AerodynamicEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "4892125a-6981-4888-b9b7-d20b29e70c64",
                0.10,
                AttributeModifier.Operation.MULTIPLY_BASE
        );

        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "505544e0-0396-470f-b8df-39c2e5ac4d70",
                0.25,
                AttributeModifier.Operation.MULTIPLY_BASE
        );

    }
}
