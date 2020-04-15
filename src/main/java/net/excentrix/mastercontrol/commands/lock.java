package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
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
            if (MasterControl.restrictedServers.contains(player.getServer().getInfo().getName())) {
                MCUtils.scNotif("console", ChatColor.AQUA + player.getServer().getInfo().getName() + ChatColor.GRAY + " is no longer locked.");
                MasterControl.restrictedServers.remove(player.getServer().getInfo().getName());
            } else {
                MCUtils.scNotif("console", ChatColor.AQUA + player.getServer().getInfo().getName() + ChatColor.GRAY + " is now locked, and is limited to " + ChatColor.GOLD + "Staff" + ChatColor.GRAY + ".");
                MasterControl.restrictedServers.add(player.getServer().getInfo().getName());
            }
        } else if (args.length == 1) {
            String selectedServer = args[0].toLowerCase();
            if (ProxyServer.getInstance().getServersCopy().containsKey(selectedServer)) {
                if (MasterControl.restrictedServers.contains(selectedServer)) {
                    MCUtils.scNotif("console", ChatColor.AQUA + selectedServer + ChatColor.GRAY + " is no longer locked.");
                    MasterControl.restrictedServers.remove(selectedServer);
                } else {
                    MCUtils.scNotif("console", ChatColor.AQUA + selectedServer + ChatColor.GRAY + " is now locked, and is limited to " + ChatColor.GOLD + "Staff" + ChatColor.GRAY + ".");
                    MasterControl.restrictedServers.add(selectedServer);
                }
            } else MCUtils.errorMessage((ProxiedPlayer) sender, "That server does not exist in the network!");

        }
    }
}
