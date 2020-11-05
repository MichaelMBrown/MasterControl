package net.excentrix.mastercontrol.messagingService;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class reply extends Command {
    public reply(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer commandSender = (ProxiedPlayer) sender;
        if (MasterControl.replyService.get(commandSender.getName()) != null) {
            String message = "";
            if (args.length < 1) {
                MCUtils.printUsage(commandSender, getName(), "<message>");
            } else {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(MasterControl.replyService.get(commandSender.getName()));
                if (target != null) {
                    if (messagingUtils.messageEligibility(target) || commandSender.hasPermission("mastercontrol.bypass.dnd")) {
                        for (String arg : args) {
                            message = message + arg + " ";
                        }
                        messagingUtils.messagePlayer(commandSender, target, message);
                    } else {
                        MCUtils.errorMessage(commandSender, "Sorry, you cannot message this player.");
                    }
                } else {
                    MCUtils.playerNotFound(commandSender);
                }
            }
        } else MCUtils.errorMessage(commandSender, "There was an error in processing your request.");
    }
}
