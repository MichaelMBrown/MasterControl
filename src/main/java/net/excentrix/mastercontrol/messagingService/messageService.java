package net.excentrix.mastercontrol.messagingService;

import net.excentrix.mastercontrol.MasterControl;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class messageService implements Listener {
    @EventHandler
    public void fixReply(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (!MasterControl.replyService.containsKey(player.getName())) {
            MasterControl.replyService.put(player.getName(), null);
        }
    }

    @EventHandler
    public void fixDND(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (!MasterControl.DoNotDisturb.containsKey(player.getName())) {
            MasterControl.DoNotDisturb.put(player.getName(), false);
        }
    }

    @EventHandler
    public void fixSS(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (!MasterControl.socialSpy.containsKey(player.getName())) {
            MasterControl.socialSpy.put(player.getName(), false);
        }
    }
}