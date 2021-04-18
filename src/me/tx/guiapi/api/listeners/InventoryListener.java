package me.tx.guiapi.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

import me.tx.guiapi.api.interfaces.MiddleClickable;
import me.tx.guiapi.api.interfaces.ShiftClickable;
import me.tx.guiapi.api.menus.Menu;


public class InventoryListener implements Listener {
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		InventoryHolder holder = event.getInventory().getHolder();
		if (holder == null || !(holder instanceof Menu)) {
			return;
		}
		((Menu) holder).onClose(event);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		InventoryHolder holder = event.getInventory().getHolder();
		if (holder == null || !(holder instanceof Menu)) {
			return;
		}
		
		event.setCancelled(true);
		Menu gui = (Menu) holder;
		
		if(!event.getView().getTopInventory().equals(event.getClickedInventory()))
			return;

		if(event.isShiftClick()) {
			if(!(gui instanceof ShiftClickable))
				return;
			((ShiftClickable)gui).onShiftClick(event);
		}
		
		if(event.getClick() == ClickType.MIDDLE) {
			if(!(gui instanceof MiddleClickable))
				return;
			((MiddleClickable)gui).onMiddleClick(event);
		}
		
		if (event.getClick() != ClickType.LEFT && event.getClick() != ClickType.RIGHT) {
			return;
		}
		
		gui.onClick(event);
	}
}
