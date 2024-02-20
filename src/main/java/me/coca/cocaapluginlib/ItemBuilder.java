package me.coca.cocaapluginlib;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ItemBuilder {
    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, int amount, byte durability) {
        this.itemStack = new ItemStack(material, amount, durability);
    }

    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        if (itemStack.getItemMeta() instanceof SkullMeta) {
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwner(owner);
            itemStack.setItemMeta(skullMeta);
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setInfinityDurability() {
        itemStack.setDurability(Short.MAX_VALUE);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder removeLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> currentLore = new ArrayList<>(itemMeta.getLore());
        if (currentLore.remove(line)) {
            itemMeta.setLore(currentLore);
            itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> currentLore = new ArrayList<>(itemMeta.getLore());
        if (index >= 0 && index < currentLore.size()) {
            currentLore.remove(index);
            itemMeta.setLore(currentLore);
            itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> currentLore = new ArrayList<>(itemMeta.getLore());
        currentLore.add(line);
        itemMeta.setLore(currentLore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLoreLine(String line, int pos) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> currentLore = new ArrayList<>(itemMeta.getLore());
        if (pos >= 0 && pos < currentLore.size()) {
            currentLore.set(pos, line);
            itemMeta.setLore(currentLore);
            itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        if (itemStack.getItemMeta() instanceof LeatherArmorMeta) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            leatherArmorMeta.setColor(color);
            itemStack.setItemMeta(leatherArmorMeta);
        }
        return this;
    }

    public ItemStack toItemStack() {
        return itemStack.clone();
    }
}
