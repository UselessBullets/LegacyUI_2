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
        LegacyCategoryManager.register();
        LOGGER.info("LegacyUI initialized.");
    }
}
