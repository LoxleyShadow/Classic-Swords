package loxleyshadow.blockhitting.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.netty.util.internal.ConcurrentSet;
import loxleyshadow.blockhitting.movement.SprintState;

public class Storage {
	public static ConcurrentHashMap<String, Boolean> bools = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<Player, Location> locs = new ConcurrentHashMap<>();
	public static Set<Material> instabreaks = new HashSet<>();
	public static Set<Player> inInv = new HashSet<>();
	public static ConcurrentSet<Player> blockers = new ConcurrentSet<>();
	public static ConcurrentSet<Player> swingers = new ConcurrentSet<>();
	public static ConcurrentHashMap<Player, SprintState> sprintStates = new ConcurrentHashMap<>();
	public static Random random = new Random();
	public static Map<UUID, Long> regens = new HashMap<>();
	public static ConcurrentHashMap<Player, Player> recent = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<Player, Double> recenthealth = new ConcurrentHashMap<>();
	public static ConcurrentSet<Player> kb = new ConcurrentSet<>();
}
