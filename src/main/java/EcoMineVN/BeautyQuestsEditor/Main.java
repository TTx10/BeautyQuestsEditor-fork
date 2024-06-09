package EcoMineVN.BeautyQuestsEditor;

import EcoMineVN.BeautyQuestsEditor.Utils.Utils;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.charset.StandardCharsets;

interface ConfigAPI {
    public static final ConfigEnum config = ConfigEnum.config;

    public static final ConfigEnum Message = ConfigEnum.Message;

    public static final ConfigEnum Quests = ConfigEnum.Quests;

    public static final ConfigEnum Player = ConfigEnum.Player;

    public static final ConfigEnum QuestsGroup = ConfigEnum.QuestsGroup;

    public static final ConfigEnum QuestsData = ConfigEnum.QuestsData;

    public static final ConfigEnum ShopGroup = ConfigEnum.ShopGroup;

    public static final ConfigEnum SaveItem = ConfigEnum.SaveItem;

    public enum ConfigEnum {
        config("settings"),
        Quests("Quests"),
        ShopGroup("ShopGroup"),
        QuestsGroup("QuestsGroup"),
        QuestsData("QuestsData/QuestsData"),
        Player("Data/"),
        SaveItem("SaveItem"),
        Message("Message");

        private final String name;

        ConfigEnum(String name) {
            this.name = name;
        }

        public void saveConfig() {
            try {
                ((FileConfiguration)Init.configData.get(this.name)).save((File)Init.configFile.get(this.name));
            } catch (IOException iOException) {}
        }

        public FileConfiguration getConfig() {
            return (FileConfiguration)Init.configData.get(this.name);
        }

        public void saveConfigPlayer(Player p) {
            try {
                ((FileConfiguration)Init.configData.get(String.valueOf(this.name) + p.getName())).save((File)Init.configFile.get(String.valueOf(this.name) + p.getName()));
            } catch (IOException iOException) {}
        }

        public FileConfiguration getConfigPlayer(Player p) {
            return (FileConfiguration)Init.configData.get(String.valueOf(this.name) + p.getName());
        }

        public void saveConfigPlayer(String p) {
            try {
                ((FileConfiguration)Init.configData.get(String.valueOf(this.name) + p)).save((File)Init.configFile.get(String.valueOf(this.name) + p));
            } catch (IOException iOException) {}
        }

        public FileConfiguration getConfigPlayer(String p) {
            return (FileConfiguration)Init.configData.get(String.valueOf(this.name) + p);
        }
    }

    static void loadConfig(String... configsName) {
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = configsName).length, b = 0; b < i; ) {
            String configName = arrayOfString[b];
            Init.configFile.put(configName, new File(Init.plugin.getDataFolder() + "/" + configName + ".yml"));
            if (!((File)Init.configFile.get(configName)).exists()) {
                ((File)Init.configFile.get(configName)).getParentFile().mkdir();
                try {
                    Exception exception2;
                    ((File)Init.configFile.get(configName)).createNewFile();
                    Exception exception1 = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Init.configData.put(configName, YamlConfiguration.loadConfiguration((File)Init.configFile.get(configName)));
            InputStream defaultConfigInputStream = Init.plugin.getResource(String.valueOf(configName) + ".yml");
            if (defaultConfigInputStream == null) {
                Utils.writeLog(new String[] { "Unable to load file " + configName + ".yml!" });
            } else {
                Reader defaultConfigStream = new InputStreamReader(defaultConfigInputStream, StandardCharsets.UTF_8);
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                ((FileConfiguration)Init.configData.get(configName)).setDefaults((Configuration)defaultConfig);
                Init.plugin.saveConfig();
            }
            b++;
        }
    }

    default FileConfiguration getConfig(String conString) {
        return (FileConfiguration)Init.configData.get(conString);
    }

    default File getFile(String conString) {
        return (File)Init.configFile.get(conString);
    }

    static void reloadAllConfig() {
        byte b;
        int i;
        ConfigEnum[] arrayOfConfigEnum;
        for (i = (arrayOfConfigEnum = ConfigEnum.values()).length, b = 0; b < i; ) {
            ConfigEnum ce = arrayOfConfigEnum[b];
            reloadConfig(ce.name());
            b++;
        }
    }

    static void reloadConfig(String cfs) {
        File f = new File(Init.plugin.getDataFolder() + "/" + cfs + ".yml");
        Init.configFile.put(cfs, f);
        Init.configData.put(cfs, YamlConfiguration.loadConfiguration(f));
    }
}
