package net.excentrix.mastercontrol.messagingService;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class message extends Command {
    public message(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer commandSender = (ProxiedPlayer) sender;
        String message = "";
        if (args.length < 2) {
            MCUtils.printUsage(commandSender, getName(), "<player> <message>");
        } else {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            if (target != null && target != sender) {
                if (messagingUtils.messageEligibility(target) || commandSender.hasPermission("mastercontrol.bypass.dnd")) {
                    MasterControl.replyService.put(commandSender.getName(), target.getName());
                    for (int i = 1; i < args.length; ++i) {
                        message = message + args[i] + " ";
                    }
                    messagingUtils.messagePlayer(commandSender, target, message);
                } else {
                    MCUtils.errorMessage(commandSender, "Sorry, you cannot message this player.");
                }
            }
            if (target == sender) {
                MCUtils.errorMessage(commandSender, "You can't message yourself!");
            } else if (target == null) MCUtils.playerNotFound(commandSender);
        }
    }
}
