package EcoMineVN.BeautyQuestsEditor.Quests;

import EcoMineVN.BeautyQuestsEditor.Config.ConfigAPI;
import EcoMineVN.BeautyQuestsEditor.Utils.UtilsMessages;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class QuestsInventory implements ConfigAPI {
  private HashMap<ItemStack, RoleItem> itemPage = new HashMap<>();
  
  private HashMap<ItemStack, QuestsEditor> itemQuests = new HashMap<>();
  
  private int page = 0;
  
  private Player player;
  
  private List<List<ItemStack>> listItemPage = new ArrayList<>();
  
  private QuestsGroup questsGroup;
  
  private Inventory inventory;
  
  public void OpenInventory(boolean update) {
    int quests = 0;
    int slot = 0;
    List<String> questsGuiEdit = this.questsGroup.getListguiEdit();
    HashMap<String, ItemStackQuests> itemStackQuests = this.questsGroup.getLiItemStackQuests();
    List<ItemStack> lQuestsEditors = this.listItemPage.get(this.page);
    List<String> listIdPlayer = QuestsData.getConfig().getStringList(this.questsGroup.getId());
    for (int i = 0; i < questsGuiEdit.size(); i++) {
      char[] array = ((String)questsGuiEdit.get(i)).toCharArray();
      for (int j = 0; j < array.length; j++) {
        String value = String.valueOf(array[j]);
        if (update) {
          if (!itemStackQuests.containsKey(value))
            if (quests < lQuestsEditors.size()) {
              if (!listIdPlayer.contains(((QuestsEditor)this.itemQuests.get(lQuestsEditors.get(quests))).getId())) {
                this.inventory.setItem(slot, lQuestsEditors.get(quests));
              } else {
                this.inventory.setItem(slot, UtilsMessages.FinishItem);
              } 
              quests++;
            } else {
              this.inventory.setItem(slot, new ItemStack(Material.AIR));
            }  
        } else if (itemStackQuests.containsKey(value)) {
          this.inventory.setItem(slot, ((ItemStackQuests)itemStackQuests.get(value)).getItem());
          this.itemPage.put(((ItemStackQuests)itemStackQuests.get(value)).getItem(), ((ItemStackQuests)itemStackQuests.get(value)).getRoleItem());
        } else if (quests < lQuestsEditors.size()) {
          if (!listIdPlayer.contains(((QuestsEditor)this.itemQuests.get(lQuestsEditors.get(quests))).getId())) {
            this.inventory.setItem(slot, lQuestsEditors.get(quests));
          } else {
            this.inventory.setItem(slot, UtilsMessages.FinishItem);
          } 
          quests++;
        } 
        slot++;
      } 
    } 
    if (!update)
      this.player.openInventory(this.inventory); 
  }
  
  public Player getPlayer() {
    return this.player;
  }
  
  public void setPlayer(Player player) {
    this.player = player;
  }
  
  public Inventory getInventory() {
    return this.inventory;
  }
  
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }
  
  public HashMap<ItemStack, RoleItem> getItemPage() {
    return this.itemPage;
  }
  
  public void setItemPage(HashMap<ItemStack, RoleItem> itemPage) {
    this.itemPage = itemPage;
  }
  
  public HashMap<ItemStack, QuestsEditor> getItemQuests() {
    return this.itemQuests;
  }
  
  public void setItemQuests(HashMap<ItemStack, QuestsEditor> itemQuests) {
    this.itemQuests = itemQuests;
  }
  
  public int getPage() {
    return this.page;
  }
  
  public void setPage(int page) {
    this.page = page;
  }
  
  public List<List<ItemStack>> getListItemPage() {
    return this.listItemPage;
  }
  
  public void setListItemPage(List<List<ItemStack>> listItemPage) {
    this.listItemPage = listItemPage;
  }
  
  public QuestsGroup getQuestsGroup() {
    return this.questsGroup;
  }
  
  public void setQuestsGroup(QuestsGroup questsGroup) {
    this.questsGroup = questsGroup;
  }
}
