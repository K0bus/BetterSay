package fr.k0bus.bettersay;

import java.util.*;
import java.util.logging.Level;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.k0bus.bettersay.commands.MainCommand;
import fr.k0bus.bettersay.event.*;
import fr.k0bus.bettersay.manager.ConfigManager;
import fr.k0bus.bettersay.type.ConfigType;

public class Main extends JavaPlugin {

    public ConfigManager mainConf;
    public ConfigManager lang;

    private HashMap<Integer, List<String>> announceMap = new HashMap<>();
    private int task;

    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, "=============================================================");
        this.getLogger().log(Level.INFO, " _     _____             ");
        this.getLogger().log(Level.INFO, "| |   /  ___|            ");
        this.getLogger().log(Level.INFO, "| |__ \\ `--.  __ _ _   _ ");
        this.getLogger().log(Level.INFO, "| '_ \\ `--. \\/ _` | | | |");
        this.getLogger().log(Level.INFO, "| |_) /\\__/ / (_| | |_| |");
        this.getLogger().log(Level.INFO, "|_.__/\\____/ \\__,_|\\__, |");
        this.getLogger().log(Level.INFO, "                    __/ |");
        this.getLogger().log(Level.INFO, "                   |___/ ");
        this.getLogger().log(Level.INFO, "=============================================================");
        this.getLogger().log(Level.INFO, "Created by K0bus for AkuraGaming");
        this.getLogger().log(Level.INFO, "=============================================================");
        this.loadConfigManager();
        this.registerEvent(this.getServer().getPluginManager());
        this.registerCommand();
        this.getLogger().log(Level.INFO, "=============================================================");
    }

    public void loadAnnouncer()
    {
        ConfigurationSection cs = mainConf.getConfig().getConfigurationSection("announcements");
        HashMap<Integer, List<String>> announceMap = new HashMap<>();
        int i = 0;
        for(String key : cs.getKeys(false))
        {
            this.getLogger().info("Loading messages from " + key + " announce");
            if(cs.isString(key))
            {
                String text = cs.getString(key);
                if(text != null)
                announceMap.put(i, Collections.singletonList(text));
            }
            else
            {
                List<String> stringList = cs.getStringList(key);
                announceMap.put(i, stringList);
            }
            this.getLogger().info(key + " announce loaded successfully !");
            i++;
        }
        this.announceMap = announceMap;
        if (this.announceMap.isEmpty())
            this.getLogger().info("Announcer disable because couldn't find any message.");
    }
    public void startAutoAnnounce()
    {
        if(Bukkit.getScheduler().isCurrentlyRunning(this.task) || Bukkit.getScheduler().isQueued(this.task))
            this.stopAutoAnnounce();
        Server server = this.getServer();
        final int[] i = {0};
        final HashMap<Integer, List<String>> announceMap = this.announceMap;
        this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                    for(String raw:announceMap.get(i[0])) {
                        server.broadcastMessage(ChatColor.translateAlternateColorCodes('&', raw));
                        i[0]++;
                        if (!announceMap.containsKey(i[0]))
                            i[0] = 0;
                    }
            }
        }, 0L, this.getConfig().getInt("announce_interval")*20);
    }
    public void stopAutoAnnounce()
    {
        if(Bukkit.getScheduler().isCurrentlyRunning(this.task) || Bukkit.getScheduler().isQueued(this.task))
            Bukkit.getScheduler().cancelTask(this.task);
    }

    public void loadConfigManager() {
        this.getLogger().log(Level.INFO, "Loading configuration ...");
        this.mainConf = new ConfigManager("config.yml", this.getDataFolder(), this, ConfigType.CONFIG);
        this.stopAutoAnnounce();
        this.loadAnnouncer();
        this.startAutoAnnounce();
        this.getLogger().log(Level.INFO, "Configuration loaded successfully !");
    }
    private void registerEvent(PluginManager pm)
	{
        this.getLogger().log(Level.INFO, "Loading event ...");
        pm.registerEvents(new PlayerCommandPreprocess(this), this);
        pm.registerEvents(new ConsoleCommandPreprocess(this), this);
        this.getLogger().log(Level.INFO, "Event loaded successfully !");
    }
    private void registerCommand()
    {
        this.getCommand("bsay").setExecutor(new MainCommand(this));
    }
    public FileConfiguration getConfig()
    {
        return this.mainConf.getConfig();
    }
    public FileConfiguration getLang()
    {
        return this.lang.getConfig();
    }
    @Override
    public void onDisable()
    {
        
    }
}
