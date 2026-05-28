package net.gabriel.gabruhaddons.util;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModTags {

    public static class Structures {

        public static final TagKey<Structure> NETHER_FORTRESS_LOCATOR =
                tag("nether_fortress_locator");

        public static final TagKey<Structure> BASTION_LOCATOR =
                tag("bastion_locator");

        private static TagKey<Structure> tag(String name) {
            return TagKey.create(
                    Registries.STRUCTURE,
                    ResourceLocation.fromNamespaceAndPath(
                            GabruhAddonsMod.MOD_ID,
                            name
                    )
            );
        }
    }
}
