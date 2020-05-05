package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class find extends Command {

    public find(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(args[0]);
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8Processing Request...")));
            if (proxiedPlayer != null) {
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', String.format("&6Lookup for: &f%s", proxiedPlayer.getName()))));
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6Online: &atrue")));
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', String.format("&6IP: &f%s", proxiedPlayer.getSocketAddress()))));
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', String.format("&6Ping: &f%d", proxiedPlayer.getPing()))));
                if (proxiedPlayer.isForgeUser()) {
                    sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', String.format("&6Forge: &a%b", proxiedPlayer.isForgeUser()))));
                } else
                    sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', String.format("&6Forge: &c%b", proxiedPlayer.isForgeUser()))));
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', String.format("&6UUID: &f%s", proxiedPlayer.getUniqueId()))));
                sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', String.format("&6Connected to: %s", MCUtils.fixServer(proxiedPlayer.getServer().getInfo())))));
            } else MCUtils.playerNotFound((ProxiedPlayer) sender);
            } else MCUtils.printUsage((ProxiedPlayer) sender, "find", "<player>");
    }
}
