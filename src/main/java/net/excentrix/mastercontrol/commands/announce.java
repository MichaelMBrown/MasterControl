package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class announce extends Command {
    public announce() {
        super("announce");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("mastercontrol.command.announce")) {
            String message = "";
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    message = message + args[i] + " ";
                }
                MCUtils.excentrixAnnounce(message);
            } else MCUtils.printUsage((ProxiedPlayer) sender, "announce", "<message>");
        }
    }
}
