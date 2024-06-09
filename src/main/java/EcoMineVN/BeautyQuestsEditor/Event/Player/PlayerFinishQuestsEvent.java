package EcoMineVN.BeautyQuestsEditor.Event.Player;

import EcoMineVN.BeautyQuestsEditor.Config.ConfigAPI;
import EcoMineVN.BeautyQuestsEditor.Main;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsEditor;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsGroup;
import EcoMineVN.BeautyQuestsEditor.Shop.ShopGroup;
import EcoMineVN.BeautyQuestsEditor.Utils.UtilsMessages;
import fr.skytasul.quests.api.events.QuestFinishEvent;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerFinishQuestsEvent implements Listener, ConfigAPI {
    @EventHandler
    public void onFinish(QuestFinishEvent e) {
        Player p = e.getPlayer();
        int id = e.getQuest().getID();
        Main.getInstance().getQuestsManager().getListQuestsGroup().values().forEach(value -> {
            List<QuestsEditor> listQuestsEditor = value.getListQuestsEditors();
            listQuestsEditor.forEach(());
        });
    }
}
