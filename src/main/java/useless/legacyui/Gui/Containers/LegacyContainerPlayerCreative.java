package useless.legacyui.Gui.Containers;

import net.minecraft.core.player.inventory.ContainerPlayerCreative;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotArmor;
import net.minecraft.core.player.inventory.slot.SlotCreative;
import useless.legacyui.Gui.Slots.SlotNull;

public class LegacyContainerPlayerCreative extends ContainerPlayerCreative {
    public static int slotsWide = 13;
    public static int slotsTall = 6;
    public static InventoryPlayer inventory;
    public LegacyContainerPlayerCreative(InventoryPlayer inventory, boolean isSinglePlayer) {
        super(inventory, isSinglePlayer);
        this.inventory = inventory;
        createSlots();
    }
    public void createSlots() {
        inventorySlots.clear(); // Remove all slots made in super class
        for (int index = 0; index < 5; ++index){ // Null Slots to keep alignment with server
            this.addSlot(new SlotNull(this.playerInv,index, -5000, -5000));
        }
        for (int index = 0; index < 4; ++index) { // Create Armor Slots
            this.addSlot(new SlotNull(this.playerInv, index + 5, -5000, -5000));
        }
        for (int row = 0; row < 3; ++row) { // Create Main Inventory Slots
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new SlotNull(this.playerInv, row * 9 + column + 9, -5000,-5000));
            }
        }
        for (int column = 0; column < 9; ++column) { // Create Hotbar slots
            this.addSlot(new Slot(inventory, column, 48 + column * 18, 160));
        }
        this.creativeSlotsStart = this.inventorySlots.size();
        for (int i = 0; i < slotsWide * slotsTall; ++i) {
            int x = i % slotsWide;
            int y = i / slotsWide;
            this.addSlot(new SlotCreative(this.creativeSlotsStart + i, 12 + x * 18, 46 + y * 18, ContainerPlayerCreative.creativeItems.get(i)));
        }
    }
}
