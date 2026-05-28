package net.gabriel.gabruhaddons.datagen;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.item.ModItems;
import net.gabriel.gabruhaddons.loot.AddItemModifier;
import net.gabriel.gabruhaddons.loot.FortuneBoostCapModifier;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, GabruhAddonsMod.MOD_ID);
    }

    @Override
    protected void start() {

        add("fortune_boost",
                new FortuneBoostCapModifier(
                        new LootItemCondition[] {}
                )
        );

        add("wind_shard_from_phantom", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/phantom")).build(),

                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.WIND_SHARD.get()));

        add("wind_shard_from_vex", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/vex")).build(),

                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.WIND_SHARD.get()));

        add("burning_shard_from_blaze", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/blaze")).build(),

                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.BURNING_SHARD.get()));

        add("burning_shard_from_magma_cube", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/magma_cube")).build(),

                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.BURNING_SHARD.get()));

        add("strong_shard_from_creeper", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/creeper")).build(),

                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.STRONG_SHARD.get()));

        add("strong_shard_from_iron_golem", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/iron_golem")).build(),

                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.STRONG_SHARD.get()));

        add("spider_shard_from_spider", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/spider")).build(),

                LootItemRandomChanceCondition.randomChance(0.05f).build()}, ModItems.SPIDER_SHARD.get()));

        add("spider_shard_from_cave_spider", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/cave_spider")).build(),

                LootItemRandomChanceCondition.randomChance(0.16f).build()}, ModItems.SPIDER_SHARD.get()));

        add("unstable_shard_from_wither_skeleton", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/wither_skeleton")).build(),

                LootItemRandomChanceCondition.randomChance(0.12f).build()}, ModItems.UNSTABLE_SHARD.get()));

        add("unstable_shard_from_ghast", new AddItemModifier(new LootItemCondition[] {

                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:entities/ghast")).build(),

                LootItemRandomChanceCondition.randomChance(0.25f).build()}, ModItems.UNSTABLE_SHARD.get()));
    }
}
