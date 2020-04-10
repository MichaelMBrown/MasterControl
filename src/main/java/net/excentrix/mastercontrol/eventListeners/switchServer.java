package net.excentrix.mastercontrol.eventListeners;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class switchServer implements Listener {
    @EventHandler
    public void handleSwitch(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (MasterControl.restrictedServers.contains(event.getTarget().getName()) && !event.getReason().equals(ServerConnectEvent.Reason.JOIN_PROXY)) {
            if (!(event.getPlayer().hasPermission("mastercontrol.staff"))) {
                MCUtils.errorMessage(player, "You are not allowed to connect to this server");
                event.setCancelled(true);
            } else
                MCUtils.informativeMessage(player, "&6Sending you to: " + ChatColor.AQUA + event.getTarget().getName());
        }
        player.resetTabHeader();
    }
}
