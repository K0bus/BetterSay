package fr.k0bus.bettersay.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.k0bus.bettersay.Main;

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
                if(!(sender instanceof Player) || sender.hasPermission("bettersay.reload"))
                {
                    plugin.loadConfigManager();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tag") + " &5Configuration reloaded !"));
                }
                else
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tag") + " &4Permissions denied !"));
                }
            }
            else
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tag") + " &aRunning " + plugin.getName() + " v" + plugin.getDescription().getVersion()));
            }
        }

        return true;
    }
}