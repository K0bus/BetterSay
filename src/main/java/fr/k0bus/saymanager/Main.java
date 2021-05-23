package fr.k0bus.saymanager;

import java.util.*;
import java.util.logging.Level;

import fr.k0bus.k0buslib.settings.Settings;
import fr.k0bus.k0buslib.updater.UpdateChecker;
import fr.k0bus.k0buslib.utils.Messages;
import fr.k0bus.k0buslib.utils.MessagesManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.k0bus.saymanager.commands.MainCommand;
import fr.k0bus.saymanager.event.*;

public class Main extends JavaPlugin {

    private Settings settings;
    private MessagesManager messagesManager;

    private HashMap<Integer, List<String>> announceMap = new HashMap<>();
    private int task;

    @Override
    public void onEnable() {
        Messages.log(this, "&9=============================================================");
        UpdateChecker updateChecker = new UpdateChecker(this, 75230);
        if(updateChecker.isUpToDate())
        {
            Messages.log(this, "&2SayManager &av" + this.getDescription().getVersion());
        }
        else
        {
            Messages.log(this, "&2SayManager &cv" + this.getDescription().getVersion() +
                    " (Update " + updateChecker.getVersion() + " available on SpigotMC)");
        }
        Messages.log(this, "&9=============================================================");
        Messages.log(this, "&2Created by K0bus for AkuraGaming");
        Messages.log(this, "&9=============================================================");
        this.loadConfigManager();
        this.registerEvent(this.getServer().getPluginManager());
        this.registerCommand();
        this.getLogger().log(Level.INFO, "=============================================================");
    }

    public void loadConfigManager() {
        this.settings = new Settings(this);
        this.messagesManager = new MessagesManager(settings, null);
        Messages.log(this, "&2Configuration loaded !");
    }
    private void registerEvent(PluginManager pm)
	{
        pm.registerEvents(new PlayerCommandPreprocess(this), this);
        pm.registerEvents(new ConsoleCommandPreprocess(this), this);
        Messages.log(this, "&2Listener registered !");
    }
    private void registerCommand()
    {
        this.getCommand("saymanager").setExecutor(new MainCommand(this));
        Messages.log(this, "&2Commands registered !");
    }
    public Settings getSettings()
    {
        return this.settings;
    }

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    @Override
    public void onDisable()
    {
        
    }
}
