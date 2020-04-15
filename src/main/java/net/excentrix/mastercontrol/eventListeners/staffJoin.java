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
            try {
                MCUtils.scNotif("console", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " logged into the network at " + ChatColor.AQUA + loginEvent.getTarget().getName() + ChatColor.GRAY + ".");
                MasterControl.scEnabled.put(loginEvent.getPlayer().getName(), true);
            } catch (NullPointerException ignored) {
                MasterControl.scEnabled.put(loginEvent.getPlayer().getName(), true);
            }
        } else if ((loginEvent.getPlayer().hasPermission("mastercontrol.use.staffchat") && !loginEvent.getReason().equals(ServerConnectEvent.Reason.JOIN_PROXY)) && !loginEvent.getReason().equals(ServerConnectEvent.Reason.UNKNOWN)) {
            ProxiedPlayer player = loginEvent.getPlayer();
            MCUtils.scNotif("console", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " joined the server " + ChatColor.AQUA + loginEvent.getTarget().getName() + ChatColor.GRAY + " from " + ChatColor.AQUA + loginEvent.getPlayer().getServer().getInfo().getName() + ChatColor.GRAY + ".");
        } else if ((loginEvent.getPlayer().hasPermission("mastercontrol.use.staffchat") && loginEvent.getReason().equals(ServerConnectEvent.Reason.UNKNOWN))) {
            ProxiedPlayer player = loginEvent.getPlayer();
            MCUtils.scNotif("console", ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " disconnected from the network.");
        }

    }

}
