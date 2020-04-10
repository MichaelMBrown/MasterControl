package net.excentrix.mastercontrol.eventListeners;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class staffTalk implements Listener {
    @EventHandler
    public void onTalkEvent(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            if (player.hasPermission("mastercontrol.use.staffchat")) {
                if (event.getMessage().startsWith("# ")) {
                    event.setCancelled(true);
                    MCUtils.scNotif(player.getName(), event.getMessage().substring(2));
                }
            }
        }
    }
}

