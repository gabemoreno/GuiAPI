package me.tx.guiapi.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.tx.guiapi.api.abstracts.ConfirmMenu;
import me.tx.guiapi.api.abstracts.Menu;

public class DecideConfirmEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private final ConfirmMenu confirmMenu;
	private final boolean decision;

	public DecideConfirmEvent(ConfirmMenu confirmMenu, boolean decision) {
		this.confirmMenu = confirmMenu;
		this.decision = decision;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public ConfirmMenu getConfirmMenu() {
		return confirmMenu;
	}

	public boolean getDecision() {
		return decision;
	}

	public Menu getParentMenu() {
		return confirmMenu.getParentMenu();
	}

	public int getParentSlot() {
		return confirmMenu.getParentSlot();
	}

	public Player getPlayer() {
		return confirmMenu.getPlayer();
	}
	
}
