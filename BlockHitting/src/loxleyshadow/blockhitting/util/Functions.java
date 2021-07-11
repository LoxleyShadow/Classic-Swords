package loxleyshadow.blockhitting.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Functions {

	public static Player playerGet(String uuid){
		UUID id = UUID.fromString(uuid);
		Player p = Bukkit.getPlayer(id);
		return p;
	}

	public static Player getEP(String playername){
		Player p = null;
		for(Player item : Bukkit.getOnlinePlayers()){
			if(playername.equalsIgnoreCase(item.getName())){
				p = item;
			}
		}
		return p;
	}

	public static boolean isDigit(String s){
		return s.matches("[0-9]+");
	}


	public static boolean isInteger(String str, int r){
		if(str.isEmpty()){
			return false;
		}
		for(int i = 0; i < str.length(); i++){
			if((i == 0) && (str.charAt(i) == '-')){
				if(str.length() == 1) {
					return false;
				}
			}else if(Character.digit(str.charAt(i), r) < 0) {
				return false;
			}
		}
		return true;
	}

}
