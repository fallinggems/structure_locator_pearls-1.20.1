package net.gabriel.gabruhaddons.datagen;

import net.gabriel.gabruhaddons.GabruhAddonsMod;
import net.gabriel.gabruhaddons.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    // private pour les listes de cuisson de blocs 16:00 sur la vidéo

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // TOUTES LES RECETTES ICI de quinconque manières

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARTHURITE.get())
                .pattern(" M ")
                .pattern("MSM")
                .pattern(" M ")
                .define('S', Items.AMETHYST_SHARD)
                .define('M', Items.MAGMA_BLOCK)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARTHURITE_LVL2.get())
                .pattern("OSO")
                .pattern("SXS")
                .pattern("OSO")
                .define('S', Items.BLAZE_POWDER)
                .define('O', Items.GOLD_BLOCK)
                .define('X', ModItems.ARTHURITE.get())
                .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARTHURITE_LVL3.get())
                .pattern("#N#")
                .pattern("RXR")
                .pattern("#I#")
                .define('N', Items.NETHERITE_SWORD)
                .define('I', Items.NETHERITE_INGOT)
                .define('R', Items.RED_SANDSTONE)
                .define('#', ModItems.BURNING_SHARD.get())
                .define('X', ModItems.ARTHURITE_LVL2.get())
                .unlockedBy(getHasName(Items.NETHERITE_SWORD), has(Items.NETHERITE_SWORD))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MATHITE.get())
                .pattern(" S ")
                .pattern("SFS")
                .pattern(" S ")
                .define('S', Items.ANDESITE)
                .define('F', Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.STONE_BRICKS), has(Items.STONE_BRICKS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MATHITE_LVL2.get())
                .pattern("DSD")
                .pattern("SXS")
                .pattern("DSD")
                .define('S', Items.IRON_BLOCK)
                .define('D', Items.DIAMOND)
                .define('X', ModItems.MATHITE.get())
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MATHITE_LVL3.get())
                .pattern("#T#")
                .pattern("DXD")
                .pattern("#S#")
                .define('T', Items.TOTEM_OF_UNDYING)
                .define('S', Items.SHIELD)
                .define('D', Items.DIAMOND_BLOCK)
                .define('#', ModItems.STRONG_SHARD.get())
                .define('X', ModItems.MATHITE_LVL2.get())
                .unlockedBy(getHasName(Items.TOTEM_OF_UNDYING), has(Items.TOTEM_OF_UNDYING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARMANITE.get())
                .pattern(" S ")
                .pattern("SRS")
                .pattern(" S ")
                .define('S', Items.GUNPOWDER)
                .define('R', Items.GLOWSTONE)
                .unlockedBy(getHasName(Items.GUNPOWDER), has(Items.GUNPOWDER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARMANITE_LVL2.get())
                .pattern("NSN")
                .pattern("SXS")
                .pattern("NSN")
                .define('S', Items.TNT)
                .define('N', Items.NETHER_BRICK)
                .define('X', ModItems.ARMANITE.get())
                .unlockedBy(getHasName(Items.SCULK), has(Items.SCULK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARMANITE_LVL3.get())
                .pattern("#C#")
                .pattern("EXE")
                .pattern("#A#")
                .define('C', Items.END_CRYSTAL)
                .define('A', Items.NETHERITE_AXE)
                .define('E', Items.ENDER_EYE)
                .define('#', ModItems.UNSTABLE_SHARD.get())
                .define('X', ModItems.ARMANITE_LVL2.get())
                .unlockedBy(getHasName(Items.END_CRYSTAL), has(Items.END_CRYSTAL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GABITE.get())
                .pattern(" S ")
                .pattern("SAS")
                .pattern(" S ")
                .define('S', Items.SNOWBALL)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GABITE_LVL2.get())
                .pattern(" D ")
                .pattern("SXS")
                .pattern(" D ")
                .define('S', Items.QUARTZ)
                .define('D', Items.POWDER_SNOW_BUCKET)
                .define('X', ModItems.GABITE.get())
                .unlockedBy(getHasName(Items.POWDER_SNOW_BUCKET), has(Items.POWDER_SNOW_BUCKET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GABITE_LVL3.get())
                .pattern("#D#")
                .pattern("BXB")
                .pattern("#@#")
                .define('D', Items.DRAGON_BREATH)
                .define('@', Items.DIAMOND_BOOTS)
                .define('B', Items.BLUE_ICE)
                .define('#', ModItems.WIND_SHARD.get())
                .define('X', ModItems.GABITE_LVL2.get())
                .unlockedBy(getHasName(Items.DRAGON_BREATH), has(Items.DRAGON_BREATH))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBENITE.get())
                .pattern("S S")
                .pattern(" A ")
                .pattern("S S")
                .define('S', Items.COBWEB)
                .define('A', Items.SPIDER_EYE)
                .unlockedBy(getHasName(Items.SNOWBALL), has(Items.SNOWBALL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBENITE_LVL2.get())
                .pattern("SWA")
                .pattern("WXW")
                .pattern("AWS")
                .define('S', Items.ENDER_PEARL)
                .define('A', Items.PHANTOM_MEMBRANE)
                .define('W', Items.RED_WOOL)
                .define('X', ModItems.RUBENITE.get())
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBENITE_LVL3.get())
                .pattern("#S#")
                .pattern("EXE")
                .pattern("#@#")
                .define('S', Items.SHULKER_BOX)
                .define('@', Items.CHAINMAIL_BOOTS)
                .define('E', Items.FERMENTED_SPIDER_EYE)
                .define('#', ModItems.SPIDER_SHARD.get())
                .define('X', ModItems.RUBENITE_LVL2.get())
                .unlockedBy(getHasName(Items.FERMENTED_SPIDER_EYE), has(Items.FERMENTED_SPIDER_EYE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHER_EYE.get())
                .pattern(" W ")
                .pattern("WXW")
                .pattern(" W ")
                .define('W', Items.NETHERRACK)
                .define('X', Items.LAVA_BUCKET)
                .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASTION_EYE.get())
                .pattern(" W ")
                .pattern("WXW")
                .pattern(" W ")
                .define('W', Items.NETHER_BRICKS)
                .define('X', Items.MAGMA_BLOCK)
                .unlockedBy(getHasName(Items.MAGMA_BLOCK), has(Items.MAGMA_BLOCK))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends net.minecraft.world.item.crafting.AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  GabruhAddonsMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
