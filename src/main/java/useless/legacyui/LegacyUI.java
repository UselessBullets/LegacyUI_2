package useless.legacyui;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.lang.I18n;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyUI implements ModInitializer {
    public static final String MOD_ID = "legacyui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        IconHelper.getOrCreateIconTexture(MOD_ID, "planks.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "tools.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "health.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "lever.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "rail.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "painting.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "modded.png");
        LOGGER.info("LegacyUI initialized.");
    }
}
