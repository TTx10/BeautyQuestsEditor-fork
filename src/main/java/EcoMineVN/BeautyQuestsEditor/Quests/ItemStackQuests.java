package EcoMineVN.BeautyQuestsEditor.Quests;

import org.bukkit.inventory.ItemStack;

public class ItemStackQuests {
  private RoleItem roleItem;
  
  private ItemStack item;
  
  private String id;
  
  public ItemStackQuests(RoleItem roleItem, ItemStack item, String id) {
    this.roleItem = roleItem;
    this.item = item;
    this.id = id;
  }
  
  public RoleItem getRoleItem() {
    return this.roleItem;
  }
  
  public void setRoleItem(RoleItem roleItem) {
    this.roleItem = roleItem;
  }
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public void setItem(ItemStack item) {
    this.item = item;
  }
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
}
