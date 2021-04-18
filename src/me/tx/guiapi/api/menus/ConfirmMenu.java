package me.tx.guiapi.api.menus;

import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.tx.guiapi.api.events.DecideConfirmEvent;
import me.tx.guiapi.api.util.GeneralUtil;


public class ConfirmMenu extends Menu {

	public ConfirmMenu(Menu menu, String title, ItemStack item, int parentSlot) {
		super(menu.getPlayer(), 3, title);
		this.parentMenu = menu;
		this.parentSlot = parentSlot;
		menu.close();
		build();
		open();
	}
	
	private final Menu parentMenu;

	private final Inventory inventory = getInventory();
	private final ItemStack filler = GeneralUtil.makeItem(Material.BLACK_STAINED_GLASS_PANE, 1, "");
	private final ItemStack confirmItem = GeneralUtil.makeItem(Material.LIME_STAINED_GLASS_PANE, 1, "&a&lConfirm");
	private final int confirmSlot = 11;
	private final ItemStack denyItem = GeneralUtil.makeItem(Material.RED_STAINED_GLASS_PANE, 1, "&4&lDeny");
	private final int denySlot = 15;
	private final int parentSlot;
	
	
	@Override
	public void build() {
		IntStream.range(0, inventory.getSize()).forEach(i -> inventory.setItem(i, filler));
		inventory.setItem(confirmSlot, confirmItem);
		inventory.setItem(denySlot, denyItem);
	}
	
	@Override
	public void onClick(InventoryClickEvent event) {
		if (event.getCurrentItem().isSimilar(filler)) {
			event.getWhoClicked().sendMessage("SIMILAR");
			return;
		}
		event.getWhoClicked().sendMessage("NOT SIMILAR");
		Boolean decision = null;
		switch (event.getSlot()) {
		case confirmSlot:
			decision = true;
			break;
		case denySlot:
			decision = false;
			break;
		default:
			break;
		}
		if (decision != null) {
			event.getWhoClicked().sendMessage("CALLING EVENT");
			DecideConfirmEvent decideEvent = new DecideConfirmEvent(this, decision);
			Bukkit.getPluginManager().callEvent(decideEvent);
		}
	}
	
	public Menu getParentMenu() {
		return parentMenu;
	}
	
	public int getParentSlot() {
		return parentSlot;
	}
}
