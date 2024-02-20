package me.coca.cocaapluginlib;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class EventHelpers {
    public static boolean isRightClick(Action action) {
        if(action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            return true;
        }
        return false;
    }
    public static boolean isRightClickWhileSneaking(Action action, Player player) {
        if(action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            if(!player.isSneaking()) return false;
            return true;
        }
        return false;
    }
    public static boolean isLeftClick(Action action) {
        if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            return true;
        }
        return false;
    }
    public static boolean isLeftClickWhileSneaking(Action action, Player player) {
        if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            if(!player.isSneaking()) return false;
            return true;
        }
        return false;
    }
}
