package useless.legacyui.Gui.GuiScreens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.crafting.recipe.IRecipe;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.lang.I18n;
import org.lwjgl.input.Keyboard;
import useless.legacyui.Gui.Containers.LegacyContainerCrafting;
import useless.legacyui.Helper.KeyboardHelper;
import useless.legacyui.ModSettings;
import useless.legacyui.Sorting.LegacyCategoryManager;
import useless.legacyui.Sorting.Recipe.RecipeCategory;
import useless.legacyui.Sorting.Recipe.RecipeGroup;

public class GuiLegacyCrafting extends GuiContainer {
    protected int craftingSize;
    private static int GUIx;
    private static int GUIy;
    private EntityPlayer player;
    public static int currentTab = 0;
    public static int currentScroll = 0;
    public static int currentSlot = 0;
    private static final int guiTextureWidth = 512;
    public GuiLegacyCrafting(EntityPlayer player, int craftingSize){
        super((new LegacyContainerCrafting(player.inventory, craftingSize)));
        this.craftingSize = craftingSize;
        this.player = player;
        this.mc = Minecraft.getMinecraft(this);
        setContainerRecipes();
    }
    public GuiLegacyCrafting(EntityPlayer player, int x, int y, int z, int craftingSize) {
        super(new LegacyContainerCrafting(player.inventory, player.world, x, y, z, craftingSize));
        this.craftingSize = craftingSize;
        this.player = player;
        this.mc = Minecraft.getMinecraft(this);
    }
    public void scrollSlot(int direction){
        if (direction > 0){
            while (direction > 0){
                selectSlot(currentSlot + 1);
                direction--;
            }
        } else if (direction < 0){
            while (direction < 0){
                selectSlot(currentSlot - 1);
                direction++;
            }
        }
    }
    public void selectSlot(int value){
        currentSlot = value;
        int groupSize = currentCategory().getRecipeGroups(isSmall()).length;
        if (currentSlot > groupSize-1){
            currentSlot -= groupSize;
        } else if (currentSlot < 0){
            currentSlot += groupSize;
        }
        currentScroll = 0;
        setContainerRecipes();
    }
    public void scrollGroup(int direction){
        if (direction > 0){
            while (direction > 0){
                selectScrollGroup(currentScroll + 1);
                direction--;
            }
        } else if (direction < 0){
            while (direction < 0){
                selectScrollGroup(currentScroll - 1);
                direction++;
            }
        }
    }
    public void selectScrollGroup(int value){
        currentScroll = value;
        int groupSize = currentCategory().getRecipeGroups(isSmall())[currentSlot].getRecipes(isSmall()).length;
        if (currentScroll > groupSize-1){
            currentScroll -= groupSize;
        } else if (currentScroll < 0){
            currentScroll += groupSize;
        }
        setContainerRecipes();
    }
    public void scrollTab(int direction){
        if (direction > 0){
            while (direction > 0){
                selectTab(currentTab + 1);
                direction--;
            }
        } else if (direction < 0){
            while (direction < 0){
                selectTab(currentTab - 1);
                direction++;
            }
        }
    }
    public void selectTab(int value){
        currentTab = value;
        int tabAmount = 8;
        if (currentTab > Math.min(tabAmount-1, LegacyCategoryManager.recipeCategories.size()-1)){
            currentTab -= tabAmount;
        } else if (currentTab < 0){
            currentTab += tabAmount;
        }
        currentTab = Math.min(currentTab, LegacyCategoryManager.recipeCategories.size()-1);
        currentScroll = 0;
        currentSlot = 0;
        setContainerRecipes();
    }
    public void handleInputs(){
        boolean shifted = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
        if (KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyForward.keyCode()) || KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLookUp.keyCode())){
            scrollGroup(-1);
        }
        if (KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyBack.keyCode()) || KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLookDown.keyCode())){
            scrollGroup(1);
        }
        if (KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyRight.keyCode()) || KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLookRight.keyCode())){
            if (shifted){
                scrollTab(1);
            } else {
                scrollSlot(1);
            }
        }
        if (KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLeft.keyCode()) || KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLookLeft.keyCode())){
            if (shifted){
                scrollTab(-1);
            } else {
                scrollSlot(-1);
            }
        }
    }
    public RecipeCategory currentCategory(){
        return LegacyCategoryManager.recipeCategories.get(currentTab);
    }
    public boolean isSmall(){
        return craftingSize <= 4;
    }
    public void initGui() {
        super.initGui();

        // Setup size variables
        this.xSize = 273; // width of Gui window
        this.ySize = 175; // height of Gui window
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;

        // Static Initialization
        currentTab = 0;
        currentScroll = 0;
        currentSlot = 0;
        setContainerRecipes();
    }
    public void setContainerRecipes(){
        ((LegacyContainerCrafting)inventorySlots).setRecipes(player, mc.statFileWriter, true);
    }
    public void onGuiClosed() {
        super.onGuiClosed();
        this.inventorySlots.onCraftGuiClosed(this.mc.thePlayer);
    }
    public void drawScreen(int x, int y, float renderPartialTicks) {
        handleInputs();
        super.drawScreen(x, y, renderPartialTicks);
    }
    protected void drawGuiContainerForegroundLayer(){
        UtilGui.bindTexture("/assets/legacyui/gui/legacycrafting.png");
        drawSelectionCursorForeground();
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick) {
        UtilGui.bindTexture("/assets/legacyui/gui/legacycrafting.png");
        UtilGui.drawTexturedModalRect(this, GUIx, GUIy, 0,0, this.xSize, this.ySize, 1f/guiTextureWidth); // Render Background

        int tabWidth = 35;
        UtilGui.drawTexturedModalRect(this, GUIx + (tabWidth - 1) * currentTab, GUIy - 2, 0,175, tabWidth, 30, 1f/guiTextureWidth); // Render Selected Tab

        if (isSmall()){ // 2x2 Crafting overlay
            UtilGui.drawTexturedModalRect(this, GUIx + 19, GUIy + 108, 61, 175, 54, 54, 1f/guiTextureWidth);
        }

        drawStringCenteredNoShadow(fontRenderer, I18n.getInstance().translateKey("legacyui.guilabel.inventory"),GUIx + 205, GUIy + 97, ModSettings.Colors.GuiLabelColor());
        String craftingString;
        if (ModSettings.Gui.ShowCraftingItemNamePreview()){
            IRecipe currentRecipe = (IRecipe) currentCategory().getRecipeGroups(isSmall())[currentSlot].getRecipes(isSmall())[currentScroll];
            craftingString = currentRecipe.getRecipeOutput().getDisplayName();
        } else {
            craftingString = I18n.getInstance().translateKey("legacyui.guilabel.crafting");
        }

        drawStringCenteredNoShadow(fontRenderer, craftingString,GUIx + 73, GUIy + 97, ModSettings.Colors.GuiLabelColor());
        drawStringCenteredNoShadow(fontRenderer, LegacyCategoryManager.recipeCategories.get(currentTab).getTranslatedKey(),GUIx + (xSize/2), GUIy + 36, ModSettings.Colors.GuiLabelColor());

        UtilGui.bindTexture("/assets/legacyui/gui/legacycrafting.png");
        drawSelectionCursorBackground();

        UtilGui.bindTexture("/assets/legacyui/gui/icons.png");
        for (int i = 0; i < Math.min(LegacyCategoryManager.recipeCategories.size(), 8); i++) {
            UtilGui.drawIconTexture(this, GUIx + 5 + (tabWidth - 1) * i, GUIy + 2, LegacyCategoryManager.recipeCategories.get(i).iconCoordinate, 0.75f); // Render Icon
        }
    }
    private void drawSelectionCursorForeground(){
        int x = 8 + 18*currentSlot;
        int y = 52;
        if (currentCategory().getRecipeGroups(isSmall())[currentSlot].getRecipes(isSmall()).length > 1){
            UtilGui.drawTexturedModalRect(this, x - 1,y,35, 175, 26, 24, 1f/guiTextureWidth);
            UtilGui.drawTexturedModalRect(this, x - 1,y - 31, 115, 175, 26,31, 1f/guiTextureWidth);
            UtilGui.drawTexturedModalRect(this, x - 1,y + 24, 141, 175, 26,31, 1f/guiTextureWidth);
        } else {
            UtilGui.drawTexturedModalRect(this, x,y, 36, 175, 24, 24, 1f/guiTextureWidth);
        }
    }
    private void drawSelectionCursorBackground(){
        int x = 12 + 18*currentSlot;
        int y = 51;
        if (currentCategory().getRecipeGroups(isSmall())[currentSlot].getRecipes(isSmall()).length > 1){
            UtilGui.drawTexturedModalRect(this,GUIx + x - 1,GUIy + y - 17,167, 175, 18, 18, 1f/guiTextureWidth);
            UtilGui.drawTexturedModalRect(this,GUIx + x - 1,GUIy + y + 25, 167, 175, 18,18, 1f/guiTextureWidth);
        }

    }
}
