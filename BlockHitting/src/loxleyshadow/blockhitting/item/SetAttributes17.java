package loxleyshadow.blockhitting.item;

import loxleyshadow.blockhitting.util.Storage17;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;

public class SetAttributes17 {
	
	// For readability and future changes 
	public static NBTTagString nbtString(String x) {
		return NBTTagString.a(x);
	}

	public static NBTTagInt nbtInt(int x) {
		return NBTTagInt.a(x);
	}
	
	public static NBTTagDouble nbtDouble(double x) {
		return NBTTagDouble.a(x);
	}
	
	//
	
	public static void defineSwords() {

		NBTTagCompound damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(7));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));

		Storage17.damagetag17.put("diamond", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(4));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));

		Storage17.damagetag17.put("wood", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(5));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));

		Storage17.damagetag17.put("stone", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(6));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));

		Storage17.damagetag17.put("iron", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(4));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));
		
		Storage17.damagetag17.put("gold", damage);
		
		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(8));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));
		
		Storage17.damagetag17.put("netherite", damage);
	}

}
