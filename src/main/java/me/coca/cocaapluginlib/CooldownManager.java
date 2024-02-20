package me.coca.cocaapluginlib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private static Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();

    public static void addCooldown(Plugin plugin, Player player, String cooldownId, long cooldownInSeconds) {
        long currentTime = System.currentTimeMillis();
        long cooldownEndTime = currentTime + (cooldownInSeconds * 1000);

        Map<String, Long> playerCooldowns = cooldowns.getOrDefault(player.getUniqueId(), new HashMap<>());
        playerCooldowns.put(cooldownId, cooldownEndTime);
        cooldowns.put(player.getUniqueId(), playerCooldowns);

        Bukkit.getScheduler().runTaskLater(plugin, () -> removeCooldown(player.getUniqueId(), cooldownId), cooldownInSeconds * 20L - 1);
    }

    public static boolean hasCooldown(Player player, String cooldownId) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            Map<String, Long> playerCooldowns = cooldowns.get(player.getUniqueId());
            return playerCooldowns.containsKey(cooldownId);
        }

        return false;
    }

    public static long getRemainingCooldown(Player player, String cooldownId) {
        if (hasCooldown(player, cooldownId)) {
            long currentTime = System.currentTimeMillis();
            Map<String, Long> playerCooldowns = cooldowns.get(player.getUniqueId());
            long cooldownEndTime = playerCooldowns.get(cooldownId);
            long remainingTime = cooldownEndTime - currentTime;

            long remainingSeconds = Math.max(remainingTime / 1000, 0);

            return remainingSeconds;
        }

        return 0;
    }

    public static void removeCooldown(UUID playerId, String cooldownId) {
        if (cooldowns.containsKey(playerId)) {
            Map<String, Long> playerCooldowns = cooldowns.get(playerId);
            playerCooldowns.remove(cooldownId);

            if (playerCooldowns.isEmpty()) {
                cooldowns.remove(playerId);
            }
        }
    }

    public static void removePlayerCooldowns(Player player) {
        UUID playerId = player.getUniqueId();
        if (cooldowns.containsKey(playerId)) {
            cooldowns.remove(playerId);
        }
    }
}