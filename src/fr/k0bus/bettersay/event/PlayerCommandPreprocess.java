package fr.k0bus.bettersay.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.k0bus.bettersay.Main;

public class PlayerCommandPreprocess implements Listener {

	Main plugin;

	public PlayerCommandPreprocess(Main instance) {
		plugin = instance;
	}

	@EventHandler(ignoreCancelled = true)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(e.getPlayer().hasPermission("minecraft.command.say")) {
			String args[] = e.getMessage().split(" ");
			if (args.length > 1 && args[0].equals("/say")) {
				String message = e.getMessage().substring(5);
				message = plugin.getConfig().getString("player-format")
						.replace("{PLAYER}", e.getPlayer().getName())
						.replace("{MESSAGE}", message);
				plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
				e.setCancelled(true);
			}
		}
	}
}
