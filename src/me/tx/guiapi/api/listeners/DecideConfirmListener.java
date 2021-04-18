package me.tx.guiapi.api.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.tx.guiapi.api.events.DecideConfirmEvent;
import me.tx.guiapi.api.interfaces.Confirmable;
import net.md_5.bungee.api.ChatColor;

public class DecideConfirmListener implements Listener {
	
	@EventHandler
	public void onDecideConfirm(DecideConfirmEvent event) {
		Bukkit.broadcastMessage(ChatColor.YELLOW + "This is a broadcast, the decide event was called. hi");
		event.getConfirmMenu().getPlayer().sendMessage(ChatColor.RED + "decide event called.");
		event.getConfirmMenu().getParentMenu().open();
		((Confirmable)event.getConfirmMenu().getParentMenu()).onDecideConfirm(event);
	}
}
