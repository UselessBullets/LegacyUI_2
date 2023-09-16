package useless.legacyui.Sorting.Recipe;

import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.CraftingManager;
import net.minecraft.core.crafting.recipe.IRecipe;
import net.minecraft.core.crafting.recipe.RecipeShaped;
import net.minecraft.core.crafting.recipe.RecipeShapeless;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import useless.legacyui.LegacyUI;

import java.util.ArrayList;
import java.util.List;

public class RecipeGroupBuilder{
    private static final List<IRecipe> allRecipes = CraftingManager.getInstance().getRecipeList();
    private static final List<IRecipe> unusedRecipes = new ArrayList<>(allRecipes);
    private Boolean isDebug = false;
    private final List<Class> inclusiveClassList = new ArrayList<>();
    private final List<ItemStack> inclusiveItemList = new ArrayList<>();
    private final List<String> inclusiveKeywordList = new ArrayList<>();
    private final List<Class> exclusiveClassList = new ArrayList<>();
    private final List<ItemStack> exclusiveItemList = new ArrayList<>();
    private final List<String> exclusiveKeywordList = new ArrayList<>();
    private final List<ItemStack> excludeItemList = new ArrayList<>();
    public RecipeGroupBuilder excludeItem(Item item){
        return excludeItem(new ItemStack(item));
    }
    public RecipeGroupBuilder excludeItem(ItemStack stack){
        if (isDebug){
            LegacyUI.LOGGER.info(stack.toString());
        }
        excludeItemList.add(stack);
        return this;
    }
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
    public RecipeGroupBuilder addItemsWithMetaRange(Item item, int metaStart, int metaRange, boolean isInclusive){
        for (int i = 0; i < metaRange; i++) {
            this.addItem(item, metaStart+i, isInclusive);
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
    protected boolean stackInExcludeItem(ItemStack recipeStack){
        for (ItemStack stack: excludeItemList) {
            if (recipeStack.itemID == stack.itemID && recipeStack.getMetadata() == stack.getMetadata()){
                return true;
            }
        }
        return false;
    }
    protected boolean stackInClassList(List<Class> classList, ItemStack itemStack){
        for (Class clazz : classList){
            try {
                if (itemStack.itemID < Block.blocksList.length){
                    clazz.cast(Block.getBlock(itemStack.itemID));
                } else {
                    clazz.cast(itemStack.getItem());
                }
                return true;
            } catch (ClassCastException e){
            }
        }
        return false;
    }
    protected boolean stackInItemList(List<ItemStack> itemStackList, ItemStack itemStack){
        for (ItemStack stack : itemStackList){
            if (itemStack.itemID == stack.itemID && itemStack.getMetadata() == stack.getMetadata()){
                return true;
            }
        }
        return false;
    }
    protected boolean stackInKeywordList(List<String> keywordList, ItemStack itemStack){
        for (String keyword : keywordList){
            if (itemStack.getItem().getKey().contains(keyword)){
                return true;
            }
        }
        return false;
    }
    protected boolean recipeInRecipeList(List<IRecipe> recipeList, IRecipe recipe){
        for (IRecipe groupRecipe : recipeList){
            if (groupRecipe.equals(recipe)){
                return true;
            }
        }
        return false;
    }

    public RecipeGroup build(){
        List<IRecipe> unused_copy = new ArrayList<>(unusedRecipes);
        List<IRecipe> recipeGroupRecipes = new ArrayList<>();
        int removeOffset = 0;
        for (int i = 0; i < unused_copy.size(); i++) { // Add exclusive Recipes
            IRecipe currentRecipe = unused_copy.get(i);
            if (currentRecipe instanceof RecipeShaped || currentRecipe instanceof RecipeShapeless){
                ItemStack recipeItem = currentRecipe.getRecipeOutput();
                if (stackInExcludeItem(recipeItem)){
                    continue;
                }
                if (stackInClassList(exclusiveClassList,recipeItem) || stackInItemList(exclusiveItemList, recipeItem) || stackInKeywordList(exclusiveKeywordList, recipeItem)) {
                    recipeGroupRecipes.add(currentRecipe);
                    unusedRecipes.remove(i - removeOffset);
                    removeOffset++;
                    continue;
                }
            }
        }
        for (int i = 0; i < allRecipes.size(); i++) { // Add inclusive recipes
            IRecipe currentRecipe = allRecipes.get(i);
            if (currentRecipe instanceof RecipeShaped || currentRecipe instanceof RecipeShapeless){
                ItemStack recipeItem = currentRecipe.getRecipeOutput();
                if (stackInExcludeItem(recipeItem)){
                    continue;
                }
                if (recipeInRecipeList(recipeGroupRecipes, currentRecipe)){ // Stack already in list
                    continue;
                }
                if (stackInClassList(inclusiveClassList, recipeItem) || stackInItemList(inclusiveItemList, recipeItem) || stackInKeywordList(inclusiveKeywordList, recipeItem)){
                    recipeGroupRecipes.add(currentRecipe);
                    continue;
                }
            }
        }
        if (isDebug){
            LegacyUI.LOGGER.info("Group");
            printRecipeList(recipeGroupRecipes);
        }
        return new RecipeGroup(recipeGroupRecipes.toArray());
    }

    private void printRecipeList(List<IRecipe> recipes){
        for (IRecipe recipe : recipes){
            LegacyUI.LOGGER.info("Output:" + recipe.getRecipeOutput());
        }
    }
}
