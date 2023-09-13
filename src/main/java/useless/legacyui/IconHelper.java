package useless.legacyui;

import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.TextureHandler;

import java.util.HashMap;
import java.util.Map;

public class IconHelper {
    public static Map<String, int[]> registeredIconTextures = new HashMap<>();
    /**
     * Place mod textures in the <i>assets/modid/icon/</i> directory for them to be seen.
     */
    public static int[] getOrCreateIconTexture(String modId, String iconTexture) {
        int[] possibleCoords = registeredIconTextures.get(modId + ":" + iconTexture);
        if (possibleCoords != null) {
            return possibleCoords;
        }

        int[] newCoords = IconCoords.nextCoords();
        registeredIconTextures.put(modId + ":" + iconTexture, newCoords);
        addTextureToIcons(modId, iconTexture, newCoords[0], newCoords[1]);
        return newCoords;
    }
    public static void addTextureToIcons(String modId, String iconTexture, int x, int y) {
        TextureHelper.textureHandlers.add(new TextureHandler("/assets/legacyui/gui/icons.png", "/assets/" + modId + "/icon/" + iconTexture, texCoordToIndex(x, y), 32, 1));
    }
    public static int texCoordToIndex(int x, int y) {
        return x + y * 16;
    }
    public static class IconCoords {
        public static int lastX = 0;
        public static int lastY = 0;
        public static boolean outOfSpace = false;
        public static int[] nextCoords(){
            if (!outOfSpace){
                int x = lastX;
                int y = lastY;
                if (++lastX > 15) {
                    lastX = 0;
                    if (++lastY > 15) {
                        outOfSpace = true;
                        LegacyUI.LOGGER.info("Reached the end of icon texture space!");
                    }
                }
                return new int[]{x, y};
            }
            else {
                LegacyUI.LOGGER.info("No more icon texture spaces are available!");
                return new int[]{15, 15};
            }
        }
    }
}
