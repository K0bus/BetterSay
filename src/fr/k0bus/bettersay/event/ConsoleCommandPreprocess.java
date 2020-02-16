package fr.k0bus.bettersay.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import fr.k0bus.bettersay.Main;

public class ConsoleCommandPreprocess implements Listener {

    Main plugin;

    public ConsoleCommandPreprocess(Main instance) {
		plugin = instance;
	}

	@EventHandler(ignoreCancelled = true)
	public void onGMChange(ServerCommandEvent e) {
        String args[] = e.getCommand().split(" ");
        if(args.length >1 && args[0].equals("say"))
        {
            String message = e.getCommand().substring(4);
            message = plugin.getConfig().getString("server-format")
                .replace("{MESSAGE}", message);
			plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
			e.setCancelled(true);
        }
	}
}
