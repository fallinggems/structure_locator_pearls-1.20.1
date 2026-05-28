package net.gabriel.gabruhaddons.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class SpiderTouchEffect extends MobEffect {
    public SpiderTouchEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        if (!pLivingEntity.horizontalCollision) return;

        // recuperation mouvement du joueur
        Vec3 initVec = pLivingEntity.getDeltaMovement();

        // poussé en fonction de l'amp de l'effet
        double climbLvl = 0.2D + (pAmplifier * 0.1D);

        // cap puissance vers lvl 20 (conseil utilisation max à 5)
        climbLvl = Math.min(climbLvl, 2.2D);

        // hitbox taille joueur + 0.2 bloc en y pour servir de detection
        AABB box = pLivingEntity.getBoundingBox().move(0.0D, 0.2D, 0.0D);

        // retourne "vrai" si pas de collision de la hitbox crée
        boolean safeClimb = pLivingEntity.level().noCollision(pLivingEntity, box);

        if (safeClimb) {

            // def vecteur "climb"
            Vec3 climbVec = new Vec3(initVec.x, climbLvl, initVec.z);

            // commande mouvement "climb"
            pLivingEntity.setDeltaMovement(climbVec.scale(0.97D));

        } else {

            // def vecteur "climb"
            Vec3 climbVec = new Vec3(initVec.x, Math.max(initVec.y, 0.0D), initVec.z);

            // commande mouvement "climb"
            pLivingEntity.setDeltaMovement(climbVec.scale(0.97D));
            
        }
        pLivingEntity.hasImpulse = true;
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
