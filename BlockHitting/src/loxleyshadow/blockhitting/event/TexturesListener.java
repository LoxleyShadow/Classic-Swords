package loxleyshadow.blockhitting.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import loxleyshadow.blockhitting.BlockHittingMain;
import net.md_5.bungee.api.ChatColor;

public class TexturesListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onJoin(PlayerJoinEvent e) {
		if (e.getPlayer() != null) {
			if (BlockHittingMain.getVersion() == "1.15") {
				e.getPlayer().setResourcePack("https://www.dropbox.com/s/pw49w7gczjuv4g6/CSwordPack_1_15_2.zip?dl=1");
			} else {
				e.getPlayer().setResourcePack("https://www.dropbox.com/s/yroufi34d0m0lfr/CSword.zip?dl=1");
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onResourcePackStatus(PlayerResourcePackStatusEvent e) {
		Player p = e.getPlayer();

		if(e.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED || e.getStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD) {
			p.sendMessage(ChatColor.BOLD+"You must accept the resource pack to see classic swords.");
		}
	}
	
}