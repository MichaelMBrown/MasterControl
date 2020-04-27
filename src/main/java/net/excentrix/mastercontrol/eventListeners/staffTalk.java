package net.excentrix.mastercontrol.eventListeners;

import net.excentrix.mastercontrol.MasterControl;
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
                if (event.getMessage().startsWith("# ") && !MasterControl.scToggled.get(player.getName())) {
                    if (MasterControl.scEnabled.get(player.getName())) {
                        MCUtils.errorMessage(player, "You cannot chat in Staff Chat, as it's turned off.");
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(true);
                        MCUtils.scNotif(player.getName(), event.getMessage().substring(2));
                    }
                }
                if (MasterControl.scToggled.get(player.getName())) {
                    if (MasterControl.scEnabled.get(player.getName())) {
                        if (event.getMessage().startsWith("# ")) {
                            event.setCancelled(true);
                            MCUtils.scNotif(player.getName(), event.getMessage().substring(2));
                        } else if (event.getMessage().startsWith("/")) {
                        } else {
                            event.setCancelled(true);
                            MCUtils.scNotif(player.getName(), event.getMessage());
                        }
                    } else {
                        MCUtils.errorMessage(player, "You cannot chat in Staff Chat, as it's turned off, taking you out now.");
                        MasterControl.scToggled.put(player.getName(), false);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}

