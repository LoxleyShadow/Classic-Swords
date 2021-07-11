package loxleyshadow.blockhitting;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import loxleyshadow.blockhitting.BlockHittingMain;
import loxleyshadow.blockhitting.event.BlockingListener;
import loxleyshadow.blockhitting.event.RegenListener;
import loxleyshadow.blockhitting.event.SprintListener;
import loxleyshadow.blockhitting.event.TexturesListener;
import loxleyshadow.blockhitting.movement.SprintState;
import loxleyshadow.blockhitting.util.Storage;
import net.md_5.bungee.api.ChatColor;

public class BlockHittingMain extends JavaPlugin {

	private static BlockHittingMain plugin;
	private static String version;
	
	private static boolean debug;
	private static boolean modifiedKB;

	public void onEnable() {
		plugin = this;
		
		String ver = Bukkit.getServer().getVersion();
		if (ver.contains("1.15")) version = "1.15";
		else if (ver.contains("1.16")) version = "1.16";
		else if (ver.contains("1.17")) version = "1.17";
		else version = "1.17";
		
		this.saveDefaultConfig();
		debug = this.getConfig().getBoolean("debug");
		modifiedKB = this.getConfig().getBoolean("modifiedKnockback");
		
		if (version == "1.15") {
			loxleyshadow.blockhitting.item.SetAttributes15.defineSwords();
			registerEvents15();
			startItemCheck15();
		} else if (version == "1.16") {
			loxleyshadow.blockhitting.item.SetAttributes16.defineSwords();
			registerEvents16();
			startItemCheck16();
		} else {
			loxleyshadow.blockhitting.item.SetAttributes17.defineSwords();
			registerEvents17();
			startItemCheck17();
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			Storage.sprintStates.put(p, new SprintState());
		}
	}

	public void onDisable() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if (version == "1.16") loxleyshadow.blockhitting.event.InventoryListener16.handleDisconnect(p);
			else loxleyshadow.blockhitting.event.InventoryListener17.handleDisconnect(p);
			SprintListener.handleDisconnect(p);
		}
		
		plugin = null;
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public static BlockHittingMain getMain(){
		return plugin;
	}
	
	public static String getVersion() {
		return version;
	}
	
	public static boolean getDebug() {
		return debug;
	}
	
	public static boolean getModifiedKB() {
		return modifiedKB;
	}

	public void registerEvents15() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new loxleyshadow.blockhitting.event.SwingListener15(), this);
		pm.registerEvents(new loxleyshadow.blockhitting.event.InventoryListener15(), this);
		pm.registerEvents(new BlockingListener(), this);
		pm.registerEvents(new RegenListener(this), this);
		pm.registerEvents(new TexturesListener(), this);
		pm.registerEvents(new SprintListener(), this);
	}
	
	public void registerEvents16() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new loxleyshadow.blockhitting.event.SwingListener16(), this);
		pm.registerEvents(new loxleyshadow.blockhitting.event.InventoryListener16(), this);
		pm.registerEvents(new BlockingListener(), this);
		pm.registerEvents(new RegenListener(this), this);
		pm.registerEvents(new TexturesListener(), this);
		pm.registerEvents(new SprintListener(), this);
	}
	
	public void registerEvents17() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new loxleyshadow.blockhitting.event.SwingListener17(), this);
		pm.registerEvents(new loxleyshadow.blockhitting.event.InventoryListener17(), this);
		pm.registerEvents(new BlockingListener(), this);
		pm.registerEvents(new RegenListener(this), this);
		pm.registerEvents(new TexturesListener(), this);
		pm.registerEvents(new SprintListener(), this);
	}

	public void startItemCheck15() {
		new BukkitRunnable() {
			public void run() {

				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getInventory().getItemInMainHand() != null) {

						if(loxleyshadow.blockhitting.item.ItemType15.isSword(p.getInventory().getItemInMainHand().getType()) && !loxleyshadow.blockhitting.item.ItemType15.isSword(p.getInventory().getItemInOffHand().getType())) {

							if(!loxleyshadow.blockhitting.item.ClassicSword15.hasOpenInv(p)) {
								if(!loxleyshadow.blockhitting.item.ClassicSword15.isBlocking(p) && !Storage.swingers.contains(p) && loxleyshadow.blockhitting.item.ClassicSword15.isCBlockShield(p.getInventory().getItemInMainHand())) {
									p.getInventory().setItemInMainHand(loxleyshadow.blockhitting.item.ClassicSword15.blockToShield(p.getInventory().getItemInMainHand(), loxleyshadow.blockhitting.item.ItemType15.getType(loxleyshadow.blockhitting.item.ClassicSword15.getCShieldShortName(p.getInventory().getItemInMainHand()))));
								}
								ItemStack newitem = loxleyshadow.blockhitting.item.ClassicSword15.toShield(p.getInventory().getItemInMainHand(), loxleyshadow.blockhitting.item.ItemType15.getSwordType(p.getInventory().getItemInMainHand().getType()));
								Integer dur = loxleyshadow.blockhitting.item.ClassicSword15.getCShieldDurability(newitem);
								p.getInventory().setItemInMainHand(newitem);
								loxleyshadow.blockhitting.item.ClassicSword15.sendActionbar(p, ChatColor.BOLD+"Durability: " + ChatColor.ITALIC + dur);
							}
						}

					}
				}
				
			}
		}.runTaskTimerAsynchronously(this, 1, 1);
	}
	
	public void startItemCheck16() {
		new BukkitRunnable() {
			public void run() {

				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getInventory().getItemInMainHand() != null) {

						if(loxleyshadow.blockhitting.item.ItemType16.isSword(p.getInventory().getItemInMainHand().getType()) && !loxleyshadow.blockhitting.item.ItemType16.isSword(p.getInventory().getItemInOffHand().getType())) {

							if(!loxleyshadow.blockhitting.item.ClassicSword16.hasOpenInv(p)) {
								if(!loxleyshadow.blockhitting.item.ClassicSword16.isBlocking(p) && !Storage.swingers.contains(p) && loxleyshadow.blockhitting.item.ClassicSword16.isCBlockShield(p.getInventory().getItemInMainHand())) {
									p.getInventory().setItemInMainHand(loxleyshadow.blockhitting.item.ClassicSword16.blockToShield(p.getInventory().getItemInMainHand(), loxleyshadow.blockhitting.item.ItemType16.getType(loxleyshadow.blockhitting.item.ClassicSword16.getCShieldShortName(p.getInventory().getItemInMainHand()))));
								}
								ItemStack newitem = loxleyshadow.blockhitting.item.ClassicSword16.toShield(p.getInventory().getItemInMainHand(), loxleyshadow.blockhitting.item.ItemType16.getSwordType(p.getInventory().getItemInMainHand().getType()));
								Integer dur = loxleyshadow.blockhitting.item.ClassicSword16.getCShieldDurability(newitem);
								p.getInventory().setItemInMainHand(newitem);
								loxleyshadow.blockhitting.item.ClassicSword16.sendActionbar(p, ChatColor.BOLD+"Durability: " + ChatColor.ITALIC + dur);
							}
						}

					}
				}
				
			}
		}.runTaskTimerAsynchronously(this, 1, 1);
	}
	
	public void startItemCheck17() {
		new BukkitRunnable() {
			public void run() {

				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getInventory().getItemInMainHand() != null) {

						if(loxleyshadow.blockhitting.item.ItemType17.isSword(p.getInventory().getItemInMainHand().getType()) && !loxleyshadow.blockhitting.item.ItemType17.isSword(p.getInventory().getItemInOffHand().getType())) {

							if(!loxleyshadow.blockhitting.item.ClassicSword17.hasOpenInv(p)) {
								if(!loxleyshadow.blockhitting.item.ClassicSword17.isBlocking(p) && !Storage.swingers.contains(p) && loxleyshadow.blockhitting.item.ClassicSword17.isCBlockShield(p.getInventory().getItemInMainHand())) {
									p.getInventory().setItemInMainHand(loxleyshadow.blockhitting.item.ClassicSword17.blockToShield(p.getInventory().getItemInMainHand(), loxleyshadow.blockhitting.item.ItemType17.getType(loxleyshadow.blockhitting.item.ClassicSword17.getCShieldShortName(p.getInventory().getItemInMainHand()))));
								}
								ItemStack newitem = loxleyshadow.blockhitting.item.ClassicSword17.toShield(p.getInventory().getItemInMainHand(), loxleyshadow.blockhitting.item.ItemType17.getSwordType(p.getInventory().getItemInMainHand().getType()));
								Integer dur = loxleyshadow.blockhitting.item.ClassicSword17.getCShieldDurability(newitem);
								p.getInventory().setItemInMainHand(newitem);
								loxleyshadow.blockhitting.item.ClassicSword17.sendActionbar(p, ChatColor.BOLD+"Durability: " + ChatColor.ITALIC + dur);
							}
						}

					}
				}
				
			}
		}.runTaskTimerAsynchronously(this, 1, 1);
	}

	public static void runBlockHit(Player p) {
		Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				Storage.swingers.remove(p);
			}
		}, 10L);
	}

	public static void runKB(Player p) {
		Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				Storage.kb.remove(p);
			}
		}, 20L);
	}

	public static void runBlock(Player p) {
		Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				Storage.blockers.remove(p);
			}
		}, 20L);
	}

	public static void runRecent(Player p, Player damaged) {
		Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				Storage.recent.remove(p);
				Storage.recenthealth.remove(damaged);
			}
		}, 20L);
	}

	public static void blockToShieldTimer(Player p) {
		Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				if (version == "1.15") {
					if(!loxleyshadow.blockhitting.item.ClassicSword15.hasOpenInv(p) && !Storage.swingers.contains(p)) {
						loxleyshadow.blockhitting.item.ClassicSword15.blockToShieldInv(p.getInventory());
					}
				} else if (version == "1.16") {
					if(!loxleyshadow.blockhitting.item.ClassicSword16.hasOpenInv(p) && !Storage.swingers.contains(p)) {
						loxleyshadow.blockhitting.item.ClassicSword16.blockToShieldInv(p.getInventory());
					}
				} else {
					if(!loxleyshadow.blockhitting.item.ClassicSword17.hasOpenInv(p) && !Storage.swingers.contains(p)) {
						loxleyshadow.blockhitting.item.ClassicSword17.blockToShieldInv(p.getInventory());
					}
				}
			}
		}, 6L);
	}

}
