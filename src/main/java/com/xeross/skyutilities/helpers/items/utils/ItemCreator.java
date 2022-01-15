package com.xeross.skyutilities.helpers.items.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.List;


@SuppressWarnings({"unused", "CommentedOutCode"})
public class ItemCreator implements Cloneable {
    private ItemStack item;
    private ItemMeta itemMeta;
    
    public ItemCreator(ItemStack item) {
        this.item = item.clone();
        itemMeta = this.item.getItemMeta();
    }
    
    public ItemCreator(Material material) {
        item = new ItemStack(material);
        itemMeta = item.getItemMeta();
    }
    
    @Override
    public ItemCreator clone() throws CloneNotSupportedException {
        return (ItemCreator) super.clone();
    }
    
    public ItemCreator setItem(Material material) {
        item = new ItemStack(material);
        itemMeta = item.getItemMeta();
        return this;
    }
    
    public ItemCreator setItem(ItemStack item) {
        this.item = item.clone();
        itemMeta = this.item.getItemMeta();
        return this;
    }
    
    public ItemCreator setName(String displayName) {
        if (itemMeta == null) return this;
        itemMeta.setDisplayName(displayName);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator setUnbreakable() {
        if (itemMeta == null) return this;
        item.getItemMeta().spigot().setUnbreakable(true);
        return this;
    }
    
    public ItemCreator setName() {
        if (itemMeta == null) return this;
        itemMeta.setDisplayName("§r");
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator setDurability(Short data) {
        if (itemMeta == null) return this;
        if (data == null) return this;
        item.setDurability(data);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator setDurability(byte data) {
        if (itemMeta == null) return this;
        item.setDurability(data);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    @Deprecated
    public ItemCreator setPotion(PotionType potionType, int level, Boolean isSplash, boolean extended) {
        if (itemMeta == null) return this;
        if (item.getType() != Material.POTION) return this;
        final Potion splash = new Potion(potionType, level, isSplash, extended);
/*        splash.setSplash(isSplash);
        splash.set(extended);*/
        item = splash.toItemStack(1);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator addEnchantment(Enchantment enchantment, int level) {
        if (itemMeta == null) return this;
        if (enchantment == null) {
            return this;
        }
        itemMeta.addEnchant(enchantment, level, true);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator removeEnchantment(Enchantment enchantment) {
        if (itemMeta == null) return this;
        if (!itemMeta.getEnchants().containsKey(enchantment)) return this;
        itemMeta.removeEnchant(enchantment);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator addBookEnchant(Enchantment enchantment, int level) {
        if (itemMeta == null || !(itemMeta instanceof EnchantmentStorageMeta)) return this;
        if (enchantment == null) return this;
        ((EnchantmentStorageMeta) itemMeta).addStoredEnchant(enchantment, level, false);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator setAmount(int amount) {
        if (itemMeta == null) return this;
        item.setAmount(amount);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator setOwner(Player player) {
        if (itemMeta == null || !(itemMeta instanceof SkullMeta)) return this;
        ((SkullMeta) itemMeta).setOwner(player.getName());
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator setItemFlag(ItemFlag... flag) {
        if (itemMeta == null) return this;
        itemMeta.addItemFlags(flag);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator setItemFlag(ItemFlag flag) {
        if (flag == null) return this;
        if (itemMeta == null) return this;
        itemMeta.addItemFlags(flag);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator addLore(String... strings) {
        if (itemMeta == null) return this;
        itemMeta.setLore(Arrays.asList(strings));
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemCreator addLore(List<String> strings) {
        if (itemMeta == null) return this;
        itemMeta.setLore(strings);
        item.setItemMeta(itemMeta);
        return this;
    }
    
    public static ItemStack setName(final ItemStack itemStack, final String name) {
        final ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    
    public ItemStack getItem() {
        return item;
    }
}
