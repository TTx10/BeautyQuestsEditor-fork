package EcoMineVN.BeautyQuestsEditor.Event.Inventory;

import EcoMineVN.BeautyQuestsEditor.Config.ConfigAPI;
import EcoMineVN.BeautyQuestsEditor.Main;
import EcoMineVN.BeautyQuestsEditor.Shop.ItemShopRole;
import EcoMineVN.BeautyQuestsEditor.Shop.ItemStackShop;
import EcoMineVN.BeautyQuestsEditor.Shop.ShopInventory;
import EcoMineVN.BeautyQuestsEditor.Utils.UtilsMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopInventoryEvent implements Listener, ConfigAPI {
  @EventHandler
  public void onClick(InventoryClickEvent e) {
    Player p = (Player)e.getWhoClicked();
    if (Main.getInstance().getShopManager().getPlayerShop().containsKey(p)) {
      e.setCancelled(true);
      ShopInventory shopsInventory = (ShopInventory)Main.getInstance().getShopManager().getPlayerShop().get(p);
      ItemStack item = e.getCurrentItem();
      int page = shopsInventory.getShopGroup().getGuiPage().size();
      if (shopsInventory.getItemShop().containsKey(item)) {
        ItemStackShop itemStackShop = (ItemStackShop)shopsInventory.getItemShop().get(item);
        if (itemStackShop.getItemShopRole() == ItemShopRole.nextpage) {
          if (page > 1 && 
            shopsInventory.getPage() + 1 < page) {
            shopsInventory.setPage(shopsInventory.getPage() + 1);
            shopsInventory.OpenInventory(true);
          } 
        } else if (itemStackShop.getItemShopRole() == ItemShopRole.beforepage) {
          if (page > 1 && 
            shopsInventory.getPage() - 1 >= 0) {
            shopsInventory.setPage(shopsInventory.getPage() - 1);
            shopsInventory.OpenInventory(true);
          } 
        } else if (itemStackShop.getItemShopRole() == ItemShopRole.buy && 
          SaveItem.getConfig().getString(itemStackShop.getSaveId()) != null) {
          String idPoint = shopsInventory.getShopGroup().getCurrency();
          int point = Player.getConfigPlayer(p).getInt(idPoint);
          int amount = itemStackShop.getAmount();
          int price = itemStackShop.getPrice();
          String id = itemStackShop.getId();
          int damua = Player.getConfigPlayer(p).getInt("Shop." + shopsInventory.getShopGroup().getId() + "." + id);
          if (p.hasPermission(itemStackShop.getPermission())) {
            if (point >= price) {
              if (damua < amount) {
                p.getInventory().addItem(new ItemStack[] { SaveItem.getConfig().getItemStack(itemStackShop.getSaveId()) });
                Player.getConfigPlayer(p).set(idPoint, Integer.valueOf(point - price));
                Player.getConfigPlayer(p).set("Shop." + shopsInventory.getShopGroup().getId() + "." + id, Integer.valueOf(damua + 1));
                Player.getConfigPlayer(p);
                p.sendMessage(UtilsMessages.BuyFinish
                    .replace("{max}", String.valueOf(amount))
                    .replace("{min}", String.valueOf(Player.getConfigPlayer(p).getInt("Shop." + shopsInventory.getShopGroup().getId() + "." + id))));
              } else {
                p.sendMessage(UtilsMessages.BuyFull
                    .replace("{max}", String.valueOf(amount))
                    .replace("{min}", String.valueOf(damua)));
              } 
            } else {
              p.sendMessage(UtilsMessages.BuyNullMoney
                  .replace("{value}", String.valueOf(price))
                  .replace("{currency_prefix}", shopsInventory.getShopGroup().getCurrencyPrefix()));
            } 
          } else {
            p.sendMessage(UtilsMessages.BuyNullPerm);
          } 
        } 
      } 
    } 
  }
}
