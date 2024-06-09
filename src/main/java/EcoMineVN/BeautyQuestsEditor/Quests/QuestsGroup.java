package EcoMineVN.BeautyQuestsEditor.Quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestsGroup {
  private List<QuestsEditor> listQuestsEditors = new ArrayList<>();
  
  private List<String> listguiEdit = new ArrayList<>();
  
  private int amount;
  
  private int reset;
  
  private int page;
  
  private String guiName;
  
  private String Id;
  
  private String pointsName;
  
  private HashMap<String, ItemStackQuests> liItemStackQuests = new HashMap<>();
  
  public QuestsGroup(List<QuestsEditor> listQuestsEditors, List<String> listguiEdit, int amount, int reset, int page, String guiName, String id, HashMap<String, ItemStackQuests> liItemStackQuests, String pointsName) {
    this.listQuestsEditors = listQuestsEditors;
    this.listguiEdit = listguiEdit;
    this.amount = amount;
    this.reset = reset;
    this.page = page;
    this.guiName = guiName;
    this.Id = id;
    this.liItemStackQuests = liItemStackQuests;
    this.pointsName = pointsName;
  }
  
  public String getPointsName() {
    return this.pointsName;
  }
  
  public void setPointsName(String pointsName) {
    this.pointsName = pointsName;
  }
  
  public String getId() {
    return this.Id;
  }
  
  public void setId(String id) {
    this.Id = id;
  }
  
  public List<QuestsEditor> getListQuestsEditors() {
    return this.listQuestsEditors;
  }
  
  public void setListQuestsEditors(List<QuestsEditor> listQuestsEditors) {
    this.listQuestsEditors = listQuestsEditors;
  }
  
  public List<String> getListguiEdit() {
    return this.listguiEdit;
  }
  
  public void setListguiEdit(List<String> listguiEdit) {
    this.listguiEdit = listguiEdit;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  public int getReset() {
    return this.reset;
  }
  
  public void setReset(int reset) {
    this.reset = reset;
  }
  
  public int getPage() {
    return this.page;
  }
  
  public void setPage(int page) {
    this.page = page;
  }
  
  public String getGuiName() {
    return this.guiName;
  }
  
  public void setGuiName(String guiName) {
    this.guiName = guiName;
  }
  
  public HashMap<String, ItemStackQuests> getLiItemStackQuests() {
    return this.liItemStackQuests;
  }
  
  public void setLiItemStackQuests(HashMap<String, ItemStackQuests> liItemStackQuests) {
    this.liItemStackQuests = liItemStackQuests;
  }
}
