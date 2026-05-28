package net.gabriel.gabruhaddons.entity.custom;

import net.gabriel.gabruhaddons.effect.ModEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.UUID;

public class MagmaZoneEntity extends Entity {

    private static final int LIFETIME = 200;

    private UUID ownerUUID;

    public MagmaZoneEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide) {

            if (tickCount >= LIFETIME) {

                // son (fire extinguish)
                level().playSound(
                        null,
                        blockPosition(),
                        SoundEvents.FIRE_EXTINGUISH,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );

                discard();
                return;
            }

            double radius = 8.0D;

            AABB area = new AABB(
                    getX() - radius,
                    getY() - 1,
                    getZ() - radius,
                    getX() + radius,
                    getY() + 1,
                    getZ() + radius
            );

            List<LivingEntity> entities = level().getEntitiesOfClass(
                    LivingEntity.class,
                    area
            );

            // ICI pour les applications de Dégâts, Effets aux Cibles concernées
            for (LivingEntity target : entities) {

                if (target.getUUID().equals(ownerUUID)) continue;
                if (target.fireImmune()) continue;
                if (target.hasEffect(MobEffects.FIRE_RESISTANCE)) continue;

                // vérifie cercle réel
                double dx = target.getX() - getX();
                double dz = target.getZ() - getZ();
                if ((dx * dx + dz * dz) > radius * radius)
                    continue;

                // dégâts style magma block ++
                if (target.tickCount % 10 == 0) {
                    target.hurt(
                            damageSources().hotFloor(),
                            3.0F
                    );

                    // Hot et Armor Melt
                    target.addEffect(new MobEffectInstance(ModEffects.HOT_EFFECT.get(), 200, 0));
                    target.addEffect(new MobEffectInstance(ModEffects.ARMOR_MELT_EFFECT.get(), 600, 0));
                }
            }

            for (LivingEntity owner : entities) {

                if (!owner.getUUID().equals(ownerUUID)) continue;

                if (owner.tickCount % 20 == 0) {

                    owner.setSecondsOnFire(5);
                }
            }

            if (!level().isClientSide) {
                // Motif : Nuage Diffus 1/4 (base de feu bleu)
                ServerLevel serverLevel = (ServerLevel) level();

                serverLevel.sendParticles(
                        ParticleTypes.FLAME,
                        getX(),
                        getY(),
                        getZ(),
                        5,
                        1.5,
                        0.15,
                        1.5,
                        0.01
                );
                // Motif : Cercle Saccagé 2/4 (cerceau de fumée)
                ServerLevel level = (ServerLevel) this.level();

                double radius2 = 4.0;
                double points2 = 24;

                for (int i = 0; i < points2; i++) {

                    double angle = (2 * Math.PI / points2) * i;

                    double x = getX() + Math.cos(angle) * radius2;
                    double z = getZ() + Math.sin(angle) * radius2;

                    level.sendParticles(
                            ParticleTypes.SMOKE,
                            x, getY() + 0.05, z,
                            1,   // 1 particule par point
                            0.5, 0.15, 0.5,
                            0
                    );
                }
                // Motif : Cercle Saccagé 3/4 (cerceau de flames)

                double radius3 = 6.0;
                double points3 = 42;

                for (int i = 0; i < points3; i++) {

                    double angle = (2 * Math.PI / points3) * i;

                    double x = getX() + Math.cos(angle) * radius3;
                    double z = getZ() + Math.sin(angle) * radius3;

                    level.sendParticles(
                            ParticleTypes.SMALL_FLAME,
                            x, getY() + 0.05, z,
                            1,   // 1 particule par point
                            0, 0, 0,
                            0
                    );
                }
                // Motif : Cercle Saccagé 4/4 (barrières de fumée)

                double radius4 = 8.0;
                double points4 = 48;

                for (int i = 0; i < points4; i++) {

                    double angle = (2 * Math.PI / points4) * i;

                    double x = getX() + Math.cos(angle) * radius4;
                    double z = getZ() + Math.sin(angle) * radius4;

                    level.sendParticles(
                            ParticleTypes.SMOKE,
                            x, getY() + 0.05, z,
                            1,   // 1 particule par point
                            0.5, 0.3, 0.5,
                            0
                    );
                }
            }
        }
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {}
}
