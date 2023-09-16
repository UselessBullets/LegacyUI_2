package useless.legacyui.Gui.GuiScreens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiInventory;
import net.minecraft.core.entity.player.EntityPlayer;
import useless.legacyui.Gui.Containers.LegacyContainerPlayerCreative;
import useless.legacyui.LegacyUI;
import useless.legacyui.Mixins.Gui.GuiInventoryAccessor;

public class GuiLegacyCreative extends GuiInventory {
    private static int GUIx;
    private static int GUIy;
    private static final int guiTextureWidth = 512;
    private static final int tabWidth = 35;
    protected LegacyContainerPlayerCreative container;
    public GuiLegacyCreative(EntityPlayer player) {
        super(player);
        this.container = (LegacyContainerPlayerCreative)player.inventorySlots;

    }

    @Override
    public void initGui() {
        // Setup size variables
        this.xSize = 273;
        this.ySize = 184;
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;
    }
    protected void drawGuiContainerForegroundLayer(){
    }
    protected void drawGuiContainerBackgroundLayer(float renderPartialTick) {
        UtilGui.bindTexture("/assets/legacyui/gui/legacycreative.png");
        UtilGui.drawTexturedModalRect(this, GUIx,GUIy, 0, 0, xSize, ySize,1f/guiTextureWidth);
    }
}
