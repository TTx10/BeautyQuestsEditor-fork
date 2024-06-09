package EcoMineVN.BeautyQuestsEditor.Api;

import fr.skytasul.quests.api.QuestsAPI;
import fr.skytasul.quests.players.PlayerAccount;
import fr.skytasul.quests.players.PlayersManager;
import fr.skytasul.quests.structure.Quest;
import org.bukkit.entity.Player;

public class BeautyQuestsAPI {
    public static void startQuests(Player p, int id) {
        for (Quest quest : QuestsAPI.getQuests()) {
            if (quest.getID() == id) {
                quest.start(p);
                break;
            }
        }
    }

    public static void resetQuests(Player p, int id) {
        for (Quest quest : QuestsAPI.getQuests()) {
            if (quest.getID() == id) {
                PlayerAccount playeracc = PlayersManager.getPlayerAccount(p);
                quest.resetPlayer(playeracc);
                break;
            }
        }
    }
}
