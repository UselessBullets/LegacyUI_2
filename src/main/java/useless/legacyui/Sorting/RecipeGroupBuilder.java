package useless.legacyui.Sorting;

import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.CraftingManager;
import net.minecraft.core.crafting.recipe.IRecipe;
import net.minecraft.core.crafting.recipe.RecipeShaped;
import net.minecraft.core.crafting.recipe.RecipeShapeless;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import useless.legacyui.LegacyUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeGroupBuilder {
    private static CraftingManager manager = CraftingManager.getInstance();
    private static List<IRecipe> allRecipes = manager.getRecipeList();
    private static List<IRecipe> unusedRecipes = new ArrayList<>(allRecipes);
    private Boolean isDebug = false;
    private List<Class> inclusiveClassList = new ArrayList<>();
    private List<ItemStack> inclusiveItemList = new ArrayList<>();
    private List<String> inclusiveKeywordList = new ArrayList<>();
    private List<Class> exclusiveClassList = new ArrayList<>();
    private List<ItemStack> exclusiveItemList = new ArrayList<>();
    private List<String> exclusiveKeywordList = new ArrayList<>();
    public RecipeGroupBuilder addKeyword(String keyword){
        return addKeyword(keyword, false);
    }
    public RecipeGroupBuilder addKeyword(String keyword, boolean isInclusive){
        if (isDebug){
            LegacyUI.LOGGER.info(keyword);
        }

        if (isInclusive){
            inclusiveKeywordList.add(keyword);
        } else {
            exclusiveKeywordList.add(keyword);
        }

        return this;
    }
    public RecipeGroupBuilder addItem(Block block){
        return addItem(block.asItem(), false);
    }
    public RecipeGroupBuilder addItem(Block block, boolean isInclusive){
        return addItem(block.asItem(), isInclusive);
    }
    public RecipeGroupBuilder addItem(int id, int meta){
        return addItem(new ItemStack(id, 1, meta), false);
    }
    public RecipeGroupBuilder addItem(int id, int meta, boolean isInclusive){
        return addItem(new ItemStack(id, 1, meta), isInclusive);
    }
    public RecipeGroupBuilder addItem(Item item, int meta){
        return addItem(new ItemStack(item, 1, meta), false);
    }
    public RecipeGroupBuilder addItem(Item item, int meta, boolean isInclusive){
        return addItem(new ItemStack(item, 1, meta), isInclusive);
    }
    public RecipeGroupBuilder addItem(Item item, boolean isInclusive){
        return addItem(new ItemStack(item), isInclusive);
    }
    public RecipeGroupBuilder addItem(Item item){
        return addItem(new ItemStack(item));
    }
    public RecipeGroupBuilder addItem(ItemStack stack){
        return addItem(stack, false);
    }
    public RecipeGroupBuilder addItem(ItemStack stack, boolean isInclusive){
        if (isDebug){
            LegacyUI.LOGGER.info(stack.toString());
        }

        if (isInclusive){
            inclusiveItemList.add(stack);
        } else {
            exclusiveItemList.add(stack);
        }

        return this;
    }
    public RecipeGroupBuilder addClass(Class clazz){
        return addClass(clazz, false);
    }
    public RecipeGroupBuilder addClass(Class clazz, boolean isInclusive){
        if (isDebug){
            LegacyUI.LOGGER.info(clazz.getName());
        }

        if (isInclusive){
            inclusiveClassList.add(clazz);
        } else {
            exclusiveClassList.add(clazz);
        }

        return this;
    }
    public RecipeGroupBuilder isDebug(){
        isDebug = true;
        return this;
    }
    public RecipeGroupBuilder printCurrentConfig(){
        LegacyUI.LOGGER.info("isDebug:" + isDebug);
        for (Class clazz : inclusiveClassList){
            LegacyUI.LOGGER.info("inclusiveClass:"+clazz.getName());
        }
        for (ItemStack stack : inclusiveItemList){
            LegacyUI.LOGGER.info("inclusiveItemStack:"+stack);
        }
        for (String keyword : inclusiveKeywordList){
            LegacyUI.LOGGER.info("inclusiveKeyword:"+keyword);
        }
        for (Class clazz : exclusiveClassList){
            LegacyUI.LOGGER.info("exclusiveClass:"+clazz.getName());
        }
        for (ItemStack stack : exclusiveItemList){
            LegacyUI.LOGGER.info("exclusiveItemStack:"+stack);
        }
        for (String keyword : exclusiveKeywordList){
            LegacyUI.LOGGER.info("exclusiveKeyword:"+keyword);
        }
        return this;
    }
    public RecipeGroup build(){

        List<IRecipe> unused_copy = new ArrayList<>(unusedRecipes);
        List<IRecipe> recipeGroupRecipes = new ArrayList<>();
        int removeOffset = 0;
        for (int i = 0; i < unused_copy.size(); i++) {
            IRecipe currentRecipe = unused_copy.get(i);
            if (currentRecipe instanceof RecipeShaped || currentRecipe instanceof RecipeShapeless){
                ItemStack recipeItem = currentRecipe.getRecipeOutput();
                boolean foundMatch = false;
                for (Class clazz : exclusiveClassList){
                    try {
                        clazz.cast(recipeItem.getItem());
                        recipeGroupRecipes.add(currentRecipe);
                        unusedRecipes.remove(i - removeOffset);
                        removeOffset++;
                        foundMatch = true;
                        continue;
                    } catch (ClassCastException e){
                    }
                    if (foundMatch){
                        continue;
                    }

                }
                if (foundMatch){
                    continue;
                }
                for (ItemStack stack : exclusiveItemList){
                    if (recipeItem.itemID == stack.itemID && recipeItem.getMetadata() == stack.getMetadata()){
                        recipeGroupRecipes.add(currentRecipe);
                        unusedRecipes.remove(i - removeOffset);
                        removeOffset++;
                        foundMatch = true;
                        continue;
                    }
                }
                if (foundMatch){
                    continue;
                }
                for (String keyword : exclusiveKeywordList){
                    if (recipeItem.getItem().getKey().contains(keyword)){
                        recipeGroupRecipes.add(currentRecipe);
                        unusedRecipes.remove(i-removeOffset);
                        removeOffset++;
                        foundMatch = true;
                        continue;
                    }
                }
                if (foundMatch){
                    continue;
                }
            }
        }
        for (int i = 0; i < allRecipes.size(); i++) {
            IRecipe currentRecipe = unused_copy.get(i);
            if (currentRecipe instanceof RecipeShaped || currentRecipe instanceof RecipeShapeless){
                ItemStack recipeItem = currentRecipe.getRecipeOutput();
                boolean foundMatch = false;
                for (IRecipe groupRecipe : recipeGroupRecipes){
                    if (groupRecipe.equals(currentRecipe)){
                        foundMatch = true;
                        continue;
                    }
                }
                if (foundMatch){
                    continue;
                }
                for (Class clazz : inclusiveClassList){
                    try {
                        clazz.cast(recipeItem.getItem());
                        recipeGroupRecipes.add(currentRecipe);
                        foundMatch = true;
                        continue;
                    } catch (ClassCastException e){
                    }
                    if (foundMatch){
                        continue;
                    }

                }
                if (foundMatch){
                    continue;
                }
                for (ItemStack stack : inclusiveItemList){
                    if (recipeItem.itemID == stack.itemID && recipeItem.getMetadata() == stack.getMetadata()){
                        recipeGroupRecipes.add(currentRecipe);
                        foundMatch = true;
                        continue;
                    }
                }
                if (foundMatch){
                    continue;
                }
                for (String keyword : inclusiveKeywordList){
                    if (recipeItem.getItem().getKey().contains(keyword)){
                        recipeGroupRecipes.add(currentRecipe);
                        foundMatch = true;
                        continue;
                    }
                }
                if (foundMatch){
                    continue;
                }
            }
        }
        LegacyUI.LOGGER.info("All");
        printRecipeList(allRecipes);
        LegacyUI.LOGGER.info("Unused");
        printRecipeList(unusedRecipes);
        LegacyUI.LOGGER.info("Group");
        printRecipeList(recipeGroupRecipes);
        return new RecipeGroup();
    }

    private void printRecipeList(List<IRecipe> recipes){
        for (IRecipe recipe : recipes){
            LegacyUI.LOGGER.info("Output:" + recipe.getRecipeOutput());
        }
    }
}
