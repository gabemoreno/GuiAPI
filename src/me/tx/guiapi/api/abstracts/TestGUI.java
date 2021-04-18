package me.tx.guiapi.api.abstracts;

import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.tx.guiapi.Interfaces.Confirmable;
import me.tx.guiapi.api.events.DecideConfirmEvent;
import me.tx.guiapi.api.util.ItemUtil;
import net.md_5.bungee.api.ChatColor;

public class TestGUI extends Menu implements Confirmable {

	public TestGUI(Player player) {
		super(player, 1, "This is a test GUI");
		build();
		open();
	}
	
	private final ItemStack filler = ItemUtil.makeItem(Material.BLACK_STAINED_GLASS_PANE, 1, "");
	private final int slot1 = 1;
	private final int slot2 = 3;

	@Override
	public void build() {
		IntStream.range(0, getInventory().getSize()).forEach(i -> getInventory().setItem(i, filler));
		getInventory().setItem(slot1, new ItemStack(Material.DIAMOND));
		getInventory().setItem(slot2, new ItemStack(Material.EMERALD));
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		switch (event.getSlot()) {
		case slot1: 
			getPlayer().sendMessage("diamond");
			break;
		case slot2:
			openConfirmMenu("DO YOU REALLY WANT ME TO SAY EMERALD", new ItemStack(Material.EMERALD), event.getSlot());
			break;
		default:
			break;
		}
	}

	@Override
	public void onDecideConfirm(DecideConfirmEvent event) {
		event.getConfirmMenu().getPlayer().sendMessage(ChatColor.GREEN + "hi this is green");
		Bukkit.broadcastMessage(event.getParentSlot() + " " + (event.getDecision() ? "CONFIRMED" : "DENIED"));
	}
}
