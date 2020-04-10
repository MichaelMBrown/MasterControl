package net.excentrix.mastercontrol.customEvents;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

public class ServerSwapEvent extends Event {
    public final ServerInfo fromServer;
    public final ProxiedPlayer player;
    public final ServerInfo serverDestination;

    public ServerSwapEvent(ServerInfo fromServer, ProxiedPlayer player, ServerInfo serverDestination) {
        this.fromServer = fromServer;
        this.player = player;
        this.serverDestination = serverDestination;
    }
}
