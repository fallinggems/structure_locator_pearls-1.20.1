package net.gabriel.structurelocatorpearls.entity;

import net.gabriel.structurelocatorpearls.StructureLocatorPearlsMod;
import net.gabriel.structurelocatorpearls.entity.custom.BastionEyeEntity;
import net.gabriel.structurelocatorpearls.entity.custom.NetherEyeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StructureLocatorPearlsMod.MOD_ID);

    public static final RegistryObject<EntityType<NetherEyeEntity>> NETHER_EYE =
            ENTITY_TYPES.register("nether_eye",
                    () -> EntityType.Builder
                            .<NetherEyeEntity>of(NetherEyeEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("nether_eye"));

    public static final RegistryObject<EntityType<BastionEyeEntity>> BASTION_EYE =
            ENTITY_TYPES.register("bastion_eye",
                    () -> EntityType.Builder
                            .<BastionEyeEntity>of(BastionEyeEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("bastion_eye"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
