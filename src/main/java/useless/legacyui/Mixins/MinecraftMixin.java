package useless.legacyui.Mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.client.gui.GuiInventory;
import net.minecraft.client.gui.GuiInventoryCreative;
import net.minecraft.core.player.gamemode.Gamemode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import useless.legacyui.Gui.GuiScreens.GuiLegacyCreative;
import useless.legacyui.Gui.GuiScreens.GuiLegacyInventory;
import useless.legacyui.ModSettings;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin {
    @Shadow
    public EntityPlayerSP thePlayer;
    @Inject(method = "getGuiInventory()Lnet/minecraft/client/gui/GuiInventory;", at = @At("RETURN"), cancellable = true)
    private void useCustomInventoryGuis(CallbackInfoReturnable<GuiInventory> cir){
        if (thePlayer.getGamemode() == Gamemode.creative && ModSettings.Gui.EnableLegacyInventoryCreative()){
            cir.setReturnValue(new GuiLegacyCreative(thePlayer));
        }
        if (thePlayer.getGamemode() == Gamemode.survival && ModSettings.Gui.EnableLegacyInventorySurvival()){
            cir.setReturnValue(new GuiLegacyInventory(thePlayer));
        }
    }
}
