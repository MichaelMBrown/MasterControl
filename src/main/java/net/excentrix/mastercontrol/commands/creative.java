package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class creative extends Command {
    public creative() {
        super("creative");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "This command can only be run by a proxied player."));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.hasPermission("mastercontrol.staff")) {
            if (player.getServer().getInfo().getName().equalsIgnoreCase("creative")) {
                MCUtils.errorMessage(player, "You're already connected to this server!");
            } else {
                ServerInfo destination = ProxyServer.getInstance().getServerInfo("creative");
                if (destination == null) {
                    MCUtils.errorMessage(player, "That server doesn't exist!");
                } else {
                    MCUtils.informativeMessage(player, "&6Sending you to: " + ChatColor.AQUA + destination.getName());
                    player.connect(destination);
                }
            }
        } else MCUtils.errorMessage(player, "You are not allowed to join this server!");
    }
}