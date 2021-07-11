package loxleyshadow.blockhitting.item;

import loxleyshadow.blockhitting.util.Storage16;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagDouble;
import net.minecraft.server.v1_16_R3.NBTTagInt;
import net.minecraft.server.v1_16_R3.NBTTagString;

public class SetAttributes16 {
	
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

		Storage16.damagetag16.put("diamond", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(4));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));

		Storage16.damagetag16.put("wood", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(5));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));

		Storage16.damagetag16.put("stone", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(6));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));

		Storage16.damagetag16.put("iron", damage);

		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(4));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));
		
		Storage16.damagetag16.put("gold", damage);
		
		damage = new NBTTagCompound();

		damage.set("AttributeName", nbtString("generic.attackDamage"));
		damage.set("Name", nbtString("generic.attackDamage"));
		damage.set("Amount", nbtInt(8));
		damage.set("Operation", nbtInt(0));
		damage.set("UUIDLeast", nbtInt(894654));
		damage.set("UUIDMost", nbtInt(2872));
		damage.set("Slot", nbtString("mainhand"));
		
		Storage16.damagetag16.put("netherite", damage);
	}

}
