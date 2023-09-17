package useless.legacyui.Gui.GuiElements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiElement;
import net.minecraft.client.render.FontRenderer;
import useless.legacyui.Gui.GuiScreens.UtilGui;
import useless.legacyui.ModSettings;

public class GuiButtonPrompt extends GuiRegion implements GuiElement {
    private static final int buttonAtlasWidth = 256;
    private FontRenderer fontRenderer;
    public String prompt;
    public int[] buttonCoordinates;
    public int spacing;
    public GuiButtonPrompt(FontRenderer fontRenderer, int id, int xPosition, int yPosition, int[] buttonCoordinates, int spacing, String prompt) {
        super(id, xPosition, yPosition, 0, 0);
        this.fontRenderer = fontRenderer;
        this.prompt = prompt;
        this.buttonCoordinates = buttonCoordinates;
        this.spacing = spacing;
        this.width = 13 + spacing + fontRenderer.getStringWidth(prompt);
        this.height = 13;
    }
    public void drawPrompt(Minecraft minecraft, int mouseX, int mouseY){
        UtilGui.bindTexture("/assets/legacyui/gui/Controller/buttons.png");
        int u = buttonCoordinates[0] * 13;
        int v = buttonCoordinates[1] * 13;
        UtilGui.drawTexturedModalRect(this, xPosition, yPosition, u, v, 13, 13, 1f/buttonAtlasWidth);
        fontRenderer.drawString(prompt, xPosition + 13 + spacing, yPosition, ModSettings.Colors.GuiLabelColor());
    }
}
