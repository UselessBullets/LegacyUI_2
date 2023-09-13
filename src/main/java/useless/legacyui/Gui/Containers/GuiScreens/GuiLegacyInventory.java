package useless.legacyui.Gui.Containers.GuiScreens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiInventory;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.Lighting;
import net.minecraft.core.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import useless.legacyui.Mixins.GuiInventoryAccessor;
import useless.prismaticlibe.gui.GuiAuditoryButtons;

public class GuiLegacyInventory extends GuiInventory {
    private static int GUIx;
    private static int GUIy;
    protected GuiAuditoryButtons craftButton;
    public GuiLegacyInventory(EntityPlayer player) {
        super(player);
    }
    public void initGui() {
        super.initGui();

        // Setup size variables
        xSize = 176;
        ySize = 176;
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;

        // Offset Armor Button
        GuiButton armorButton = ((GuiInventoryAccessor)this).getArmorButton();
        armorButton.xPosition += 44;
        armorButton.yPosition -= 5;

        // Create Crafting Button
        craftButton = new GuiAuditoryButtons(10, GUIx + 138, GUIy + 33, 20, 21, "");
        craftButton.setMuted(true);
        craftButton.visible = false;
        controlList.add(craftButton);

    }
    protected void buttonPressed(GuiButton guibutton) {
        super.buttonPressed(guibutton);
        if (guibutton == craftButton){
            openCrafting();
        }
    }
    protected void openCrafting(){
        this.onGuiClosed(); //TODO Make it open the crafting GUI
        mc.currentScreen = null;
    }
    //
    // Rendering
    //
    public void drawScreen(int x, int y, float renderPartialTicks) {
        super.drawScreen(x,y,renderPartialTicks);

        bindTexture("/assets/legacyui/gui/legacyinventory.png");
        this.drawTexturedModalRect(craftButton.xPosition, craftButton.yPosition, 177, craftButton.isHovered(x,y) ? 77:54, craftButton.width, craftButton.height); // Crafting Button Render

    }
    protected void drawGuiContainerForegroundLayer() {}
    protected void drawGuiContainerBackgroundLayer(float f) {
        bindTexture("/assets/legacyui/gui/legacyinventory.png");
        this.drawTexturedModalRect(GUIx, GUIy, 0, 0, this.xSize, this.ySize);

        renderPlayerDoll();
    }
    private void renderPlayerDoll(){
        GL11.glEnable(32826);
        GL11.glEnable(2903);
        GL11.glEnable(2929);
        GL11.glPushMatrix();
        GL11.glTranslatef(GUIx + 51 + 44, GUIy + 75 - 5, 50.0f);
        float f1 = 30.0f;
        GL11.glScalef(-f1, f1, f1);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        float f2 = this.mc.thePlayer.renderYawOffset;
        float f3 = this.mc.thePlayer.yRot;
        float f4 = this.mc.thePlayer.xRot;
        float f5 = (float)(GUIx + 51 + 44) - this.xSize_lo;
        float f6 = (float)(GUIy + 75 - 50 - 5) - this.ySize_lo;
        GL11.glRotatef(135.0f, 0.0f, 1.0f, 0.0f);
        Lighting.enableLight();
        GL11.glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-((float)Math.atan(f6 / 40.0f)) * 20.0f, 1.0f, 0.0f, 0.0f);
        this.mc.thePlayer.renderYawOffset = (float)Math.atan(f5 / 40.0f) * 20.0f;
        this.mc.thePlayer.yRot = (float)Math.atan(f5 / 40.0f) * 40.0f;
        this.mc.thePlayer.xRot = -((float)Math.atan(f6 / 40.0f)) * 20.0f;
        this.mc.thePlayer.entityBrightness = 1.0f;
        GL11.glTranslatef(0.0f, this.mc.thePlayer.heightOffset, 0.0f);
        EntityRenderDispatcher.instance.viewLerpYaw = 180.0f;
        EntityRenderDispatcher.instance.renderEntityWithPosYaw(this.mc.thePlayer, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        this.mc.thePlayer.entityBrightness = 0.0f;
        this.mc.thePlayer.renderYawOffset = f2;
        this.mc.thePlayer.yRot = f3;
        this.mc.thePlayer.xRot = f4;
        GL11.glPopMatrix();
        Lighting.disable();
        GL11.glDisable(32826);
    }
    private void bindTexture(String texture){
        int inventoryTex = this.mc.renderEngine.getTexture(texture);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(inventoryTex);
    }
}
