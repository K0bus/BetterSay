package fr.k0bus.saymanager.event;

import fr.k0bus.k0buslib.utils.Formater;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import fr.k0bus.saymanager.Main;

public class ConsoleCommandPreprocess implements Listener {

    Main plugin;

    public ConsoleCommandPreprocess(Main instance) {
		plugin = instance;
	}

	@EventHandler(ignoreCancelled = true)
	public void onCommand(ServerCommandEvent e) {
        String[] args = e.getCommand().split(" ");
        if(args.length >1 && args[0].equals("say"))
        {
            String toSend = e.getCommand().substring(4);
            String message = plugin.getSettings().getString("server-format");
            if(message != null)
                message = message.replace("{MESSAGE}", toSend);
            else
                message = "";
			plugin.getServer().broadcastMessage(Formater.formatColor(message));
			e.setCancelled(true);
        }
	}
}
