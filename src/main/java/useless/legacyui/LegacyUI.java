package useless.legacyui;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.helper.TextureHelper;
public class LegacyUI implements ModInitializer {
    public static final String MOD_ID = "legacyui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        IconHelper.getOrCreateIconTexture(MOD_ID, "grass.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "bricks.png");
        IconHelper.getOrCreateIconTexture(MOD_ID, "health.png");
        LOGGER.info("LegacyUI initialized.");
    }
}
