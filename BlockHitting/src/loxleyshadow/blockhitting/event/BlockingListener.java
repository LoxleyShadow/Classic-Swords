package loxleyshadow.blockhitting.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;

@SuppressWarnings("deprecation")
public class BlockingListener  implements Listener{

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player) {

			Player p = (Player) event.getEntity();
			if(p.isHandRaised() || p.isBlocking()){
				double newdamage = event.getDamage() * 0.5;
				if(newdamage < 0) {
					newdamage = 0;
				}

				if(event.getDamage(DamageModifier.BLOCKING) >= 0){
					return;
				}
				event.setDamage(DamageModifier.BLOCKING, 0);
				if(event.getFinalDamage() >= newdamage) {
					event.setDamage(DamageModifier.BLOCKING, newdamage * -1);
				}
				
			}
		}
	}

	// for future use
	
	@SuppressWarnings("unused")
	private boolean hitShield(Location attacker, Location victim) {
		double attackerPitch = Math.toRadians(attacker.getPitch());
		double attackerYaw = Math.toRadians(attacker.getYaw());
		double attackerX = -Math.cos(attackerPitch) * Math.sin(attackerYaw);
		double attackerY = -Math.sin(attackerPitch);
		double attackerZ = Math.cos(attackerPitch) * Math.cos(attackerYaw);
		double victimPitch = Math.toRadians(victim.getPitch());
		double victimYaw = Math.toRadians(victim.getYaw());
		double victimX = -Math.cos(victimPitch) * Math.sin(victimYaw);
		double victimY = -Math.sin(victimPitch);
		double victimZ = Math.cos(victimPitch) * Math.cos(victimYaw);
		return (victimX * attackerX + victimY * attackerY + victimZ * attackerZ) < 0.6D;
	}




}
