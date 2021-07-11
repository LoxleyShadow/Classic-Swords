package loxleyshadow.blockhitting.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import loxleyshadow.blockhitting.movement.SprintState;
import loxleyshadow.blockhitting.util.Storage;

public class SprintListener implements Listener {

	@EventHandler
	public void toggleSprint(PlayerToggleSprintEvent event) {
		Storage.sprintStates.get(event.getPlayer()).setSprinting(event.isSprinting());
		if (event.isSprinting()) Storage.sprintStates.get(event.getPlayer()).newSprint();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Storage.sprintStates.put(event.getPlayer(), new SprintState());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		handleDisconnect(event.getPlayer());
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent event) {
		handleDisconnect(event.getPlayer());
	}
	
	public static void handleDisconnect(Player p) {
		Storage.sprintStates.remove(p);
	}
	
}
