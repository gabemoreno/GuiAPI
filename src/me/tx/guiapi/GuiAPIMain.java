package me.tx.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.tx.guiapi.api.listeners.DecideConfirmListener;
import me.tx.guiapi.api.listeners.InventoryListener;

public class GuiAPIMain extends JavaPlugin {

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new DecideConfirmListener(), this);
	}	
}
