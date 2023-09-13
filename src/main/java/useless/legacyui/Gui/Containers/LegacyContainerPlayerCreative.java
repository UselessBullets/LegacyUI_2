package useless.legacyui.Gui.Containers;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.SlotCreative;

import java.util.ArrayList;
import java.util.List;

public class LegacyContainerPlayerCreative extends LegacyContainerPlayerSurvival{
    public int page = 0;
    public int maxPage;
    protected int creativeSlotsStart;
    protected List<ItemStack> searchedItems = new ArrayList<ItemStack>();
    public String searchText = "";
    public static List<ItemStack> creativeItems = new ArrayList<ItemStack>();
    public static int creativeItemsCount;
    public LegacyContainerPlayerCreative(InventoryPlayer inventory, boolean isSinglePlayer) {
        super(inventory, isSinglePlayer);
        this.creativeSlotsStart = this.inventorySlots.size();
        for (int i = 0; i < 36; ++i) {
            int x = i % 6;
            int y = i / 6;
            this.addSlot(new SlotCreative(this.creativeSlotsStart + i, 174 + x * 18, 30 + y * 18, creativeItems.get(i)));
        }
        this.searchPage("");
    }
    public void setInventoryStatus(int page, String searchText) {
        if (this.page != page) {
            this.page = page;
            this.updatePage();
        }
        if (!this.searchText.equals(searchText)) {
            this.searchText = searchText;
            this.searchPage(searchText);
        }
    }

    public void lastPage() {
        if (this.page == 0) {
            return;
        }
        --this.page;
        this.updatePage();
    }

    public void nextPage() {
        if (this.page == this.maxPage) {
            return;
        }
        ++this.page;
        this.updatePage();
    }

    public void searchPage(String search) {
        this.searchText = search;
        this.searchedItems.clear();
        this.page = 0;
        I18n t = I18n.getInstance();
        for (int i = 0; i < creativeItemsCount; ++i) {
            if (!t.translateNameKey(creativeItems.get(i).getItemName()).toLowerCase().contains(search.toLowerCase())) continue;
            this.searchedItems.add(creativeItems.get(i));
        }
        this.updatePage();
    }

    protected void updatePage() {
        this.maxPage = this.searchedItems.size() / 36;
        if (this.searchedItems.size() % 36 == 0) {
            --this.maxPage;
        }
        if (this.maxPage == -1) {
            this.maxPage = 0;
        }
        for (int i = 0; i < 36; ++i) {
            ((SlotCreative)this.inventorySlots.get((int)(this.creativeSlotsStart + i))).item = i + this.page * 36 >= this.searchedItems.size() ? null : this.searchedItems.get(i + this.page * 36);
        }
        this.playerInv.player.updateCreativeInventory(this.page, this.searchText);
    }

    public String getSearchText() {
        return this.searchText;
    }

    @Override
    public int getHotbarSlotId(int number) {
        return number + 8 + 27;
    }
}
