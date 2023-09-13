package useless.legacyui.Gui.Containers;

import net.minecraft.core.InventoryAction;
import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.CraftingManager;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.*;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotArmor;
import net.minecraft.core.player.inventory.slot.SlotCrafting;
import net.minecraft.core.world.World;
import useless.legacyui.Gui.Slots.SlotNull;
import useless.prismaticlibe.gui.slot.SlotResizable;

import java.util.List;

public class LegacyContainerCrafting extends Container {
    public InventoryCrafting craftMatrix;
    public IInventory craftResult = new InventoryCraftResult();
    private World world;
    private int x;
    private int y;
    private int z;
    private int craftingSize;
    private InventoryPlayer inventoryPlayer;
    public LegacyContainerCrafting(InventoryPlayer inventoryplayer, int craftingSize) {
        if (craftingSize <= 4){
            craftMatrix = new InventoryCrafting(this, 2, 2);
        } else {
            craftMatrix = new InventoryCrafting(this, 3, 3);
        }
        this.world = null;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.craftingSize = craftingSize;
        this.inventoryPlayer = inventoryplayer;
        craftingSlots();
    }
    public LegacyContainerCrafting(InventoryPlayer inventoryplayer, World world, int x, int y, int z, int craftingSize) {
        if (craftingSize <= 4){
            craftMatrix = new InventoryCrafting(this, 2, 2);
        } else {
            craftMatrix = new InventoryCrafting(this, 3, 3);
        }
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.craftingSize = craftingSize;
        this.inventoryPlayer = inventoryplayer;
        craftingSlots();
    }
    public void craftingSlots() {
        this.addSlot(new SlotCrafting(this.inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 107, 127));
        int baseIterator;
        int subIterator;

        if (craftingSize <= 4){
            // 2x2 Crafting
            for (baseIterator = 0; baseIterator < 2; ++baseIterator) {
                for (subIterator = 0; subIterator < 2; ++subIterator) {
                    this.addSlot(new Slot(this.craftMatrix, subIterator + baseIterator * 2, 29 + subIterator * 18, 118 + baseIterator * 18));
                }
            }
            for (baseIterator = 0; baseIterator < 4; ++baseIterator) {
                this.addSlot(new SlotNull(inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - baseIterator, -5000, -5000));
            }
        }
        else {
            // 3x3 Crafting
            for (baseIterator = 0; baseIterator < 3; ++baseIterator) {
                for (subIterator = 0; subIterator < 3; ++subIterator) {
                    this.addSlot(new Slot(this.craftMatrix, subIterator + baseIterator * 3, 20 + subIterator * 18, 109 + baseIterator * 18));
                }
            }
        }
        // 3x9 inventory
        for (baseIterator = 0; baseIterator < 3; ++baseIterator) {
            for (subIterator = 0; subIterator < 9; ++subIterator) {
                this.addSlot(new SlotResizable(this.inventoryPlayer, subIterator + baseIterator * 9 + 9, 153 + subIterator * 12, 112 + baseIterator * 12, 12));
            }
        }
        // 1x9 hotbar
        for (baseIterator = 0; baseIterator < 9; ++baseIterator) {
            this.addSlot(new SlotResizable(this.inventoryPlayer, baseIterator, 153 + baseIterator * 12, 154, 12));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }
    public void onCraftMatrixChanged(IInventory iinventory) {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix));
    }
    public boolean isUsableByPlayer(EntityPlayer entityplayer) {
        if (craftingSize <=4 ){ // Inventory Crafting
            return true;
        }
        if (this.world.getBlockId(this.x, this.y, this.z) != Block.workbench.id) { // Supplied coords are not a crafting table
            return false;
        }
        return entityplayer.distanceToSqr((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) <= 64.0; // Is close enough to table
    }
    public List<Integer> getMoveSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (craftingSize > 4){
            if (slot.id == 0) {
                return this.getSlots(0, 1, false);
            }
            if (slot.id >= 1 && slot.id < 9) {
                return this.getSlots(1, 9, false);
            }
            if (action == InventoryAction.MOVE_SIMILAR) {
                if (slot.id >= 10 && slot.id <= 45) {
                    return this.getSlots(10, 36, false);
                }
            } else {
                if (slot.id >= 10 && slot.id <= 36) {
                    return this.getSlots(10, 27, false);
                }
                if (slot.id >= 37 && slot.id <= 45) {
                    return this.getSlots(37, 9, false);
                }
            }
        } else {
            if (slot.id == 0) {
                return this.getSlots(0, 1, false);
            }
            if (slot.id >= 1 && slot.id <= 4) {
                return this.getSlots(1, 4, false);
            }
            if (slot.id >= 5 && slot.id <= 8) {
                return this.getSlots(5, 4, false);
            }
            if (action == InventoryAction.MOVE_SIMILAR) {
                if (slot.id >= 9 && slot.id <= 44) {
                    return this.getSlots(9, 36, false);
                }
            } else {
                if (slot.id >= 9 && slot.id <= 35) {
                    return this.getSlots(9, 27, false);
                }
                if (slot.id >= 36 && slot.id <= 44) {
                    return this.getSlots(36, 9, false);
                }
            }
        }
        return null;

    }
    public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (craftingSize > 4){
            if (slot.id >= 10 && slot.id <= 45) {
                if (target == 1) {
                    return this.getSlots(1, 9, false);
                }
                if (slot.id >= 10 && slot.id <= 36) {
                    return this.getSlots(37, 9, false);
                }
                if (slot.id >= 37 && slot.id <= 45) {
                    return this.getSlots(10, 27, false);
                }
            } else {
                if (slot.id == 0) {
                    return this.getSlots(10, 36, true);
                }
                return this.getSlots(10, 36, false);
            }
            return null;
        } {
            if (slot.id >= 9 && slot.id <= 44) {
                if (target == 1) {
                    return this.getSlots(1, 4, false);
                }
                if (target == 2) {
                    return this.getSlots(5, 4, false);
                }
                if (slot.id < 36) {
                    return this.getSlots(36, 9, false);
                }
                return this.getSlots(9, 27, false);
            }
            if (slot.id == 0) {
                return this.getSlots(9, 36, true);
            }
            return this.getSlots(9, 36, false);
        }

    }
}
