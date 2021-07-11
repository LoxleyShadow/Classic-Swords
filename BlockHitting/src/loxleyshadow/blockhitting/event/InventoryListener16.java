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

import loxleyshadow.blockhitting.item.ClassicSword16;
import loxleyshadow.blockhitting.item.ItemType16;
import loxleyshadow.blockhitting.util.Storage;
import net.md_5.bungee.api.ChatColor;

public class InventoryListener16 implements Listener {
	
	@EventHandler
	public void onInvOpen(InventoryOpenEvent event) {
		if(event.getPlayer() != null) {
			Storage.inInv.add((Player)event.getPlayer());
			ClassicSword16.toSwordInv(((Player)event.getPlayer()).getInventory());
		}
	}

	@EventHandler
	public void onInvClose(InventoryCloseEvent event) {
		if(event.getPlayer() != null) {
			Storage.inInv.remove((Player)event.getPlayer());
			ClassicSword16.toShieldInv(((Player)event.getPlayer()).getInventory());
			ClassicSword16.blockToShieldInv(((Player)event.getPlayer()).getInventory());
		}
	}

	@EventHandler
	public void onExp(PlayerExpChangeEvent event) {
		if(event.getPlayer() != null) {
			Player p = event.getPlayer();
			if(ClassicSword16.isCShield(p.getInventory().getItemInMainHand())) {
				ItemStack csword = p.getInventory().getItemInMainHand();
				if(csword.containsEnchantment(Enchantment.MENDING)) {
					int max = ItemType16.getMaxDurability(ItemType16.getType(ClassicSword16.getCShieldShortName(csword)));
					int dur = ClassicSword16.getCShieldDurability(csword);
					int expnum = event.getAmount();
					int pxpadd = 0;
					if(dur != max) {
						if(dur + expnum <= max) {
							ClassicSword16.setCShieldDurability(p, csword, dur + expnum);
							event.setAmount(0);
						} else if(dur + expnum > max){
							pxpadd = (dur + expnum) - max;
							ClassicSword16.setCShieldDurability(p, csword, max);
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
				ClassicSword16.toSwordInv(p.getInventory());
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
		ClassicSword16.toSwordInv(p.getInventory());
	}

	@EventHandler
	public void onItemClick(InventoryDragEvent event) {
		if(event.getClass() != null) {
			if(ClassicSword16.isCShield(event.getCursor())) {
				ItemStack newsword = ClassicSword16.toSword(event.getCursor(), ItemType16.getType(ClassicSword16.getCShieldShortName(event.getCursor())));
				event.setCursor(newsword);
			}
		}
	}

	@EventHandler
	public void onHold(PlayerItemHeldEvent event){
		if(event.getPlayer() != null && ClassicSword16.isCShield(event.getPlayer().getInventory().getItem(event.getNewSlot()))){
			Integer dur = ClassicSword16.getCShieldDurability(event.getPlayer().getInventory().getItem(event.getNewSlot()));
			ClassicSword16.sendActionbar(event.getPlayer(), ChatColor.BOLD+"Durability: " + ChatColor.ITALIC + dur);
		}
	}

}