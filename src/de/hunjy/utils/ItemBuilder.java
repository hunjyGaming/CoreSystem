package de.hunjy.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

	private ItemStack item;
	private ItemMeta meta;
	
	public ItemBuilder(Material matrial) {
		item = new ItemStack(matrial);
		meta = item.getItemMeta();
	}
	public ItemBuilder(Material matrial, int amount) {
		item = new ItemStack(matrial, amount);
		meta = item.getItemMeta();
	}
	public ItemBuilder(Material matrial, short subid) {
		item = new ItemStack(matrial, 1, subid);
		meta = item.getItemMeta();
	}

	public ItemBuilder(Material matrial, short subid, int amount) {
		item = new ItemStack(matrial, amount, subid);
		meta = item.getItemMeta();
	}
	
	public ItemBuilder removeAllAtributs() {
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		return this;
	}
	
	public ItemBuilder setDisplayName(String name) {
		meta.setDisplayName(name);
		return this;
	}
	
	public ItemBuilder addLore(ArrayList<String> lore) {
		meta.setLore(lore);
		return this;
	}

	public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
		meta.addEnchant(enchantment, level, true);
		return this;
	}


	public ItemStack build() {
		item.setItemMeta(meta);
		return item;
	}
	
}
