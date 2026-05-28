package net.gabriel.structurelocatorpearls.events;

import net.gabriel.structurelocatorpearls.StructureLocatorPearlsMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StructureLocatorPearlsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
}
