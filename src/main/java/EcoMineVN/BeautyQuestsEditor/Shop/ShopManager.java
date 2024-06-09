package EcoMineVN.BeautyQuestsEditor.Shop;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopManager {
  private HashMap<String, ShopGroup> listShopGroup = new HashMap<>();
  
  private HashMap<Player, ShopInventory> PlayerShop = new HashMap<>();
  
  public HashMap<String, ShopGroup> getListShopGroup() {
    return this.listShopGroup;
  }
  
  public HashMap<Player, ShopInventory> getPlayerShop() {
    return this.PlayerShop;
  }
  
  public void addShopGroup(ShopGroup shopGroup) {
    this.listShopGroup.put(shopGroup.getId(), shopGroup);
  }
  
  public void loadInventory(String group, Player p) {
    if (this.listShopGroup.containsKey(group)) {
      ShopGroup shopGroup = this.listShopGroup.get(group);
      ShopInventory shopInventory = new ShopInventory();
      HashMap<Integer, List<String>> guiPage = shopGroup.getGuiPage();
      HashMap<String, ItemStackShop> listItem = shopGroup.getListItemStackShop();
      Inventory inv = Bukkit.createInventory(null, ((List)guiPage.get(Integer.valueOf(1))).size() * 9, shopGroup.getGuiName());
      for (int g = 0; g < guiPage.size(); g++) {
        for (int i = 0; i < ((List)guiPage.get(Integer.valueOf(g + 1))).size(); i++) {
          char[] array = ((String)((List<String>)guiPage.get(Integer.valueOf(g + 1))).get(i)).toCharArray();
          for (int j = 0; j < array.length; j++) {
            String value = String.valueOf(array[j]);
            if (listItem.containsKey(value))
              shopInventory.getItemShop().put(((ItemStackShop)listItem.get(value)).getItem(), listItem.get(value)); 
          } 
        } 
      } 
      shopInventory.setPlayer(p);
      shopInventory.setShopGroup(shopGroup);
      shopInventory.setInventory(inv);
      this.PlayerShop.put(p, shopInventory);
    } 
  }
}
