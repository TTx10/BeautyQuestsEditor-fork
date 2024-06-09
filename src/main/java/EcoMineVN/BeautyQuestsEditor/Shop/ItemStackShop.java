package EcoMineVN.BeautyQuestsEditor.Shop;

import org.bukkit.inventory.ItemStack;

public class ItemStackShop {
  private String id;
  
  private String saveId;
  
  private String permission;
  
  private int price;
  
  private int amount;
  
  private ItemStack item;
  
  private ItemShopRole itemShopRole;
  
  public ItemStackShop(String id, String saveId, String permission, int price, int amount, ItemShopRole itemShopRole, ItemStack item) {
    this.id = id;
    this.saveId = saveId;
    this.permission = permission;
    this.price = price;
    this.amount = amount;
    this.itemShopRole = itemShopRole;
    this.item = item;
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
  
  public String getSaveId() {
    return this.saveId;
  }
  
  public void setSaveId(String saveId) {
    this.saveId = saveId;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public void setPermission(String permission) {
    this.permission = permission;
  }
  
  public int getPrice() {
    return this.price;
  }
  
  public void setPrice(int price) {
    this.price = price;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  public ItemShopRole getItemShopRole() {
    return this.itemShopRole;
  }
  
  public void setItemShopRole(ItemShopRole itemShopRole) {
    this.itemShopRole = itemShopRole;
  }
}
