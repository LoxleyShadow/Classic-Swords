package loxleyshadow.blockhitting.item;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import loxleyshadow.blockhitting.item.ItemType16.SwordType;
import loxleyshadow.blockhitting.util.Storage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

public class ClassicSword16 {
	private static int maxDur = 0;
	
	public static ItemStack toShield(ItemStack old, SwordType type) {
		String name = ChatColor.RESET + ItemType16.getDefaultName(type);

		ItemStack csword = new ItemStack(Material.SHIELD, 1);

		ItemMeta meta = csword.getItemMeta();
		((Damageable)meta).setDamage(ItemType16.getSwordModel(type));

		if(old.hasItemMeta()) {
			ItemMeta ometa = old.getItemMeta();
			if(ometa.hasDisplayName()) {
				name = ometa.getDisplayName();
			}
			
			if(ometa.hasLore()) {
				meta.setLore(ometa.getLore());
			}
		}
		
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.setDisplayName(name);
		csword.setItemMeta(meta);
		net.minecraft.server.v1_16_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(csword);
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();

		NBTTagCompound cstag = new NBTTagCompound();

		maxDur = old.getType().getMaxDurability();
		int dur = maxDur - ((Damageable)old.getItemMeta()).getDamage();

		cstag.set("Durability", SetAttributes16.nbtInt(dur));
		cstag.set("Type", SetAttributes16.nbtString(ItemType16.getShortName(type)));

		compound.set("ClassicSwords", cstag);
		nmsStack.setTag(compound);
		csword = CraftItemStack.asBukkitCopy(nmsStack);
		if(old.getEnchantments() != null) {
			csword.addUnsafeEnchantments(old.getEnchantments());
		}
		
		ItemMeta newMeta = csword.getItemMeta();
		double atdamage = ItemType16.getSwordAttackDamage(type) - 1;
		newMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", atdamage, Operation.ADD_NUMBER));
		newMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("generic.attackSpeed", 64, Operation.ADD_NUMBER));
		csword.setItemMeta(newMeta);
		
		return csword;
	}

	public static ItemStack toBlockShield(ItemStack old, SwordType type) {
		if(isCShield(old)) {
			String name = ChatColor.RESET + ItemType16.getDefaultName(type);

			ItemStack csword = new ItemStack(Material.SHIELD, 1);

			ItemMeta meta = csword.getItemMeta();
			((Damageable)meta).setDamage(ItemType16.getSwordBlockModel(type));

			if(old.hasItemMeta()) {
				ItemMeta ometa = old.getItemMeta();
				if(ometa.hasDisplayName()) {
					name = ometa.getDisplayName();
				}
				
				if(ometa.hasLore()) {
					meta.setLore(ometa.getLore());
				}
			}
			
			meta.setUnbreakable(true);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.setDisplayName(name);
			csword.setItemMeta(meta);
			net.minecraft.server.v1_16_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(csword);
			NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();

			NBTTagCompound cstag = new NBTTagCompound();

			int dur = getCShieldDurability(old);
			cstag.set("Durability", SetAttributes16.nbtInt(dur));
			cstag.set("Type", SetAttributes16.nbtString(ItemType16.getShortName(type)));

			NBTTagCompound blocktag = new NBTTagCompound();
			blocktag.set("BlockHit", SetAttributes16.nbtString("BlockHit"));

			compound.set("ClassicSwords", cstag);
			compound.set("BlockHit", blocktag);
			nmsStack.setTag(compound);
			csword = CraftItemStack.asBukkitCopy(nmsStack);
			if(old.getEnchantments() != null) {
				csword.addUnsafeEnchantments(old.getEnchantments());
			}
			
			ItemMeta newMeta = csword.getItemMeta();
			double atdamage = ItemType16.getSwordAttackDamage(type) - 1;
			newMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", atdamage, Operation.ADD_NUMBER));
			newMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("generic.attackSpeed", 64, Operation.ADD_NUMBER));
			csword.setItemMeta(newMeta);

			return csword;
		} else {
			return null;
		}

	}

	public static ItemStack blockToShield(ItemStack old, SwordType type) {
		if(isCBlockShield(old)) {

			String name = ChatColor.RESET + ItemType16.getDefaultName(type);

			ItemStack csword = new ItemStack(Material.SHIELD, 1);

			ItemMeta meta = csword.getItemMeta();	
			((Damageable)meta).setDamage(ItemType16.getSwordModel(type));

			if(old.hasItemMeta()) {
				ItemMeta ometa = old.getItemMeta();
				if(ometa.hasDisplayName()) {
					name = ometa.getDisplayName();
				}

				if(ometa.hasLore()) {
					meta.setLore(ometa.getLore());
				}
			}
			meta.setUnbreakable(true);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.setDisplayName(name);
			csword.setItemMeta(meta);
			net.minecraft.server.v1_16_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(csword);
			NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();

			NBTTagCompound cstag = new NBTTagCompound();

			int dur = getCShieldDurability(old);
			cstag.set("Durability", SetAttributes16.nbtInt(dur));
			cstag.set("Type", SetAttributes16.nbtString(ItemType16.getShortName(type)));

			compound.set("ClassicSwords", cstag);
			nmsStack.setTag(compound);
			csword = CraftItemStack.asBukkitCopy(nmsStack);
			if(old.getEnchantments() != null) {
				csword.addUnsafeEnchantments(old.getEnchantments());
			}
			
			ItemMeta newMeta = csword.getItemMeta();
			double atdamage = ItemType16.getSwordAttackDamage(type) - 1;
			newMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", atdamage, Operation.ADD_NUMBER));
			newMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("generic.attackSpeed", 64, Operation.ADD_NUMBER));
			csword.setItemMeta(newMeta);
			
			return csword;
		}else {
			return null;
		}

	}

	public static ItemStack toSword(ItemStack old, SwordType type) {
		String name = ChatColor.RESET + ItemType16.getDefaultName(type);

		ItemStack csword = new ItemStack(ItemType16.getMaterial(type), 1);
		if(old.getEnchantments() != null) {
			csword.addUnsafeEnchantments(old.getEnchantments());
		}
		ItemMeta meta = csword.getItemMeta();
		if(old.hasItemMeta()) {
			ItemMeta ometa = old.getItemMeta();
			if(ometa.hasDisplayName()) {
				name = ometa.getDisplayName();
			}

			if(ometa.hasLore()) {
				meta.setLore(ometa.getLore());
			}
		}
		
		meta.setUnbreakable(false);
		meta.setDisplayName(name);
		csword.setItemMeta(meta);
		net.minecraft.server.v1_16_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(csword);
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();

		NBTTagCompound cstag = new NBTTagCompound();

		compound.set("ClassicSwords", cstag);
		nmsStack.setTag(compound);
		csword = CraftItemStack.asBukkitCopy(nmsStack);

		maxDur = ItemType16.getMaterial(type).getMaxDurability();
		ItemMeta newMeta = csword.getItemMeta();
		((Damageable)newMeta).setDamage(maxDur - (int)getCShieldDurability(old));
		csword.setItemMeta(newMeta);

		return csword;
	}

	public static Boolean isCShield(ItemStack item) {
		if(item == null) {
			return false;
		}
		if(item.getType() != Material.SHIELD) {
			return false;
		}
		net.minecraft.server.v1_16_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
		if(!nms.hasTag() || nms.getTag() == null) {
			return false;
		}
		if(nms.getTag().hasKey("ClassicSwords")) {
			return true;
		}else {
			return false;
		}
	}

	public static Boolean isCBlockShield(ItemStack item) {
		net.minecraft.server.v1_16_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
		return nms.getTag() != null && isCShield(item) && nms.getTag().hasKey("ClassicSwords");
	}

	public static Boolean isBlocking(Player p) {
		return Storage.blockers.contains(p);
	}

	public static Boolean isCSword(ItemStack item) {
		if(!(item.getType() == Material.WOODEN_SWORD || item.getType() == Material.STONE_SWORD ||item.getType() == Material.IRON_SWORD || item.getType() == Material.GOLDEN_SWORD || item.getType() == Material.DIAMOND_SWORD)) {
			return false;
		}
		net.minecraft.server.v1_16_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
		if(!nms.hasTag() || nms.getTag() == null) {
			return false;
		}
		if(nms.getTag().hasKey("ClassicSwords")) {
			return true;
		}else {
			return false;
		}
	}

	public static Boolean isCItem(ItemStack item) {
		return isCSword(item) || isCShield(item);
	}

	public static Integer getCShieldDurability(ItemStack item) {
		if(!isCItem(item)) {
			return null;
		} else {
			NBTTagCompound cstag = getCSTag(item);
			int dur = cstag.getInt("Durability");
			return dur;
		}
	}

	public static String getCShieldShortName(ItemStack item) {
		if(!isCItem(item)) {
			return null;
		} else {
			NBTTagCompound cstag = getCSTag(item);
			String name = cstag.getString("Type");
			return name;
		}
	}

	public static void blockToShieldInv(Inventory inv) {
		for(int i = 0; i < inv.getSize()-1; i++){
			ItemStack item = inv.getItem(i);
			if(item != null && isCBlockShield(item)) {
				ItemStack newitem = blockToShield(item, ItemType16.getType(getCShieldShortName(item)));
				inv.setItem(i, newitem);
			}
		}
	}

	public static void toShieldInv(Inventory inv) {
		for(int i = 0; i < inv.getSize()-1; i++){
			ItemStack item = inv.getItem(i);
			if(item != null && ItemType16.isSword(item.getType())) {
				ItemStack newitem = toShield(item, ItemType16.getSwordType(item.getType()));
				inv.setItem(i, newitem);
			}
		}
	}

	public static void toSwordInv(Inventory inv) {
		for(int i = 0; i < inv.getSize()-1; i++){
			ItemStack item = inv.getItem(i);
			if(isCShield(item)) {
				ItemStack newitem = toSword(item, ItemType16.getType(getCShieldShortName(item)));
				inv.setItem(i, newitem);
			}
		}
	}

	public static Boolean hasOpenInv(Player p) {
		return Storage.inInv.contains(p);
	}

	public static void setCShieldDurability(Player p, ItemStack item, Integer dur) {
		if(isCItem(item)) {
			Boolean skip = false;
			if(item.containsEnchantment(Enchantment.DURABILITY)) {
				int level = item.getEnchantmentLevel(Enchantment.DURABILITY);
				int chance = (100/(level+1));
				int result = Storage.random.nextInt(101);
				skip = result > chance;
			}
			if(!skip) {
				NBTTagCompound cstag = getCSTag(item);
				cstag.set("Durability", SetAttributes16.nbtInt(dur));
				p.getInventory().setItemInMainHand(updateCSTag(item, cstag));
			}
		}
	}

	public static NBTTagCompound getCSTag(ItemStack item) {
		if(!isCItem(item)) {
			return null;
		}else {
			net.minecraft.server.v1_16_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
			NBTTagCompound cstag = (NBTTagCompound) nms.getTag().get("ClassicSwords");
			return cstag;
		}
	}

	public static ItemStack updateCSTag(ItemStack item, NBTTagCompound tag) {
		if(isCItem(item)) {
			net.minecraft.server.v1_16_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
			nms.getTag().set("ClassicSwords", tag);
			return CraftItemStack.asBukkitCopy(nms);
		}else {
			return null;
		}
	}

	public static void breakHeldItem(Player p) {
		p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 10.0F, 1.0F);
	}

	public static void damageCItem(Player p, ItemStack item, int amount) {
		int dur = ClassicSword16.getCShieldDurability(item) - amount;
		if(dur <= 0) {
			ClassicSword16.sendActionbar(p, ChatColor.BOLD+"Durability: " + ChatColor.ITALIC + dur);
			breakHeldItem(p);
		}else {
			if(isCItem(item)) {
				ClassicSword16.sendActionbar(p, ChatColor.BOLD+"Durability: " + ChatColor.ITALIC + dur);
				setCShieldDurability(p, item, dur);
			}
		}
	}

	public static void sendActionbar(Player player, String msg) {
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
	}

}