package useless.legacyui.Sorting;

import net.minecraft.core.lang.I18n;

import java.util.ArrayList;
import java.util.List;

public class RecipeCategory {
    private String key;
    public int[] iconCoordinate;
    private final List<RecipeGroup> recipes = new ArrayList<>();

    public RecipeCategory(String modid, String translationKey , int[] iconCoordinate){
        this.key = (modid + ".categories." + translationKey).replace("..", ".");
        this.iconCoordinate = iconCoordinate;
    }
    public String getKey(){
        return key;
    }
    public String getTranslatedKey(){
        return I18n.getInstance().translateKey(key);
    }
}
