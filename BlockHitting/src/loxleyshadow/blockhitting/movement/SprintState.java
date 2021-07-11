package loxleyshadow.blockhitting.movement;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SprintState {
	
	private int attacks;
	private boolean sprint;
	private boolean reset;
	
	public SprintState() {
		sprint = false;
		reset = true;
	}
	
	public void hitEntity() {
		reset = false;
		attacks++;
	}
	
	public void swordBlock() {
		reset = true;
	}
	
	public void newSprint() {
		attacks = 0;
	}
	
	public void setSprinting(boolean b) {
		sprint = b;
	}
	
	public boolean getSprinting() {
		return sprint;
	}
	
	public boolean getReset() {
		return reset;
	}
	
	public boolean fixKB() {
		if (attacks > 0 && sprint && reset) return true;
		else return false;
	}
	
	public Vector getKBDelt(Player p) {
		Vector eyeDir = p.getEyeLocation().getDirection();
		VecXZ dir = new VecXZ(eyeDir.getX(), eyeDir.getZ());
		dir.normalize();
		
		int kbEnchant = p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.KNOCKBACK);
		double hScale = getHorizontalScale(kbEnchant);
		
		Vector kb = new Vector(hScale*dir.getX(), 0.4, hScale*dir.getZ());
		return kb;
	}
	
	private double getHorizontalScale(int kbEnchant) {
		return 0.6 + kbEnchant*0.5;
	}
}
