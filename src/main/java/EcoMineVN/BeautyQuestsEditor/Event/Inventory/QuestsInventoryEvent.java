package EcoMineVN.BeautyQuestsEditor.Event.Inventory;

import EcoMineVN.BeautyQuestsEditor.Api.BeautyQuestsAPI;
import EcoMineVN.BeautyQuestsEditor.Config.ConfigAPI;
import EcoMineVN.BeautyQuestsEditor.Main;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsEditor;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsInventory;
import EcoMineVN.BeautyQuestsEditor.Quests.RoleItem;
import EcoMineVN.BeautyQuestsEditor.Utils.UtilsMessages;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class QuestsInventoryEvent implements Listener, ConfigAPI {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if (Main.getInstance().getQuestsManager().getPlayerQuestsInventory().containsKey(p)) {
            e.setCancelled(true);
            QuestsInventory questsInventory = (QuestsInventory)Main.getInstance().getQuestsManager().getPlayerQuestsInventory().get(p);
            ItemStack item = e.getCurrentItem();
            int page = questsInventory.getQuestsGroup().getPage();
            if (questsInventory.getItemPage().containsKey(item))
                if (questsInventory.getItemPage().get(item) == RoleItem.nextpage) {
                    if (page > 1 &&
                            questsInventory.getPage() + 1 < page) {
                        questsInventory.setPage(questsInventory.getPage() + 1);
                        questsInventory.OpenInventory(true);
                    }
                } else if (questsInventory.getItemPage().get(item) == RoleItem.beforepage &&
                        page > 1 &&
                        questsInventory.getPage() - 1 >= 0) {
                    questsInventory.setPage(questsInventory.getPage() - 1);
                    questsInventory.OpenInventory(true);
                }
            p.sendMessage("#1");
            if (questsInventory.getItemQuests().containsKey(item)) {
                p.sendMessage("#2");
                QuestsEditor questsEditor = (QuestsEditor)questsInventory.getItemQuests().get(item);
                if (questsEditor.getBeautyQuestsId() >= 0) {
                    p.sendMessage("#3");
                    if (questsEditor.getPermission().equals("") || p.hasPermission(questsEditor.getPermission())) {
                        List<String> listIdPlayer = QuestsData.getConfig().getStringList(questsInventory.getQuestsGroup().getId());
                        listIdPlayer.add(questsEditor.getId());
                        QuestsData.getConfig().set(questsInventory.getQuestsGroup().getId(), listIdPlayer);
                        QuestsData.saveConfig();
                        p.closeInventory();
                        BeautyQuestsAPI.startQuests(p, questsEditor.getBeautyQuestsId());
                    } else {
                        p.sendMessage(UtilsMessages.Permission);
                    }
                }
            }
        }
    }
}
