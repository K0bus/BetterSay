package fr.k0bus.bettersay;

import java.util.logging.Level;

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

    public void loadConfigManager() {
        this.getLogger().log(Level.INFO, "Loading configuration ...");
        this.mainConf = new ConfigManager("config.yml", this.getDataFolder(), this, ConfigType.CONFIG);
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
