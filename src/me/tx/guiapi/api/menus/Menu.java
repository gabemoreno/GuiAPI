package me.tx.guiapi.api.menus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class Menu implements InventoryHolder {

	private final static List<Player> viewers = new ArrayList<>();
	private final Inventory inventory;
	private final Player player;
	private final int size;
	private final String title;
		
	public Menu(Player player, int rows, String title) {
		
		viewers.add(player);
		
		this.player = player;
		this.title = title;
		
		size = rows * 9;
		inventory = Bukkit.createInventory(this, size, title);
		
		//objects run build() then open()
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	
	public List<Player> getViewersList(){
		return viewers;
	}
	
	
	private ItemStack filler = null;
	
	public void setFiller(ItemStack item) {
		filler = item;
	}
	
	public void fill() {
		IntStream.range(0, inventory.getSize()).forEach(i -> inventory.setItem(i, filler));
	}
	
	public void fill(int[] slotsToFill) {
		Arrays.stream(slotsToFill).forEach(i -> inventory.setItem(i, filler));
	}
	
	public abstract void build();
	
	public void open() {
		player.openInventory(inventory);
	}
	
	//run in InventoryClickEvent listener
	public abstract void onClick(InventoryClickEvent event);
	
	public void openConfirmMenu(String title, ItemStack item, int slot) {
		close();
		new ConfirmMenu(this, title, item, slot);
	}
	
	public void close() {
		viewers.remove(player);
		player.closeInventory();
	}
	
	//What's this?
	public void onClose(InventoryCloseEvent event) {}
	
	public static void onDisable() {
		viewers.forEach(Player::closeInventory);
	}

}
