package net.excentrix.mastercontrol.eventListeners;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class switchServer implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleSwitch(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (!MasterControl.networkedServers.get(event.getTarget()) && !player.hasPermission("mastercontrol.staff")) {
            event.setCancelled(true);
            MCUtils.errorMessage(player, "You are not allowed to connect to this server");
        } else
            player.resetTabHeader();
    }
}
