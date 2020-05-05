package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class grants extends Command {
    public grants(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer target;
        LuckPerms api = LuckPermsProvider.get();
        target = ProxyServer.getInstance().getPlayer(args[0]);
        try {
            String group = MCUtils.getRank(target.getName());
            String grantName = api.getGroupManager().getGroup(group).getDisplayName();
            if (grantName == null) {
                MCUtils.informativeMessage((ProxiedPlayer) sender, target.getName() + "&7 has the grant: " + api.getGroupManager().getGroup(group).getName() + "&7.");
            } else {
                MCUtils.informativeMessage((ProxiedPlayer) sender, target.getName() + "&7 has the grant: " + api.getGroupManager().getGroup(group).getDisplayName() + "&7.");
            }
        } catch (NullPointerException e) {
            MCUtils.playerNotFound((ProxiedPlayer) sender);
        }
    }
}
