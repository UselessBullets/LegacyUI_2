package useless.legacyui.Gui.GuiScreens;

import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.ContainerWorkbench;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.world.World;
import useless.legacyui.Gui.Containers.LegacyContainerCrafting;
import useless.prismaticlibe.gui.slot.SlotResizable;

public class GuiLegacyCrafting extends GuiContainer {
    protected int craftingSize;
    private static int GUIx;
    private static int GUIy;
    public GuiLegacyCrafting(InventoryPlayer inventoryplayer, World world, int x, int y, int z, int craftingSize) {
        super(new LegacyContainerCrafting(inventoryplayer, world, x, y, z, craftingSize));
        this.craftingSize = craftingSize;
    }
    public void initGui() {
        super.initGui();

        // Setup size variables
        this.xSize = 273; // width of texture plus the 17px strip that was cut off
        this.ySize = 175; // height of Gui window
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;
    }
    public void onGuiClosed() {
        super.onGuiClosed();
        this.inventorySlots.onCraftGuiClosed(this.mc.thePlayer);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick) {
        UtilGui.bindTexture("/assets/legacyui/gui/legacycrafting.png");
        UtilGui.drawTexturedModalRect(this, GUIx, GUIy, 0,0, this.xSize, this.ySize, 1/512f); // Render Background
        UtilGui.bindTexture("/assets/legacyui/gui/icons.png");
        for (int i = 0; i < 8; i++) {
            UtilGui.drawIconTexture(this, GUIx + 5 + 34*i, GUIy + 2, i+1,0, 0.75f); // Render Icon
        }

    }
}
