package me.tx.guiapi.api.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ItemUtil {
	public static ItemStack makeItem(Material mat, int amount, String displayName, String... lore) {
		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(toColor(displayName));
		meta.setLore(toColor(Arrays.asList(lore)));
		item.setItemMeta(meta);
		return item;
	}
	
    public static String toColor(String str) {
    	return ChatColor.translateAlternateColorCodes('&', str);
    }
    
	public static List<String> toColor(List<String> str) {
		return str.stream().map(ItemUtil::toColor).collect(Collectors.toList());
	}
}