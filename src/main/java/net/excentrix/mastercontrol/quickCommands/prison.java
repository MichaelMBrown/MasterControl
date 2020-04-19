package net.excentrix.mastercontrol.quickCommands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class prison extends Command {
    public prison(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "This command can only be run by a proxied player."));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.getServer().getInfo().getName().equalsIgnoreCase("prison")) {
            MCUtils.errorMessage(player, "You're already connected to this server!");
        } else {
            ServerInfo destination = ProxyServer.getInstance().getServerInfo("prison");
            MCUtils.informativeMessage(player, "&7Attempting to send you to: " + ChatColor.AQUA + destination.getName());
            player.connect(destination);
        }
    }
}