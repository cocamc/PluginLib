package me.coca.cocaapluginlib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class GUIHelper implements Listener {
    private final JavaPlugin plugin;
    private final Map<Integer, GUIAction> actions;

    public GUIHelper(JavaPlugin plugin) {
        this.plugin = plugin;
        this.actions = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void openGUI(Player player, String title, int size) {
        Inventory inventory = Bukkit.createInventory(null, size, title);
        player.openInventory(inventory);
    }

    public void setItem(Player player, int slot, ItemStack item, GUIAction action) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        inventory.setItem(slot, item);
        actions.put(slot, action);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null || event.getClickedInventory().getHolder() != null)
            return;

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (actions.containsKey(slot)) {
            GUIAction action = actions.get(slot);
            action.onClick(player);
        }

        event.setCancelled(true);
    }

    public interface GUIAction {
        void onClick(Player player);
    }
}
