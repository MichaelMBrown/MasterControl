package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class find extends Command {
    public find(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("mastercontrol.command.locate")) {
            if (args.length >= 1) {
                ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(args[0]);
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8Processing Request...")));
                if (proxiedPlayer != null) {
                    MCUtils.informativeMessage((ProxiedPlayer) sender, "&3" + proxiedPlayer.getName() + " &fis &aonline&f and connected to &b" + MCUtils.findPlayer(proxiedPlayer));
                } else MCUtils.playerNotFound((ProxiedPlayer) sender);
            } else MCUtils.printUsage((ProxiedPlayer) sender, "locate", "<player>");
        }
    }
}
