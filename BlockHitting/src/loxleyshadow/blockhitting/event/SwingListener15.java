package loxleyshadow.blockhitting.event;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import loxleyshadow.blockhitting.BlockHittingMain;
import loxleyshadow.blockhitting.item.ClassicSword15;
import loxleyshadow.blockhitting.item.ItemType15;
import loxleyshadow.blockhitting.movement.SprintState;
import loxleyshadow.blockhitting.util.Storage;

public class SwingListener15 implements Listener{

	@EventHandler
	public void onSwingAtBlock(BlockDamageEvent event){
		if(event.getBlock() != null){
			if(event.getPlayer().getGameMode() != GameMode.CREATIVE && event.getInstaBreak()) {
				Storage.instabreaks.add(event.getBlock().getType());
			}
		}
	}

	@EventHandler
	public void onSwingAtBlock(BlockBreakEvent event){
		if(event.getPlayer() != null){
			Player p = event.getPlayer();
			if(p.getInventory().getItemInMainHand() != null){
				if(p.getInventory().getItemInMainHand().hasItemMeta()){
					if(ClassicSword15.isCShield(p.getInventory().getItemInMainHand())){
						if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
							event.setCancelled(true);
						}else {
							if(!Storage.instabreaks.contains(event.getBlock().getType())) {
								ItemStack csword = p.getInventory().getItemInMainHand();
								ClassicSword15.damageCItem(p, csword, 2);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler (priority=EventPriority.MONITOR)
	public void onPlayerHit(EntityDamageByEntityEvent event){
		boolean debug = BlockHittingMain.getDebug();
		boolean modifiedKB = BlockHittingMain.getModifiedKB();
		
		if(event.getEntity() != null){
			if(event.getDamager() instanceof Player) {
				Player p = (Player)event.getDamager();
				ItemStack weapon = p.getInventory().getItemInMainHand();
				
				if (modifiedKB) {
					SprintState ss = Storage.sprintStates.get(p);
					if (ss.fixKB()) ((LivingEntity)event.getEntity()).setVelocity(ss.getKBDelt(p));
					ss.hitEntity();
				}

				if (debug) {
					new BukkitRunnable() {
						public void run() {
							System.out.println("Velocity = <"+Double.toString(event.getEntity().getVelocity().getX())+", "+Double.toString(event.getEntity().getVelocity().getY())+", "+Double.toString(event.getEntity().getVelocity().getZ())+">");
							System.out.println("Magnitude: "+Double.toString(event.getEntity().getVelocity().length()));
						}
					}.runTaskLaterAsynchronously(BlockHittingMain.getPlugin(), 1);
				}

				if(event.getEntity() instanceof Player) {
					Player damaged = (Player) event.getEntity();
					Storage.recent.put(p, damaged);
					Storage.recenthealth.put(damaged, damaged.getHealth());
					BlockHittingMain.runRecent(p, damaged);
				}

				if(ClassicSword15.isCShield(weapon) && p.getGameMode() != GameMode.CREATIVE) {
					ClassicSword15.damageCItem(p, weapon, 1);
				}
			}
		}
	}

	@EventHandler
	public void onHit(PlayerInteractEvent event){
		if(event.getPlayer() != null){
			Player p = event.getPlayer();
			if((event.getAction() == Action.LEFT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_AIR)) {
				if(ClassicSword15.isCShield(event.getPlayer().getInventory().getItemInMainHand())) {
					Storage.swingers.add(event.getPlayer());
					BlockHittingMain.runBlockHit(event.getPlayer());
					if(!ClassicSword15.isBlocking(p)) {
						ClassicSword15.blockToShieldInv(p.getInventory());
					}
				}
			}

			if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(Storage.swingers.contains(p) && ClassicSword15.isCShield(p.getInventory().getItemInMainHand())) {
					p.getInventory().setItemInMainHand(ClassicSword15.toBlockShield(p.getInventory().getItemInMainHand(), ItemType15.getType(ClassicSword15.getCShieldShortName(p.getInventory().getItemInMainHand()))));
					p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_STEP, 10.0F, 1.0F);

					if(Storage.recent.containsKey(p)) {
						final Player damaged = Storage.recent.get(p);
						Double health = Storage.recenthealth.get(damaged);
						if(damaged != null && damaged.getHealth() != health && !Storage.kb.contains(damaged)) {
							double kb = p.getEyeLocation().getDirection().getY();
							if(kb > damaged.getLocation().getDirection().getY()) {
								kb = damaged.getLocation().getDirection().getY();
							}

							kb = kb + 0.5;

							if(p.getLocation().getY() < damaged.getLocation().getY()) {
								kb = kb/2;
								damaged.setVelocity(p.getEyeLocation().getDirection().setY(kb).multiply(0.25));
							}else {
								damaged.setVelocity(p.getEyeLocation().getDirection().setY(kb).multiply(0.75));
							}	
						}
						Storage.recenthealth.remove(damaged);
						Storage.recent.remove(p);
					}
					Storage.swingers.remove(p);

					BlockHittingMain.blockToShieldTimer(p);
				}
				
				if (ClassicSword15.isCItem(p.getInventory().getItemInMainHand())) {
					Storage.sprintStates.get(p).swordBlock();
				}
			}
			
			try{
				ItemStack holding = p.getInventory().getItemInMainHand();
				if(event.hasBlock()){
					if(holding != null){
						if(holding.hasItemMeta()){
							if(ClassicSword15.isCShield(holding)){
								if(event.getAction().toString().contains("LEFT") ){
									if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
										event.setCancelled(true);
									}
								}
							}
						}
					}
				}

				if(ItemType15.isSword(holding.getType())) {
					ClassicSword15.toShield(holding, ItemType15.getSwordType(holding.getType()));
				}
			}
			catch(NullPointerException npex){

			}
		}
	}
}