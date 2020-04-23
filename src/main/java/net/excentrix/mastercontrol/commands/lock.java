package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class lock extends Command {
    public lock() {
        super("lock", "mastercontrol.command.lock");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "If you're sending this command via console, please login with an administrative account to execute this command, otherwise.. you must be a proxied player to execute this command."));
            return;
        }
        if (args.length == 0) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if ((MasterControl.networkedServers.get(player.getServer().getInfo()))) {
                MCUtils.scNotif("console", ChatColor.AQUA + MCUtils.findPlayer(player) + ChatColor.GRAY + " is now locked, and is limited to " + ChatColor.GOLD + "Staff" + ChatColor.GRAY + ".");
                MasterControl.networkedServers.put(player.getServer().getInfo(), false);
            } else {
                MCUtils.scNotif("console", ChatColor.AQUA + MCUtils.findPlayer(player) + ChatColor.GRAY + " is no longer locked.");
                MasterControl.networkedServers.put(player.getServer().getInfo(), true);
            }
        } else if (args.length == 1) {
            String selectedServer = args[0].toLowerCase();
            if (ProxyServer.getInstance().getServersCopy().containsKey(selectedServer)) {
                ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(selectedServer);
                if (MasterControl.networkedServers.get(serverInfo)) {
                    MCUtils.scNotif("console", ChatColor.AQUA + MCUtils.fixServer(serverInfo) + ChatColor.GRAY + " is now locked, and is limited to " + ChatColor.GOLD + "Staff" + ChatColor.GRAY + ".");
                    MasterControl.networkedServers.put(serverInfo, false);
                } else {
                    MCUtils.scNotif("console", ChatColor.AQUA + MCUtils.fixServer(serverInfo) + ChatColor.GRAY + " is no longer locked.");
                    MasterControl.networkedServers.put(serverInfo, true);
                }
            } else MCUtils.errorMessage((ProxiedPlayer) sender, "That server does not exist in the network!");

        }
    }
}
