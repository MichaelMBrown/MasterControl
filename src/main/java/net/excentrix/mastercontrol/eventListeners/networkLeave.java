package net.excentrix.mastercontrol.eventListeners;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class networkLeave implements Listener {
    @EventHandler
    public void leaveNetwork(PlayerDisconnectEvent event) {
        if (event.getPlayer().hasPermission("mastercontrol.use.staffchat")) {
            ProxiedPlayer player = event.getPlayer();
            MCUtils.scNotif("console", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " disconnected from the network.");
        }
    }
}
