package net.gabriel.gabruhaddons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;


public class HotEffect extends MobEffect {
    public HotEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "cea8d3f7-6079-4700-ad06-9f97ab706a4b",
                -0.10,
                AttributeModifier.Operation.MULTIPLY_TOTAL
                // - 10% total de vitesse d'attaque
        );

    }
}
