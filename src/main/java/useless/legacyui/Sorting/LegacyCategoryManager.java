package useless.legacyui.Sorting;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.tool.*;
import useless.legacyui.Helper.IconHelper;
import useless.legacyui.LegacyUI;
import useless.legacyui.Sorting.Recipe.RecipeCategory;
import useless.legacyui.Sorting.Recipe.RecipeGroup;
import useless.legacyui.Sorting.Recipe.RecipeGroupBuilder;

import java.util.ArrayList;
import java.util.List;

public class LegacyCategoryManager {
    public static List<RecipeCategory> recipeCategories = new ArrayList<>();
    public static void register(){}
    static {
        String MOD_ID = LegacyUI.MOD_ID;
        /*LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "basics", IconHelper.getOrCreateIconTexture(MOD_ID, "planks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "bricks", IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "equipment", IconHelper.getOrCreateIconTexture(MOD_ID, "tools.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "food", IconHelper.getOrCreateIconTexture(MOD_ID, "health.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "redstone", IconHelper.getOrCreateIconTexture(MOD_ID, "lever.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "travel", IconHelper.getOrCreateIconTexture(MOD_ID, "rail.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "misc", IconHelper.getOrCreateIconTexture(MOD_ID, "painting.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "modded", IconHelper.getOrCreateIconTexture(MOD_ID, "modded.png")));*/

        RecipeGroup pickaxe = new RecipeGroupBuilder()
                .addClass(ItemToolPickaxe.class)
                .build();
        RecipeGroup shovel = new RecipeGroupBuilder()
                .addClass(ItemToolShovel.class)
                .build();
        RecipeGroup axe = new RecipeGroupBuilder()
                .addClass(ItemToolAxe.class)
                .build();
        RecipeGroup hoe = new RecipeGroupBuilder()
                .addClass(ItemToolHoe.class)
                .build();
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(
                MOD_ID,
                "equipment",
                IconHelper.getOrCreateIconTexture(MOD_ID, "tools.png"),
                new RecipeGroup[]{pickaxe,shovel,axe,hoe}));

        RecipeGroup food = new RecipeGroupBuilder()
                .addClass(ItemFood.class)
                .build();
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(
                MOD_ID,
                "food",
                IconHelper.getOrCreateIconTexture(MOD_ID, "health.png"),
                new RecipeGroup[]{food}));

        RecipeGroup blocks = new RecipeGroupBuilder()
                .addClass(Block.class)
                .addKeyword("tile")
                .build();
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(
                MOD_ID,
                "bricks",
                IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png"),
                new RecipeGroup[]{blocks}));
    }
}
