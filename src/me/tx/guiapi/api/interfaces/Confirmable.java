package me.tx.guiapi.api.interfaces;

import org.bukkit.inventory.ItemStack;

import me.tx.guiapi.api.events.DecideConfirmEvent;

public interface Confirmable {
	
	public abstract void onDecideConfirm(DecideConfirmEvent decideEvent);
	
	public abstract void openConfirmMenu(String title, ItemStack item, int slot);
	
}
