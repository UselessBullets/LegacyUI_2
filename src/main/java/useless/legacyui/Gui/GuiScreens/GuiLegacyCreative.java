package useless.legacyui.Gui.GuiScreens;

import net.minecraft.client.gui.GuiInventory;
import net.minecraft.core.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import useless.legacyui.Gui.Containers.LegacyContainerPlayerCreative;
import useless.legacyui.Gui.GuiElements.Buttons.GuiAuditoryButton;
import useless.legacyui.Gui.GuiElements.GuiRegion;
import useless.legacyui.Helper.KeyboardHelper;
import useless.legacyui.LegacySoundManager;
import useless.legacyui.ModSettings;
import useless.legacyui.Sorting.LegacyCategoryManager;

public class GuiLegacyCreative extends GuiInventory {
    private static int GUIx;
    private static int GUIy;
    private static final int guiTextureWidth = 512;
    private static final int tabWidth = 35;
    public static int currentTab = 0;
    public static int currentRow = 0;
    private static float scrollProgress = 0f;
    protected LegacyContainerPlayerCreative container;
    protected GuiRegion scrollBar;
    protected GuiAuditoryButton clearButton;
    public GuiLegacyCreative(EntityPlayer player) {
        super(player);
        this.container = (LegacyContainerPlayerCreative)player.inventorySlots;
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
        if (currentTab != value){
            LegacySoundManager.play.focus(true);
        }
        currentTab = value;
        int tabAmount = Math.min(8, LegacyCategoryManager.creativeCategories.size());
        if (currentTab > tabAmount-1){
            currentTab -= tabAmount;
        } else if (currentTab < 0){
            currentTab += tabAmount;
        }
        currentTab = Math.min(currentTab, tabAmount-1);
        currentRow = 0;
        setContainerSlots();
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
    public void setContainerSlots(){
        container.setSlots();
    }
    @Override
    public void initGui() {
        this.controlList.clear();
        // Setup size variables
        this.xSize = 273;
        this.ySize = 184;
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;

        scrollBar = new GuiRegion(100, GUIx + 251, GUIy + 43, 15, 112);
        clearButton = new GuiAuditoryButton(101, GUIx + 209, GUIy + 159, 18, 18, "X");
        clearButton.visible = false;
        controlList.add(clearButton);

        currentTab = 0;
        currentRow = 0;
        setContainerSlots();
    }
    public void drawScreen(int x, int y, float renderPartialTicks) {
        handleInputs();
        if (scrollBar.isHovered(x,y)){
            if (Mouse.isButtonDown(0)){
                scrollProgress = (y-scrollBar.getY())/ (float)scrollBar.getHeight();
                currentRow = Math.round((LegacyContainerPlayerCreative.getTotalRows() - LegacyContainerPlayerCreative.slotsTall) * scrollProgress);
                if (LegacyContainerPlayerCreative.getTotalRows() <= LegacyContainerPlayerCreative.slotsTall){
                    scrollProgress = 0f;
                }
                currentRow = Math.max(currentRow,0);

                setContainerSlots();
            }
        }
        super.drawScreen(x,y, renderPartialTicks);
        UtilGui.bindTexture("/assets/legacyui/gui/legacycreative.png");
        UtilGui.drawTexturedModalRect(this, clearButton.xPosition, clearButton.yPosition, clearButton.isHovered(x, y) ? 146+18:146, 184, clearButton.width, clearButton.height, 1f/guiTextureWidth); // draw clearbutton
    }
    protected void drawGuiContainerForegroundLayer(){
    }
    protected void drawGuiContainerBackgroundLayer(float renderPartialTick) {
        UtilGui.bindTexture("/assets/legacyui/gui/legacycreative.png");
        UtilGui.drawTexturedModalRect(this, GUIx,GUIy, 0, 0, xSize, ySize,1f/guiTextureWidth); // GUI Background
        UtilGui.drawTexturedModalRect(this, GUIx + (tabWidth - 1) * currentTab, GUIy - 2, 0,184, tabWidth, 30, 1f/guiTextureWidth); // Render Selected Tab

        float scrollProgressLimited = ((float) currentRow) /(LegacyContainerPlayerCreative.getTotalRows()-LegacyContainerPlayerCreative.slotsTall);
        UtilGui.drawTexturedModalRect(this,scrollBar.xPosition, (scrollBar.yPosition + (int) ((scrollBar.height-15)*scrollProgressLimited)),131,184,15,15,1f/guiTextureWidth);

        UtilGui.bindTexture("/assets/legacyui/gui/icons.png");
        for (int i = 0; i < Math.min(LegacyCategoryManager.creativeCategories.size(), 8); i++) {
            UtilGui.drawIconTexture(this, GUIx + 5 + (tabWidth - 1) * i, GUIy + 2, LegacyCategoryManager.creativeCategories.get(i).iconCoordinate, 0.75f); // Render Icon
        }

        drawStringCenteredNoShadow(fontRenderer, LegacyCategoryManager.creativeCategories.get(currentTab).getTranslatedKey(), GUIx + xSize/2, GUIy + 32, ModSettings.Colors.GuiLabelColor());
    }
}
