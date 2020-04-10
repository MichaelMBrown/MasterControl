package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class lockdown extends Command {
    public lockdown() {
        super("lockdown", "mastercontrol.command.lockdown");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "If you're sending this command via console, please login with an administrative account to execute this command, otherwise.. you must be a proxied player to execute this command."));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (MasterControl.restrictedServers.contains(player.getServer().getInfo().getName())) {
            MCUtils.scNotif("console", ChatColor.AQUA + player.getServer().getInfo().getName() + ChatColor.GRAY + " has now exited lockdown, and is no longer limited to " + ChatColor.YELLOW + "Staff+" + ChatColor.GRAY + ".");
            MasterControl.restrictedServers.remove(player.getServer().getInfo().getName());
        } else {
            MCUtils.scNotif("console", ChatColor.AQUA + player.getServer().getInfo().getName() + ChatColor.GRAY + " has now entered lockdown, and is limited to " + ChatColor.YELLOW + "Staff+" + ChatColor.GRAY + ".");
            MasterControl.restrictedServers.add(player.getServer().getInfo().getName());
        }
    }
}
