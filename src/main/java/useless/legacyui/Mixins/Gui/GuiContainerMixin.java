package useless.legacyui.Mixins.Gui;

import net.minecraft.client.gui.GuiContainer;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.legacyui.LegacySoundManager;
import useless.legacyui.ModSettings;

@Mixin(value = GuiContainer.class, remap = false)
public class GuiContainerMixin extends GuiScreen {
    @Inject(method = "onGuiClosed()V", at = @At("HEAD"))
    private void closingSound(CallbackInfo cbi){
        if (ModSettings.Sounds.UseLegacySounds()){
            LegacySoundManager.play.back(false);
        }
    }
}
