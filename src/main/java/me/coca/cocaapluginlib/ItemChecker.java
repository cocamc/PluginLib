package me.coca.cocaapluginlib;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemChecker {

    public static boolean checkForDisplayName(ItemStack itemStack, String name) {
        if (itemStack == null || !itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        Component displayName = itemMeta.displayName();

        return displayName != null && displayName.toString().equals(name);
    }

    public static boolean checkForCustomModelData(ItemStack itemStack, int modelData) {
        if (itemStack == null || !itemStack.hasItemMeta()) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        int customModelData = itemMeta.getCustomModelData();

        return customModelData == modelData;
    }
}
