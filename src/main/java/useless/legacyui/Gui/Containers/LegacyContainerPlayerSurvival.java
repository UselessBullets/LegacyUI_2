package useless.legacyui.Gui.Containers;

import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotArmor;
import net.minecraft.core.player.inventory.slot.SlotCrafting;

import java.util.List;

public class LegacyContainerPlayerSurvival extends ContainerPlayer {
    public LegacyContainerPlayerSurvival(InventoryPlayer inventory, boolean isSinglePlayer) {
        super(inventory, isSinglePlayer);

        inventorySlots.clear(); // Remove all slots made in super class

        for (int index = 0; index < 4; ++index) { // Create Armor Slots
            this.addSlot(new SlotArmor(this, inventory, inventory.getSizeInventory() - 1 - index, 8, 8 + index * 18, index));
        }
        for (int row = 0; row < 3; ++row) { // Create Main Inventory Slots
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(inventory, column + (row + 1) * 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for (int column = 0; column < 9; ++column) { // Create Hotbar slots
            this.addSlot(new Slot(inventory, column, 8 + column * 18, 142));
        }
    }
    @Override
    public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
        if (slot.id >= 0 && slot.id <= 3) { // Armor Slots
            return this.getSlots(0, 4, false);
        }
        if (inventoryAction == InventoryAction.MOVE_SIMILAR) { // Search Inventory for similar action
            if (slot.id >= 4 && slot.id <= 39) { // Entire Inventory
                return this.getSlots(4, 36, false);
            }
        } else {
            if (slot.id >= 4 && slot.id <= 30) {  // Main Inventory
                return this.getSlots(4, 27, false);
            }
            if (slot.id >= 31 && slot.id <= 39) { // Hotbar
                return this.getSlots(31, 9, false);
            }
        }
        return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int target, EntityPlayer entityPlayer) {
        if (slot.id >= 4 && slot.id <= 39) { // Entire Inventory
            if (target == 2) { // Target is armor slots
                return this.getSlots(0, 4, false);
            }
            if (slot.id < 31) { // If from Main Inventory goto hotbar
                return this.getSlots(31, 9, false);
            }
            return this.getSlots(4, 27, false); // Else goto main inventory
        }
        return this.getSlots(4, 36, false); // Armor slots to rest of inventory
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return super.isUsableByPlayer(entityPlayer);
    }
}
