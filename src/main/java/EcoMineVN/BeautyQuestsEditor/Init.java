package EcoMineVN.BeautyQuestsEditor;

import java.io.File;
import java.util.HashMap;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Init {
    public static Plugin plugin;

    public static Server server = null;

    public static ConsoleCommandSender console = null;

    public static HashMap<String, File> configFile = new HashMap<>();

    public static HashMap<String, FileConfiguration> configData = new HashMap<>();
}
