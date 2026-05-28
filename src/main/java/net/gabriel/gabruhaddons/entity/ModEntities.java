package net.gabriel.gabruhaddons.entity;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.entity.custom.MagmaZoneEntity;
import net.gabriel.gabruhaddons.entity.custom.NetherEyeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GabruhAddonsMod.MOD_ID);

    // création nouvelles entités ici :
    public static final RegistryObject<EntityType<MagmaZoneEntity>> MAGMA_ZONE =
            ENTITY_TYPES.register("magma_zone",
                    () -> EntityType.Builder
                            .of(MagmaZoneEntity::new, MobCategory.MISC)
                            .sized(0.01f, 0.01f)
                            .clientTrackingRange(8)
                            .updateInterval(10)
                            .build("magma_zone"));

    public static final RegistryObject<EntityType<NetherEyeEntity>> NETHER_EYE =
            ENTITY_TYPES.register("nether_eye",
                    () -> EntityType.Builder
                            .<NetherEyeEntity>of(NetherEyeEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("nether_eye"));

    public static final RegistryObject<EntityType<NetherEyeEntity>> BASTION_EYE =
            ENTITY_TYPES.register("bastion_eye",
                    () -> EntityType.Builder
                            .<NetherEyeEntity>of(NetherEyeEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("bastion_eye"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
