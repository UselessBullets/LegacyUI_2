package useless.legacyui.Mixins.Network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiInventory;
import net.minecraft.client.gui.GuiInventoryCreative;
import net.minecraft.client.net.handler.NetClientHandler;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import useless.legacyui.LegacyUI;

import java.util.Arrays;

@Mixin(value = NetClientHandler.class, remap = false)
public class NetClientHandlerMixin {
    @Shadow
    private Minecraft mc;
    @Redirect(method = "handleWindowItems(Lnet/minecraft/core/net/packet/Packet104WindowItems;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/Container;putStacksInSlots([Lnet/minecraft/core/item/ItemStack;)V"))
    private void inventorySlotsSpoofing(Container container, ItemStack[] aitemstack){
        /*if (mc.thePlayer.inventory instanceof GuiInventory && !(mc.currentScreen instanceof GuiInventoryCreative)){
            container.putStacksInSlots(invSurvivalServerToClient(aitemstack));
        }
        else {
            container.putStacksInSlots(aitemstack);
        }*/
    }

    @Unique
    private ItemStack[] invSurvivalServerToClient(ItemStack[] aitemstack){
        ItemStack[] returnStacks = new ItemStack[27+9+4];
        System.arraycopy(aitemstack, 5, returnStacks,0, returnStacks.length);
        return returnStacks;
    }
}
