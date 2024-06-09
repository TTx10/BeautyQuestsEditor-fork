package EcoMineVN.BeautyQuestsEditor.Commands;

import EcoMineVN.BeautyQuestsEditor.Config.ConfigAPI;
import EcoMineVN.BeautyQuestsEditor.Main;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsInventory;
import EcoMineVN.BeautyQuestsEditor.Shop.ShopInventory;
import EcoMineVN.BeautyQuestsEditor.Utils.Utils;
import EcoMineVN.BeautyQuestsEditor.Utils.UtilsMessages;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RegisterCommands extends UtilsMessages {
    public void registerCommands() {
        registerCommand("bqe", new openCommand());
    }

    private static void registerCommand(String fallback, BukkitCommand command) {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap)bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(fallback, (Command)command);
        } catch (IllegalAccessException|IllegalArgumentException|NoSuchFieldException|SecurityException e) {
            e.printStackTrace();
        }
    }

    private class openCommand extends BukkitCommand {
        public openCommand() {
            super("bqe");
            setDescription("bqe Command");
            setPermission("bqe.*");
            setPermissionMessage("You don't have permission to do this command");
            setUsage("/bqe");
        }

        public boolean execute(CommandSender sender, String lb, String[] args) {
            Player p = (Player)sender;
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("save")) {
                    if (!p.hasPermission(getPermission())) {
                        p.sendMessage(getPermissionMessage());
                        return true;
                    }
                    if (args.length < 2) {
                        p.sendMessage("/bqe save [id]");
                        return true;
                    }
                    if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        p.sendMessage(UtilsMessages.HandNullItem);
                        return true;
                    }
                    ItemStack item = p.getInventory().getItemInMainHand();
                    SaveItem.getConfig().set(args[1], item);
                    SaveItem.saveConfig();
                    p.sendMessage(UtilsMessages.SaveItemFinish);
                    return true;
                }
                if (args[0].equalsIgnoreCase("shop")) {
                    if (args.length < 2) {
                        p.sendMessage("/bqe shop [id]");
                        return true;
                    }
                    if (Main.getInstance().getShopManager().getListShopGroup().containsKey(args[1])) {
                        Main.getInstance().getShopManager().loadInventory(args[1], p);
                        ((ShopInventory)Main.getInstance().getShopManager().getPlayerShop().get(p)).OpenInventory(false);
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("quests")) {
                    if (args.length < 2) {
                        p.sendMessage("/bqe quests [id]");
                        return true;
                    }
                    if (Main.getInstance().getQuestsManager().getListQuestsGroup().containsKey(args[1])) {
                        Main.getInstance().getQuestsManager().loadInventory(args[1], p);
                        ((QuestsInventory)Main.getInstance().getQuestsManager().getPlayerQuestsInventory().get(p)).OpenInventory(false);
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("checkpoints")) {
                    if (args.length < 2) {
                        p.sendMessage("/bqe checkpoints [id]");
                        return true;
                    }
                    if (Main.getInstance().getShopManager().getListShopGroup().containsKey(args[1])) {
                        String prefix = ShopGroup.getConfig().getString("Group." + args[1] + ".Currency-Prefix");
                        int value = Player.getConfigPlayer(p).getInt(args[1]);
                        p.sendMessage(UtilsMessages.CheckPoints
                                .replace("{curency_prefix}", prefix)
                                .replace("{value}", String.valueOf(value)));
                        return true;
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("givepoints")) {
                    if (!p.hasPermission(getPermission())) {
                        p.sendMessage(getPermissionMessage());
                        return true;
                    }
                    if (args.length < 4) {
                        p.sendMessage("/bqe givepoints [id] [player] [points]");
                        return true;
                    }
                    String id = args[1];
                    String target = args[2];
                    int points = Integer.parseInt(args[3]);
                    if (Bukkit.getPlayer(target) == null) {
                        p.sendMessage(UtilsMessages.PlayerOffline.replace("{player}", target));
                        return true;
                    }
                    Player ptarget = Bukkit.getPlayer(target);
                    int oldmoney = Player.getConfigPlayer(ptarget).getInt(id);
                    Player.getConfigPlayer(ptarget).set(id, Integer.valueOf(oldmoney + points));
                    Player.saveConfigPlayer(ptarget);
                    p.sendMessage(UtilsMessages.GivePoints.replace("{player}", target));
                    ptarget.sendMessage(UtilsMessages.TargetGivePoints.replace("{type}", id).replace("{points}", args[3]));
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!p.hasPermission(getPermission())) {
                        p.sendMessage(getPermissionMessage());
                        return true;
                    }
                    Utils.writeLog(new String[] { "Reload Starting..." });
                    List<HumanEntity> listhum = new ArrayList<>();
                    Main.getInstance().getQuestsManager().getPlayerQuestsInventory().values().forEach(quests -> {
                        List<HumanEntity> listHumanEntities = quests.getInventory().getViewers();
                        for (HumanEntity p1 : listHumanEntities)
                            param1List.add(p1);
                    });
                    listhum.forEach(p1 -> p1.closeInventory());
                    List<HumanEntity> listhum1 = new ArrayList<>();
                    Main.getInstance().getShopManager().getPlayerShop().values().forEach(quests -> {
                        List<HumanEntity> listHumanEntities = quests.getInventory().getViewers();
                        for (HumanEntity p1 : listHumanEntities)
                            param1List.add(p1);
                    });
                    listhum1.forEach(p1 -> p1.closeInventory());
                    Utils.writeLog(new String[] { "Remove Data HashMap..." });
                    Bukkit.getScheduler().cancelTask(UtilsMessages.taskQuests.getTaskId());
                    Bukkit.getScheduler().cancelTask(UtilsMessages.taskShop.getTaskId());
                    Main.getInstance().getQuestsManager().getListQuestsEditor().clear();
                    Main.getInstance().getQuestsManager().getListQuestsGroup().clear();
                    Main.getInstance().getQuestsManager().getPlayerQuestsInventory().clear();
                    Main.getInstance().getShopManager().getPlayerShop().clear();
                    Main.getInstance().getShopManager().getListShopGroup().clear();
                    Utils.writeLog(new String[] { "Remove All" });
                    Utils.writeLog(new String[] { "Create Config..." });
                    ConfigAPI.loadConfig(new String[] { "settings", "Quests", "Message", "QuestsGroup", "ShopGroup", "SaveItem" });
                    Utils.writeLog(new String[] { "Load All Config !" });
                    Utils.writeLog(new String[] { "Load Data Config Quest Shop Message..." });
                    UtilsMessages.loadQuests();
                    UtilsMessages.loadQuestsGroup();
                    UtilsMessages.loadShopGui();
                    Utils.writeLog(new String[] { "Load All QuestsEditor !" });
                    UtilsMessages.loadUtilsMessages();
                    Utils.writeLog(new String[] { "Load All Message !" });
                    p.sendMessage(UtilsMessages.Reload);
                }
            }
            return true;
        }
    }
}
