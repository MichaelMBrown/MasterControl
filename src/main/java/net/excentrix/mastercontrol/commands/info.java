package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class info extends Command {
    public info(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            MCUtils.informativeMessage((ProxiedPlayer) sender, "&7You're currently connected to &b" + MCUtils.findPlayer((ProxiedPlayer) sender) + "&7 and your ping is currently &b" + ((ProxiedPlayer) sender).getPing() + "&7ms");
        }
    }
}
