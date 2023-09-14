package useless.legacyui.Gui.GuiScreens;

import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.world.World;
import org.lwjgl.input.Keyboard;
import useless.legacyui.Gui.Containers.LegacyContainerCrafting;
import useless.legacyui.Helper.KeyboardHelper;
import useless.legacyui.ModSettings;
import useless.legacyui.Sorting.LegacyCategoryManager;

public class GuiLegacyCrafting extends GuiContainer {
    protected int craftingSize;
    private static int GUIx;
    private static int GUIy;
    public static int currentTab = 0;
    public GuiLegacyCrafting(InventoryPlayer inventoryplayer, int craftingSize){
        super((new LegacyContainerCrafting(inventoryplayer, craftingSize)));
        this.craftingSize = craftingSize;
    }
    public GuiLegacyCrafting(InventoryPlayer inventoryplayer, World world, int x, int y, int z, int craftingSize) {
        super(new LegacyContainerCrafting(inventoryplayer, world, x, y, z, craftingSize));
        this.craftingSize = craftingSize;
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
        if (currentTab > 7){
            currentTab -= tabAmount;
        } else if (currentTab < 0){
            currentTab += tabAmount;
        }
    }
    public void handleInputs(){
        boolean shifted = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
        if (KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyRight.keyCode()) || KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLookRight.keyCode())){
            if (shifted){
                scrollTab(1);
            }
        }
        if (KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLeft.keyCode()) || KeyboardHelper.isKeyPressedThisFrame(mc.gameSettings.keyLookLeft.keyCode())){
            if (shifted){
                scrollTab(-1);
            }
        }
    }
    public void initGui() {
        super.initGui();

        // Setup size variables
        this.xSize = 273; // width of texture plus the 17px strip that was cut off
        this.ySize = 175; // height of Gui window
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;

        // Static Initialization
        currentTab = 0;
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
        drawStringCenteredNoShadow(fontRenderer, I18n.getInstance().translateKey("legacyui.guilabel.inventory"),205, 97, ModSettings.Colors.GuiLabelColor());
        drawStringCenteredNoShadow(fontRenderer, I18n.getInstance().translateKey("legacyui.guilabel.crafting"),73, 97, ModSettings.Colors.GuiLabelColor());
        drawStringCenteredNoShadow(fontRenderer, LegacyCategoryManager.recipeCategories.get(currentTab).getTranslatedKey(),xSize/2, 36, ModSettings.Colors.GuiLabelColor());
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick) {
        UtilGui.bindTexture("/assets/legacyui/gui/legacycrafting.png");
        UtilGui.drawTexturedModalRect(this, GUIx, GUIy, 0,0, this.xSize, this.ySize, 1/512f); // Render Background

        int tabWidth = 35;
        UtilGui.drawTexturedModalRect(this, GUIx + (tabWidth - 1) * currentTab, GUIy - 2, 0,175, tabWidth, 30, 1/512f); // Render Selected Tab

        if (craftingSize <= 4){
            UtilGui.drawTexturedModalRect(this, GUIx + 19, GUIy + 108, 61, 175, 54, 54, 1/512f);
        }

        UtilGui.bindTexture("/assets/legacyui/gui/icons.png");
        for (int i = 0; i < 8; i++) {
            UtilGui.drawIconTexture(this, GUIx + 5 + (tabWidth - 1) * i, GUIy + 2, LegacyCategoryManager.recipeCategories.get(i).iconCoordinate, 0.75f); // Render Icon
        }

    }
}
