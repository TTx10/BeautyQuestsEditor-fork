package EcoMineVN.BeautyQuestsEditor.Shop;

import java.util.HashMap;
import java.util.List;

public class ShopGroup {
  private int reset;
  
  private String guiName;
  
  private String currency;
  
  private String Id;
  
  private String currencyPrefix;
  
  private HashMap<Integer, List<String>> guiPage;
  
  private HashMap<String, ItemStackShop> listItemStackShop;
  
  public ShopGroup(int reset, String guiName, String currency, HashMap<Integer, List<String>> guiPage, HashMap<String, ItemStackShop> listItemStackShop, String Id, String currencyPrefix) {
    this.reset = reset;
    this.guiName = guiName;
    this.currency = currency;
    this.guiPage = guiPage;
    this.listItemStackShop = listItemStackShop;
    this.Id = Id;
    this.currencyPrefix = currencyPrefix;
  }
  
  public String getCurrencyPrefix() {
    return this.currencyPrefix;
  }
  
  public void setCurrencyPrefix(String currencyPrefix) {
    this.currencyPrefix = currencyPrefix;
  }
  
  public String getId() {
    return this.Id;
  }
  
  public void setId(String id) {
    this.Id = id;
  }
  
  public int getReset() {
    return this.reset;
  }
  
  public void setReset(int reset) {
    this.reset = reset;
  }
  
  public String getGuiName() {
    return this.guiName;
  }
  
  public void setGuiName(String guiName) {
    this.guiName = guiName;
  }
  
  public String getCurrency() {
    return this.currency;
  }
  
  public void setCurrency(String currency) {
    this.currency = currency;
  }
  
  public HashMap<Integer, List<String>> getGuiPage() {
    return this.guiPage;
  }
  
  public void setGuiPage(HashMap<Integer, List<String>> guiPage) {
    this.guiPage = guiPage;
  }
  
  public HashMap<String, ItemStackShop> getListItemStackShop() {
    return this.listItemStackShop;
  }
  
  public void setListItemStackShop(HashMap<String, ItemStackShop> listItemStackShop) {
    this.listItemStackShop = listItemStackShop;
  }
}
