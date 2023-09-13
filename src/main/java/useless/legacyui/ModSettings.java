package useless.legacyui;

import turniplabs.halplibe.util.ConfigHandler;
import useless.legacyui.ModModules.GuiModModule;
import useless.prismaticlibe.helper.ModCheckHelper;

import java.util.Properties;

public class ModSettings {
    public static final ConfigHandler config;
    static {
        Properties props = new Properties();
        props.setProperty("CraftingHideUndiscoveredItems","true"); //
        props.setProperty("ExperimentalQuickStackFix", "false"); //
        props.setProperty("ExperimentalQuickStackFixDelay", "50"); //
        props.setProperty("GuiLabelColor", "404040"); //
        props.setProperty("HighlightColor", "FF0000"); //
        props.setProperty("GuiBackgroundColor", "90101010"); //
        props.setProperty("OverrideLabelModColor", "false"); //
        props.setProperty("UseLegacySounds", "true"); //
        props.setProperty("HideHotbarInGUIs", "true"); // 
        config = new ConfigHandler(LegacyUI.MOD_ID, props);
    }
    public static class Gui {
        private static final boolean hideUndiscoveredItems = config.getBoolean("CraftingHideUndiscoveredItems");
        private static final boolean hideHotbarInGUIs = config.getBoolean("HideHotbarInGUIs");
        private static final boolean experimentalQuickStackFix = config.getBoolean("ExperimentalQuickStackFix");
        private static final int experimentalQuickStackFixDelay = config.getInt("ExperimentalQuickStackFixDelay");
        public static boolean HideUndiscoveredItems(){
            return hideUndiscoveredItems;
        }
        public static boolean HideHotbarInGUIs(){
            return hideHotbarInGUIs;
        }
        public static boolean ExperimentalQuickStackFix(){return experimentalQuickStackFix;}
        public static int ExperimentalQuickStackFixDelay(){return experimentalQuickStackFixDelay;}
    }
    public static class Colors {
        private static final boolean overrideLabelModColor = config.getBoolean("OverrideLabelModColor");
        private static final int guiLabelColor;
        static {
            if (ModCheckHelper.checkForMod("guimod", ">=2.0.0") && !overrideLabelModColor){
                guiLabelColor = GuiModModule.getColorFromMod();
            } else {
                guiLabelColor = Integer.decode("0X" + config.getString("GuiLabelColor"));
            }
        }
        private static final int highlightColor = Integer.decode("0X" + config.getString("HighlightColor"));
        private static final int guiBackgroundColor = ((Integer.decode("0X" + config.getString("GuiBackgroundColor").substring(0,2)) << 24) + Integer.decode("0X" + config.getString("GuiBackgroundColor").substring(2)));
        public static int GuiLabelColor(){
            return guiLabelColor;
        }
        public static int HighlightColor(){
            return highlightColor;
        }
        public static int GuiBackgroundColor(){
            return guiBackgroundColor;
        }
    }
    public static class Sounds {
        private static final boolean useLegacySounds = config.getBoolean("UseLegacySounds");
        public static boolean UseLegacySounds(){
            return useLegacySounds;
        }
    }
}
