package me.coca.cocaapluginlib;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ChatUTIL {
    public static String formatTime(long seconds) {
        if (seconds == 0) {
            return "§2§lREADY";
        }

        long minutes = seconds / 60L;
        long remainingSeconds = seconds % 60L;
        return "§4§l" + String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static void sendActionBar(Player player, String message) {
        player.sendActionBar(Component.text(message));
    }
}
