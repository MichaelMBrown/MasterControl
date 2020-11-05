package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class grant extends Command {
    public grant(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer target;
        LuckPerms api = LuckPermsProvider.get();
        ProxiedPlayer commandSender = (ProxiedPlayer) sender;
        if (args.length != 2) {
            MCUtils.printUsage(commandSender, "grant", "<user> <grant>");
        } else {
            target = ProxyServer.getInstance().getPlayer(args[0]);
            if (target != null) {
                if (api.getGroupManager().getGroup(args[1]) != null) {
                    if (commandSender.hasPermission("group." + args[1])) {
                        String grantName = api.getGroupManager().getGroup(args[1]).getDisplayName();
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + target.getName() + " parent switchprimarygroup " + args[1]);
                        if (grantName != null) {
                            ProxyServer.getInstance().getLogger().info(ChatColor.DARK_RED + "User " + target.getName() + " was granted " + ChatColor.translateAlternateColorCodes('&', api.getGroupManager().getGroup(args[1]).getDisplayName()) + ChatColor.DARK_RED + " by " + sender.getName());
                            MCUtils.informativeMessage(commandSender, target.getName() + "&7's grant was updated to " + grantName + "&7!");
                            MCUtils.scNotif("console", sender.getName() + " has granted " + target.getName() + " the grant of " + grantName);
                            MCUtils.informativeMessage(target, "You have been granted " + grantName + "&7.");
                        } else {
                            MCUtils.informativeMessage(commandSender, target.getName() + "&7's grant was updated to " + api.getGroupManager().getGroup(args[1]).getName() + "&7!");
                            MCUtils.informativeMessage(target, "You have been granted " + api.getGroupManager().getGroup(args[1]).getName() + "&7.");
                            MCUtils.scNotif("console", sender.getName() + " has granted " + target.getName() + " the grant of " + api.getGroupManager().getGroup(args[1]).getName());
                            ProxyServer.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lUser &7" + target.getName() + "&6&l was granted: &f'" + api.getGroupManager().getGroup(args[1]).getName() + "&f'&6&l by &c&l" + sender.getName()));
                        }
                    } else {
                        MCUtils.actionForbidden(commandSender);
                    }
                } else {
                    MCUtils.errorMessage(commandSender, "You cannot grant " + args[1].toLowerCase() + "&c as it doesn't exist.");
                }
            } else MCUtils.playerNotFound(commandSender);
        }
    }
}

