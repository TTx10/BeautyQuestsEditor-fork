package EcoMineVN.BeautyQuestsEditor.Quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class QuestsManager {
  private HashMap<String, QuestsEditor> listQuestsEditor = new HashMap<>();
  
  private HashMap<String, QuestsGroup> listQuestsGroup = new HashMap<>();
  
  private HashMap<Player, QuestsInventory> playerQuestsInventory = new HashMap<>();
  
  public void addQuestsGroup(QuestsGroup questsGroup) {
    this.listQuestsGroup.put(questsGroup.getId(), questsGroup);
  }
  
  public HashMap<String, QuestsEditor> getListQuestsEditor() {
    return this.listQuestsEditor;
  }
  
  public HashMap<String, QuestsGroup> getListQuestsGroup() {
    return this.listQuestsGroup;
  }
  
  public HashMap<Player, QuestsInventory> getPlayerQuestsInventory() {
    return this.playerQuestsInventory;
  }
  
  public void addQuestsEditor(QuestsEditor questsEditor) {
    this.listQuestsEditor.put(questsEditor.getId(), questsEditor);
  }
  
  public void loadInventory(String group, Player p) {
    if (this.listQuestsGroup.containsKey(group)) {
      this.playerQuestsInventory.remove(p);
      QuestsGroup questsGroup = this.listQuestsGroup.get(group);
      QuestsInventory questsInventory = new QuestsInventory();
      List<String> questsGuiEdit = questsGroup.getListguiEdit();
      List<QuestsEditor> lQuestsEditors = questsGroup.getListQuestsEditors();
      HashMap<String, ItemStackQuests> itemStackQuests = questsGroup.getLiItemStackQuests();
      int quests = 0;
      Inventory inv = Bukkit.createInventory(null, questsGuiEdit.size() * 9, questsGroup.getGuiName());
      for (int y = 0; y < questsGroup.getPage(); y++) {
        List<ItemStack> listItem = new ArrayList<>();
        for (int i = 0; i < questsGuiEdit.size(); i++) {
          char[] array = ((String)questsGuiEdit.get(i)).toCharArray();
          for (int j = 0; j < array.length; j++) {
            String value = String.valueOf(array[j]);
            if (!itemStackQuests.containsKey(value) && 
              quests < lQuestsEditors.size()) {
              listItem.add(((QuestsEditor)lQuestsEditors.get(quests)).getIcon());
              questsInventory.getItemQuests().put(((QuestsEditor)lQuestsEditors.get(quests)).getIcon(), lQuestsEditors.get(quests));
              quests++;
            } 
          } 
        } 
        questsInventory.getListItemPage().add(listItem);
      } 
      questsInventory.setQuestsGroup(questsGroup);
      questsInventory.setInventory(inv);
      questsInventory.setPlayer(p);
      this.playerQuestsInventory.put(p, questsInventory);
    } 
  }
}
