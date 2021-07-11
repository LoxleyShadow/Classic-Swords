package loxleyshadow.blockhitting.event;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import loxleyshadow.blockhitting.item.ClassicSword17;
import loxleyshadow.blockhitting.item.ItemType17;
import loxleyshadow.blockhitting.util.Storage;
import net.md_5.bungee.api.ChatColor;

public class InventoryListener17 implements Listener {
	
	@EventHandler
	public void onInvOpen(InventoryOpenEvent event) {
		if(event.getPlayer() != null) {
			Storage.inInv.add((Player)event.getPlayer());
			ClassicSword17.toSwordInv(((Player)event.getPlayer()).getInventory());
		}
	}

	@EventHandler
	public void onInvClose(InventoryCloseEvent event) {
		if(event.getPlayer() != null) {
			Storage.inInv.remove((Player)event.getPlayer());
			ClassicSword17.toShieldInv(((Player)event.getPlayer()).getInventory());
			ClassicSword17.blockToShieldInv(((Player)event.getPlayer()).getInventory());
		}
	}

	@EventHandler
	public void onExp(PlayerExpChangeEvent event) {
		if(event.getPlayer() != null) {
			Player p = event.getPlayer();
			if(ClassicSword17.isCShield(p.getInventory().getItemInMainHand())) {
				ItemStack csword = p.getInventory().getItemInMainHand();
				if(csword.containsEnchantment(Enchantment.MENDING)) {
					int max = ItemType17.getMaxDurability(ItemType17.getType(ClassicSword17.getCShieldShortName(csword)));
					int dur = ClassicSword17.getCShieldDurability(csword);
					int expnum = event.getAmount();
					int pxpadd = 0;
					if(dur != max) {
						if(dur + expnum <= max) {
							ClassicSword17.setCShieldDurability(p, csword, dur + expnum);
							event.setAmount(0);
						} else if(dur + expnum > max){
							pxpadd = (dur + expnum) - max;
							ClassicSword17.setCShieldDurability(p, csword, max);
							event.setAmount(pxpadd);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if(event.getInventory() != null) {
			if(!event.isShiftClick()) {
				ClassicSword17.toSwordInv(p.getInventory());
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		handleDisconnect(event.getPlayer());
	}

	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		handleDisconnect(event.getPlayer());
	}

	public static void handleDisconnect(Player p) {
		Storage.inInv.remove(p);
		ClassicSword17.toSwordInv(p.getInventory());
	}

	@EventHandler
	public void onItemClick(InventoryDragEvent event) {
		if(event.getClass() != null) {
			if(ClassicSword17.isCShield(event.getCursor())) {
				ItemStack newsword = ClassicSword17.toSword(event.getCursor(), ItemType17.getType(ClassicSword17.getCShieldShortName(event.getCursor())));
				event.setCursor(newsword);
			}
		}
	}

	@EventHandler
	public void onHold(PlayerItemHeldEvent event){
		if(event.getPlayer() != null && ClassicSword17.isCShield(event.getPlayer().getInventory().getItem(event.getNewSlot()))){
			Integer dur = ClassicSword17.getCShieldDurability(event.getPlayer().getInventory().getItem(event.getNewSlot()));
			ClassicSword17.sendActionbar(event.getPlayer(), ChatColor.BOLD+"Durability: " + ChatColor.ITALIC + dur);
		}
	}

}