package eu.endercentral.crazy_advancements.packet;

import java.util.HashMap;

import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;

import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.Advancement;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.AdvancementFlag;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.item.ItemStack;

public class PacketConverter {
	
	private static AdvancementRewards advancementRewards = new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], null);
	
	private static HashMap<NameKey, Float> smallestX = new HashMap<>();
	private static HashMap<NameKey, Float> smallestY = new HashMap<>();
	
	public static void setSmallestX(NameKey tab, float smallestX) {
		PacketConverter.smallestX.put(tab, smallestX);
	}
	
	public static float getSmallestX(NameKey key) {
		return smallestX.containsKey(key) ? smallestX.get(key) : 0;
	}
	
	public static void setSmallestY(NameKey tab, float smallestY) {
		PacketConverter.smallestY.put(tab, smallestY);
	}
	
	public static float getSmallestY(NameKey key) {
		return smallestY.containsKey(key) ? smallestY.get(key) : 0;
	}
	
	public static float generateX(NameKey tab, float displayX) {
		return displayX - getSmallestX(tab);
	}
	
	public static float generateY(NameKey tab, float displayY) {
		return displayY - getSmallestY(tab);
	}
	
	/**
	 * Creates an NMS Advancement
	 * 
	 * @param advancement The Advancement to use as a base
	 * @return The NMS Advancement
	 */
	public static net.minecraft.advancements.Advancement toNmsAdvancement(Advancement advancement) {
		AdvancementDisplay display = advancement.getDisplay();
		
		ItemStack icon = CraftItemStack.asNMSCopy(display.getIcon());
		
		MinecraftKey backgroundTexture = null;
		boolean hasBackgroundTexture = display.getBackgroundTexture() != null;
		
		if(hasBackgroundTexture) {
			backgroundTexture = new MinecraftKey(display.getBackgroundTexture());
		}
		
		float x = generateX(advancement.getTab(), display.generateX());
		float y = generateY(advancement.getTab(), display.generateY());
		
		net.minecraft.advancements.AdvancementDisplay advDisplay = new net.minecraft.advancements.AdvancementDisplay(icon, display.getTitle().getBaseComponent(), display.getDescription().getBaseComponent(), backgroundTexture, display.getFrame().getNMS(), false, false, advancement.hasFlag(AdvancementFlag.SEND_WITH_HIDDEN_BOOLEAN));
		advDisplay.a(x, y);
		
		net.minecraft.advancements.Advancement parent = advancement.getParent() == null ? null : createDummy(advancement.getParent().getName());
		net.minecraft.advancements.Advancement adv = new net.minecraft.advancements.Advancement(advancement.getName().getMinecraftKey(), parent, advDisplay, advancementRewards, advancement.getCriteria().getCriteria(), advancement.getCriteria().getRequirements());
		
		return adv;
	}
	
	/**
	 * Creates an NMS Toast Advancement
	 * 
	 * @param advancement The Advancement to use as a base
	 * @return The NMS Advancement
	 */
	public static net.minecraft.advancements.Advancement toNmsToastAdvancement(Advancement advancement) {
		AdvancementDisplay display = advancement.getDisplay();
		
		ItemStack icon = CraftItemStack.asNMSCopy(display.getIcon());
		
		MinecraftKey backgroundTexture = null;
		boolean hasBackgroundTexture = display.getBackgroundTexture() != null;
		
		if(hasBackgroundTexture) {
			backgroundTexture = new MinecraftKey(display.getBackgroundTexture());
		}
		
		float x = generateX(advancement.getTab(), display.generateX());
		float y = generateY(advancement.getTab(), display.generateY());
		
		net.minecraft.advancements.AdvancementDisplay advDisplay = new net.minecraft.advancements.AdvancementDisplay(icon, display.getTitle().getBaseComponent(), display.getDescription().getBaseComponent(), backgroundTexture, display.getFrame().getNMS(), true, false, advancement.hasFlag(AdvancementFlag.SEND_WITH_HIDDEN_BOOLEAN));
		advDisplay.a(x, y);
		
		net.minecraft.advancements.Advancement parent = advancement.getParent() == null ? null : createDummy(advancement.getParent().getName());
		net.minecraft.advancements.Advancement adv = new net.minecraft.advancements.Advancement(advancement.getName().getMinecraftKey(), parent, advDisplay, advancementRewards, advancement.getCriteria().getCriteria(), advancement.getCriteria().getRequirements());
		
		return adv;
	}
	
	/**
	 * Creates a Dummy Advancement<br>Internally used to generate temporary parent advancements that need to be referenced in the packet
	 * 
	 * @param name The name of the Advancement
	 * @return the Dummy Advancement
	 */
	public static net.minecraft.advancements.Advancement createDummy(NameKey name) {
//		net.minecraft.advancements.AdvancementDisplay advDisplay = new net.minecraft.advancements.AdvancementDisplay(null, null, null, null, AdvancementFrameType.a, false, false, false);
		net.minecraft.advancements.Advancement adv = new net.minecraft.advancements.Advancement(name.getMinecraftKey(), null, null, null, new HashMap<>(), new String[0][0]);
		return adv;
	}
	
}