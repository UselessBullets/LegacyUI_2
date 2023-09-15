package useless.legacyui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.core.sound.SoundType;

import java.util.Random;

public class LegacySoundManager {
    public static Random rand = new Random();
    public static Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
    public static SoundManager sndManager = mc.sndManager;
    public static float getPitch(boolean randomPitch){
        return (randomPitch && ModSettings.Sounds.UseRandomPitch()) ? 1f + ((rand.nextFloat()-0.5f)/16f) : 1f;
    }
    public static class play {
        public static void press(boolean randomPitch){
            sndManager.playSound("legacyui.ui.press", SoundType.GUI_SOUNDS, 1f, getPitch(randomPitch));
        }
        public static void back(boolean randomPitch){
            sndManager.playSound("legacyui.ui.back", SoundType.GUI_SOUNDS, 1f, getPitch(randomPitch));
        }
        public static void craft(boolean randomPitch){
            sndManager.playSound("legacyui.ui.craft", SoundType.GUI_SOUNDS, 1f, getPitch(randomPitch));
        }
        public static void craftfail(boolean randomPitch){
            sndManager.playSound("legacyui.ui.craftfail", SoundType.GUI_SOUNDS, 1f, getPitch(randomPitch));
        }
        public static void focus(boolean randomPitch){
            sndManager.playSound("legacyui.ui.focus", SoundType.GUI_SOUNDS, 1f, getPitch(randomPitch));
        }
        public static void scroll(boolean randomPitch){
            sndManager.playSound("legacyui.ui.scroll", SoundType.GUI_SOUNDS, 1f, getPitch(randomPitch));
        }
        public static void achievement(boolean randomPitch){
            sndManager.playSound("legacyui.ui.achievement", SoundType.GUI_SOUNDS, 1f, getPitch(randomPitch));
        }
    }
}
