package net.excentrix.mastercontrol.eventListeners;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class staffJoin implements Listener {
    @EventHandler
    public void onStaffJoin(ServerConnectEvent loginEvent) {
        if (loginEvent.getPlayer().hasPermission("mastercontrol.use.staffchat") && loginEvent.getReason().equals(ServerConnectEvent.Reason.JOIN_PROXY)) {
            ProxiedPlayer player = loginEvent.getPlayer();
            MasterControl.scEnabled.put(loginEvent.getPlayer().getName(), true);
            if (!MasterControl.scToggled.containsKey(player.getName())) {
                MasterControl.scToggled.put(player.getName(), false);
            }
            try {
                MCUtils.scNotif("console", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " logged into the network at " + ChatColor.AQUA + MCUtils.fixServer(player.getServer().getInfo()) + ChatColor.GRAY + ".");
            } catch (NullPointerException ignored) {
            }
        } else if ((loginEvent.getPlayer().hasPermission("mastercontrol.use.staffchat") && !loginEvent.getReason().equals(ServerConnectEvent.Reason.JOIN_PROXY)) && !loginEvent.getReason().equals(ServerConnectEvent.Reason.UNKNOWN)) {
            ProxiedPlayer player = loginEvent.getPlayer();
            MCUtils.scNotif("console", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " joined the server " + ChatColor.AQUA + MCUtils.fixServer(loginEvent.getTarget()) + ChatColor.GRAY + " from " + ChatColor.AQUA + MCUtils.findPlayer(player) + ChatColor.GRAY + ".");
        } else if ((loginEvent.getPlayer().hasPermission("mastercontrol.use.staffchat") && loginEvent.getReason().equals(ServerConnectEvent.Reason.UNKNOWN))) {
            ProxiedPlayer player = loginEvent.getPlayer();
            MCUtils.scNotif("console", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " disconnected from the network.");
        }

    }

}
