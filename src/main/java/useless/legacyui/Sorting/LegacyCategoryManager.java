package useless.legacyui.Sorting;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemBucket;
import net.minecraft.core.item.tool.ItemTool;
import useless.legacyui.Helper.IconHelper;
import useless.legacyui.LegacyUI;

import java.util.ArrayList;
import java.util.List;

public class LegacyCategoryManager {
    public static List<RecipeCategory> recipeCategories = new ArrayList<>();
    public static void register(){}
    static {
        String MOD_ID = LegacyUI.MOD_ID;
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "basics", IconHelper.getOrCreateIconTexture(MOD_ID, "planks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "bricks", IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "equipment", IconHelper.getOrCreateIconTexture(MOD_ID, "tools.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "food", IconHelper.getOrCreateIconTexture(MOD_ID, "health.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "redstone", IconHelper.getOrCreateIconTexture(MOD_ID, "lever.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "travel", IconHelper.getOrCreateIconTexture(MOD_ID, "rail.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "misc", IconHelper.getOrCreateIconTexture(MOD_ID, "painting.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "modded", IconHelper.getOrCreateIconTexture(MOD_ID, "modded.png")));

        RecipeGroup group = new RecipeGroupBuilder().addClass(ItemBucket.class).addItem(Item.toolSwordDiamond).addKeyword("sponge").addClass(ItemTool.class).addClass(ItemTool.class, true).printCurrentConfig().build();
        int i = 0;
    }
}
