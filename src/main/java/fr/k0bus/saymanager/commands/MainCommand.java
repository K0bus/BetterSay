package fr.k0bus.saymanager.commands;

import fr.k0bus.k0buslib.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.k0bus.saymanager.Main;

public class MainCommand implements CommandExecutor {

    Main plugin;

    public MainCommand(Main instance)
    {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length >=1)
        {
            if(args[0].equals("reload"))
            {
                if(sender.hasPermission("saymanager.reload"))
                {
                    plugin.loadConfigManager();
                    Messages.sendMessageText(plugin.getMessagesManager(), sender, "&5Configuration reloaded !");
                }
                else
                {
                    Messages.sendMessageText(plugin.getMessagesManager(), sender, "&4Permissions denied !");
                }
            }
            else
            {
                Messages.sendMessageText(plugin.getMessagesManager(), sender,
                        "&aRunning " + plugin.getName() + " v" + plugin.getDescription().getVersion());
            }
        }

        return true;
    }
}