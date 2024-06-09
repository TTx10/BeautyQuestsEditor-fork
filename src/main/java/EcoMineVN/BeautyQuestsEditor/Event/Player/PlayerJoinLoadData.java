package EcoMineVN.BeautyQuestsEditor.Event.Player;

import EcoMineVN.BeautyQuestsEditor.Api.BeautyQuestsAPI;
import EcoMineVN.BeautyQuestsEditor.Config.ConfigAPI;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinLoadData implements Listener, ConfigAPI {
  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Player p = e.getPlayer();
    ConfigAPI.loadConfig(new String[] { "Data/" + p.getName() });
    List<String> list = new ArrayList<>();
    if (Player.getConfigPlayer(p).getString("Reset") != null)
      list = Player.getConfigPlayer(p).getStringList("Reset"); 
    for (String groupid : list) {
      List<String> listId = new ArrayList<>();
      if (Player.getConfigPlayer(p).getString(groupid) != null)
        listId = Player.getConfigPlayer(p).getStringList(groupid); 
      for (String id : listId) {
        int questsid = Quests.getConfig().getInt("Quests." + id + ".Id");
        BeautyQuestsAPI.resetQuests(p, questsid);
      } 
      Player.getConfigPlayer(p).set(groupid, "");
    } 
    Player.getConfigPlayer(p).set("Reset", null);
    Player.saveConfigPlayer(p);
  }
}
