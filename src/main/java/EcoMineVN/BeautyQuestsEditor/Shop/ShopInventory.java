package EcoMineVN.BeautyQuestsEditor.Shop;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopInventory {
  private HashMap<ItemStack, ItemStackShop> itemShop = new HashMap<>();
  
  private Inventory inventory;
  
  private ShopGroup shopGroup;
  
  private Player player;
  
  private int page = 0;
  
  public void OpenInventory(boolean update) {
    int slot = 0;
    HashMap<Integer, List<String>> guiPage = this.shopGroup.getGuiPage();
    HashMap<String, ItemStackShop> listItem = this.shopGroup.getListItemStackShop();
    for (int i = 0; i < ((List)guiPage.get(Integer.valueOf(this.page + 1))).size(); i++) {
      char[] array = ((String)((List<String>)guiPage.get(Integer.valueOf(this.page + 1))).get(i)).toCharArray();
      for (int j = 0; j < array.length; j++) {
        String value = String.valueOf(array[j]);
        if (listItem.containsKey(value)) {
          this.inventory.setItem(slot, ((ItemStackShop)listItem.get(value)).getItem());
        } else {
          this.inventory.setItem(slot, new ItemStack(Material.AIR));
        } 
        slot++;
      } 
    } 
    if (!update)
      this.player.openInventory(this.inventory); 
  }
  
  public int getPage() {
    return this.page;
  }
  
  public void setPage(int page) {
    this.page = page;
  }
  
  public Player getPlayer() {
    return this.player;
  }
  
  public void setPlayer(Player player) {
    this.player = player;
  }
  
  public HashMap<ItemStack, ItemStackShop> getItemShop() {
    return this.itemShop;
  }
  
  public void setItemShop(HashMap<ItemStack, ItemStackShop> itemShop) {
    this.itemShop = itemShop;
  }
  
  public ShopGroup getShopGroup() {
    return this.shopGroup;
  }
  
  public void setShopGroup(ShopGroup shopGroup) {
    this.shopGroup = shopGroup;
  }
  
  public Inventory getInventory() {
    return this.inventory;
  }
  
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }
}
