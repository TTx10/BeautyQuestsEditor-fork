package EcoMineVN.BeautyQuestsEditor.Event;

import EcoMineVN.BeautyQuestsEditor.Event.Inventory.QuestsInventoryEvent;
import EcoMineVN.BeautyQuestsEditor.Event.Inventory.ShopInventoryEvent;
import EcoMineVN.BeautyQuestsEditor.Event.Player.PlayerFinishQuestsEvent;
import EcoMineVN.BeautyQuestsEditor.Event.Player.PlayerJoinLoadData;
import EcoMineVN.BeautyQuestsEditor.Init;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class EventManager {
  public void registerEvent() {
    Bukkit.getPluginManager().registerEvents((Listener)new QuestsInventoryEvent(), Init.plugin);
    Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoinLoadData(), Init.plugin);
    Bukkit.getPluginManager().registerEvents((Listener)new PlayerFinishQuestsEvent(), Init.plugin);
    Bukkit.getPluginManager().registerEvents((Listener)new ShopInventoryEvent(), Init.plugin);
  }
}
