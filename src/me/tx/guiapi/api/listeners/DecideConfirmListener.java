package me.tx.guiapi.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.tx.guiapi.api.events.DecideConfirmEvent;
import me.tx.guiapi.api.interfaces.Confirmable;

public class DecideConfirmListener implements Listener {

	@EventHandler
	public void onDecideConfirm(DecideConfirmEvent event) {
		event.getConfirmMenu().getParentMenu().open();
		((Confirmable) event.getConfirmMenu().getParentMenu()).onDecideConfirm(event);
	}
}
