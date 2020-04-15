package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staffchat extends Command {

    public staffchat(String name) {
        super(name);
    }

    /**
     * Execute this command with the specified sender and arguments.
     *
     * @param sender the executor of this command
     * @param args   arguments used to invoke this command
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer)) {
            if (sender.hasPermission("mastercontrol.use.staffchat")) {
                if (args.length > 0) {
                    String message = String.join(" ", args);
                    //plugin.getLogger().info(ChatColor.GOLD + ((Player) sender).getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message);
                    MCUtils.scNotif(sender.getName(), message);
                } else {
                    MCUtils.printUsage((ProxiedPlayer) sender, "sc", "<message>");
                }
            } else MCUtils.noPerm((ProxiedPlayer) sender);
        }
    }
}
