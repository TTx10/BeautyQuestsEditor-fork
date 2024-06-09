package EcoMineVN.BeautyQuestsEditor.Quests;

import org.bukkit.inventory.ItemStack;

public class QuestsEditor {
  private String id;
  
  private int beautyQuestsId;
  
  private int rate;
  
  private int Points;
  
  private ItemStack icon;
  
  private String permission;
  
  public QuestsEditor(String id, int beautyQuestsId, int rate, ItemStack icon, int Points, String permission) {
    this.id = id;
    this.beautyQuestsId = beautyQuestsId;
    this.rate = rate;
    this.icon = icon;
    this.Points = Points;
    this.permission = permission;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public void setPermission(String permission) {
    this.permission = permission;
  }
  
  public int getPoints() {
    return this.Points;
  }
  
  public void setPoints(int points) {
    this.Points = points;
  }
  
  public String getId() {
    return this.id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public int getBeautyQuestsId() {
    return this.beautyQuestsId;
  }
  
  public void setBeautyQuestsId(int beautyQuestsId) {
    this.beautyQuestsId = beautyQuestsId;
  }
  
  public int getRate() {
    return this.rate;
  }
  
  public void setRate(int rate) {
    this.rate = rate;
  }
  
  public ItemStack getIcon() {
    return this.icon;
  }
  
  public void setIcon(ItemStack icon) {
    this.icon = icon;
  }
}
