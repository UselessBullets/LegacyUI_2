package useless.legacyui;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.lang.I18n;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import useless.legacyui.Sorting.LegacyCategoryManager;
import useless.legacyui.Sorting.RecipeCategory;

public class LegacyUI implements ModInitializer {
    public static final String MOD_ID = "legacyui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "basics", IconHelper.getOrCreateIconTexture(MOD_ID, "planks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "bricks", IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "equipment", IconHelper.getOrCreateIconTexture(MOD_ID, "tools.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "food", IconHelper.getOrCreateIconTexture(MOD_ID, "health.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "redstone", IconHelper.getOrCreateIconTexture(MOD_ID, "lever.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "travel", IconHelper.getOrCreateIconTexture(MOD_ID, "rail.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "misc", IconHelper.getOrCreateIconTexture(MOD_ID, "painting.png")));
        LegacyCategoryManager.recipeCategories.add(new RecipeCategory(MOD_ID, "modded", IconHelper.getOrCreateIconTexture(MOD_ID, "modded.png")));
        LOGGER.info("LegacyUI initialized.");
    }
}
