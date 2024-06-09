package EcoMineVN.BeautyQuestsEditor.Utils;

import EcoMineVN.BeautyQuestsEditor.Api.BeautyQuestsAPI;
import EcoMineVN.BeautyQuestsEditor.Config.ConfigAPI;
import EcoMineVN.BeautyQuestsEditor.Init;
import EcoMineVN.BeautyQuestsEditor.Main;
import EcoMineVN.BeautyQuestsEditor.Quests.ItemStackQuests;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsEditor;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsGroup;
import EcoMineVN.BeautyQuestsEditor.Quests.QuestsInventory;
import EcoMineVN.BeautyQuestsEditor.Quests.RoleItem;
import EcoMineVN.BeautyQuestsEditor.Shop.ItemShopRole;
import EcoMineVN.BeautyQuestsEditor.Shop.ItemStackShop;
import EcoMineVN.BeautyQuestsEditor.Shop.ShopGroup;
import fr.skytasul.quests.api.QuestsAPI;
import fr.skytasul.quests.structure.Quest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class UtilsMessages implements ConfigAPI {
  public static ItemStack FinishItem;
  
  public static String HandNullItem;
  
  public static String SaveItemFinish;
  
  public static String PlayerOffline;
  
  public static String GivePoints;
  
  public static String TargetGivePoints;
  
  public static String Reload;
  
  public static String UpdateShop;
  
  public static String UpdateQuests;
  
  public static String BuyFull;
  
  public static String BuyNullMoney;
  
  public static String BuyNullPerm;
  
  public static String BuyFinish;
  
  public static String QuestsFinish;
  
  public static BukkitTask taskShop;
  
  public static BukkitTask taskQuests;
  
  public static String CheckPoints;
  
  public static String Permission;
  
  public static void loadUtilsMessages() {
    String material = config.getConfig().getString("Inventory.FinishItem.Icon");
    String name = Replace(config.getConfig().getString("Inventory.FinishItem.Name"));
    List<String> lores = config.getConfig().getStringList("Inventory.FinishItem.Lore");
    for (int i = 0; i < lores.size(); ) {
      lores.set(i, Replace(lores.get(i)));
      i++;
    } 
    ItemStack item = null;
    if (material.contains(":")) {
      String[] materialsp = material.split(":");
      item = new ItemStack(Material.getMaterial(materialsp[0].toUpperCase()), 1, (short)Integer.parseInt(materialsp[1]));
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(lores);
      item.setItemMeta(meta);
    } else {
      item = new ItemStack(Material.getMaterial(material.toUpperCase()));
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(name);
      meta.setLore(lores);
      item.setItemMeta(meta);
    } 
    FinishItem = item;
    HandNullItem = Replace(Message.getConfig().getString("Message.HandNullItem"));
    SaveItemFinish = Replace(Message.getConfig().getString("Message.SaveItemFinish"));
    PlayerOffline = Replace(Message.getConfig().getString("Message.PlayerOffline"));
    GivePoints = Replace(Message.getConfig().getString("Message.GivePoints"));
    TargetGivePoints = Replace(Message.getConfig().getString("Message.TargetGivePoints"));
    Reload = Replace(Message.getConfig().getString("Message.Reload"));
    UpdateShop = Replace(Message.getConfig().getString("Message.UpdateShop"));
    UpdateQuests = Replace(Message.getConfig().getString("Message.UpdateQuests"));
    BuyFull = Replace(Message.getConfig().getString("Message.BuyFull"));
    BuyNullMoney = Replace(Message.getConfig().getString("Message.BuyNullMoney"));
    BuyNullPerm = Replace(Message.getConfig().getString("Message.BuyNullPerm"));
    BuyFinish = Replace(Message.getConfig().getString("Message.BuyFinish"));
    QuestsFinish = Replace(Message.getConfig().getString("Message.QuestsFinish"));
    CheckPoints = Replace(Message.getConfig().getString("Message.CheckPoints"));
    Permission = Replace(Message.getConfig().getString("Message.Permission"));
  }
  
  public static void loadQuestsGroup() {
    QuestsGroup.getConfig().getConfigurationSection("Group.").getKeys(false).stream().forEach(value -> {
          final int amount = QuestsGroup.getConfig().getInt("Group." + value + ".Amount");
          final int reset = QuestsGroup.getConfig().getInt("Group." + value + ".Reset");
          int page = QuestsGroup.getConfig().getInt("Group." + value + ".Page");
          final String guiname = Replace(QuestsGroup.getConfig().getString("Group." + value + ".Gui-Name"));
          String pointsName = Replace(QuestsGroup.getConfig().getString("Group." + value + ".Points-Name"));
          final List<String> questsRandom = QuestsGroup.getConfig().getStringList("Group." + value + ".QuestRandom");
          List<String> guiedit = QuestsGroup.getConfig().getStringList("Group." + value + ".GuiEdit");
          HashMap<String, ItemStackQuests> listItemStackQuests = new HashMap<>();
          QuestsGroup.getConfig().getConfigurationSection("Group." + value + ".Item.").getKeys(false).stream().forEach(());
          List<QuestsEditor> listQuestsEditors = new ArrayList<>();
          List<RandomQuestsEditor> list = new ArrayList<>();
          questsRandom.forEach(());
          NavigableMap<Integer, RandomQuestsEditor> weighedMap = new TreeMap<>();
          int totalWeight = 0;
          for (RandomQuestsEditor obj : list) {
            totalWeight += obj.getRate();
            weighedMap.put(Integer.valueOf(totalWeight), obj);
          } 
          Random rnd = new Random();
          for (int i = 0; i < amount; i++) {
            int pick = rnd.nextInt(totalWeight);
            Map.Entry<Integer, RandomQuestsEditor> obj = weighedMap.higherEntry(Integer.valueOf(pick));
            totalWeight -= ((RandomQuestsEditor)obj.getValue()).getRate();
            weighedMap.remove(obj.getKey());
            listQuestsEditors.add(((RandomQuestsEditor)obj.getValue()).getQuestsEditor());
          } 
          final QuestsGroup questsGroup = new QuestsGroup(listQuestsEditors, guiedit, amount, reset, page, guiname, value, listItemStackQuests, pointsName);
          Main.getInstance().getQuestsManager().addQuestsGroup(questsGroup);
          taskQuests = (new BukkitRunnable() {
              public void run() {
                questsGroup.setReset(questsGroup.getReset() - 1);
                if (questsGroup.getReset() <= 0) {
                  if ((new File(String.valueOf(Init.plugin.getDataFolder().getAbsolutePath()) + File.separator + "Data")).exists()) {
                    byte b;
                    int j;
                    File[] arrayOfFile;
                    for (j = (arrayOfFile = (new File(String.valueOf(Init.plugin.getDataFolder().getAbsolutePath()) + File.separator + "Data")).listFiles()).length, b = 0; b < j; ) {
                      File file = arrayOfFile[b];
                      String p = file.getName().replace(".yml", "");
                      List<String> listIdPlayer = new ArrayList<>();
                      if (UtilsMessages.Player.getConfigPlayer(p).getString(questsGroup.getId()) != null)
                        listIdPlayer = UtilsMessages.Player.getConfigPlayer(p).getStringList(questsGroup.getId()); 
                      boolean isSet = true;
                      for (String id : listIdPlayer) {
                        int questsid = UtilsMessages.Quests.getConfig().getInt("Quests." + id + ".Id");
                        if (Bukkit.getPlayer(p) != null) {
                          BeautyQuestsAPI.resetQuests(Bukkit.getPlayer(p), questsid);
                          continue;
                        } 
                        List<String> list1 = new ArrayList<>();
                        if (UtilsMessages.Player.getConfigPlayer(p).getString("Reset") != null)
                          list1 = UtilsMessages.Player.getConfigPlayer(p).getStringList("Reset"); 
                        if (!list1.contains(questsGroup.getId()))
                          list1.add(questsGroup.getId()); 
                        UtilsMessages.Player.getConfigPlayer(p).set("Reset", list1);
                        UtilsMessages.Player.saveConfigPlayer(p);
                        isSet = false;
                      } 
                      if (isSet) {
                        UtilsMessages.Player.getConfigPlayer(p).set(questsGroup.getId(), "");
                        UtilsMessages.Player.saveConfigPlayer(p);
                      } 
                      b++;
                    } 
                  } 
                  UtilsMessages.QuestsData.getConfig().set(questsGroup.getId(), "");
                  UtilsMessages.QuestsData.saveConfig();
                  List<QuestsEditor> listQuestsEditors = new ArrayList<>();
                  List<RandomQuestsEditor> list = new ArrayList<>();
                  questsRandom.forEach(key -> {
                        if (Main.getInstance().getQuestsManager().getListQuestsEditor().containsKey(key))
                          param1List.add(new RandomQuestsEditor((QuestsEditor)Main.getInstance().getQuestsManager().getListQuestsEditor().get(key), ((QuestsEditor)Main.getInstance().getQuestsManager().getListQuestsEditor().get(key)).getRate())); 
                      });
                  NavigableMap<Integer, RandomQuestsEditor> weighedMap = new TreeMap<>();
                  int totalWeight = 0;
                  for (RandomQuestsEditor obj : list) {
                    totalWeight += obj.getRate();
                    weighedMap.put(Integer.valueOf(totalWeight), obj);
                  } 
                  Random rnd = new Random();
                  for (int i = 0; i < amount; i++) {
                    int pick = rnd.nextInt(totalWeight);
                    Map.Entry<Integer, RandomQuestsEditor> obj = weighedMap.higherEntry(Integer.valueOf(pick));
                    totalWeight -= ((RandomQuestsEditor)obj.getValue()).getRate();
                    weighedMap.remove(obj.getKey());
                    listQuestsEditors.add(((RandomQuestsEditor)obj.getValue()).getQuestsEditor());
                  } 
                  QuestsGroup questsg = (QuestsGroup)Main.getInstance().getQuestsManager().getListQuestsGroup().get(questsGroup.getId());
                  questsg.setListQuestsEditors(listQuestsEditors);
                  List<HumanEntity> listhum = new ArrayList<>();
                  Main.getInstance().getQuestsManager().getPlayerQuestsInventory().values().forEach(quests -> {
                        List<HumanEntity> listHumanEntities = quests.getInventory().getViewers();
                        for (HumanEntity p : listHumanEntities)
                          param1List.add(p); 
                      });
                  listhum.forEach(p -> p.closeInventory());
                  Main.getInstance().getQuestsManager().getPlayerQuestsInventory().clear();
                  Bukkit.broadcastMessage(UtilsMessages.UpdateQuests.replace("{prefix_quests}", guiname));
                  questsGroup.setReset(reset);
                } 
              }
            }).runTaskTimer(Init.plugin, 20L, 20L);
        });
  }
  
  public static void loadShopGui() {
    ShopGroup.getConfig().getConfigurationSection("Group.").getKeys(false).stream().forEach(value -> {
          final int reset = ShopGroup.getConfig().getInt("Group." + value + ".Reset");
          final String guiName = ShopGroup.getConfig().getString("Group." + value + ".Gui-Name");
          String currency = ShopGroup.getConfig().getString("Group." + value + ".Currency");
          String currencyPrefix = Replace(ShopGroup.getConfig().getString("Group." + value + ".Currency-Prefix"));
          HashMap<Integer, List<String>> guiPage = new HashMap<>();
          ShopGroup.getConfig().getConfigurationSection("Group." + value + ".Gui-shop.").getKeys(false).stream().forEach(());
          HashMap<String, ItemStackShop> listItemStackShop = new HashMap<>();
          ShopGroup.getConfig().getConfigurationSection("Group." + value + ".Item.").getKeys(false).stream().forEach(());
          final ShopGroup shopGroup = new ShopGroup(reset, guiName, currency, guiPage, listItemStackShop, value, currencyPrefix);
          Main.getInstance().getShopManager().addShopGroup(shopGroup);
          taskShop = (new BukkitRunnable() {
              public void run() {
                shopGroup.setReset(shopGroup.getReset() - 1);
                if (shopGroup.getReset() <= 0) {
                  if ((new File(String.valueOf(Init.plugin.getDataFolder().getAbsolutePath()) + File.separator + "Data")).exists()) {
                    byte b;
                    int i;
                    File[] arrayOfFile;
                    for (i = (arrayOfFile = (new File(String.valueOf(Init.plugin.getDataFolder().getAbsolutePath()) + File.separator + "Data")).listFiles()).length, b = 0; b < i; ) {
                      File file = arrayOfFile[b];
                      String p = file.getName().replace(".yml", "");
                      UtilsMessages.Player.getConfigPlayer(p).set("Shop." + shopGroup.getId(), null);
                      UtilsMessages.Player.saveConfigPlayer(p);
                      b++;
                    } 
                  } 
                  Bukkit.broadcastMessage(UtilsMessages.UpdateShop.replace("{prefix_shop}", guiName));
                  shopGroup.setReset(reset);
                } 
              }
            }).runTaskTimer(Init.plugin, 20L, 20L);
        });
  }
  
  public static void loadQuests() {
    List<Integer> listId = new ArrayList<>();
    QuestsAPI.getQuests().forEach(value -> paramList.add(Integer.valueOf(value.getID())));
    Quests.getConfig().getConfigurationSection("Quests.").getKeys(false).stream().forEach(value -> {
          int id = Quests.getConfig().getInt("Quests." + value + ".Id");
          int rate = Quests.getConfig().getInt("Quests." + value + ".Rate");
          int points = Quests.getConfig().getInt("Quests." + value + ".Points");
          String name = Replace(Quests.getConfig().getString("Quests." + value + ".Name"));
          String permission = Quests.getConfig().getString("Quests." + value + ".Permission");
          Material material = Material.getMaterial(Quests.getConfig().getString("Quests." + value + ".Icon").toUpperCase());
          List<String> lores = Quests.getConfig().getStringList("Quests." + value + ".Lore");
          for (int i = 0; i < lores.size(); i++)
            lores.set(i, Replace(lores.get(i))); 
          boolean glowing = Quests.getConfig().getBoolean("Quests." + value + ".Glowing");
          ItemStack item = null;
          if (paramList.contains(Integer.valueOf(id))) {
            item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lores);
            meta.addEnchant(Enchantment.BINDING_CURSE, 1, glowing);
            meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
            item.setItemMeta(meta);
          } else {
            item = new ItemStack(Material.BARRIER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("hsl);
            item.setItemMeta(meta);
            id = -99;
          } 
          QuestsEditor questsEditor = new QuestsEditor(value, id, rate, item, points, permission);
          Main.getInstance().getQuestsManager().addQuestsEditor(questsEditor);
        });
  }
  
  public static String Replace(String s) {
    if (s.contains("{prefix}"))
      return ChatColor.translateAlternateColorCodes('&', s.replace("{prefix}", Message.getConfig().getString("Prefix"))); 
    return ChatColor.translateAlternateColorCodes('&', s);
  }
}
