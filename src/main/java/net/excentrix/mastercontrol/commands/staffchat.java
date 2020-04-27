package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staffchat extends Command {


    public staffchat(String name, String permission, String... aliases) {
        super(name, permission, aliases);
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
                if (!MasterControl.scEnabled.get(sender.getName())) {
                    MCUtils.errorMessage((ProxiedPlayer) sender, "You cannot chat in Staff Chat, as it's turned off.");
                    return;
                }
                if (args.length > 0) {
                    String message = String.join(" ", args);
                    MCUtils.scNotif(sender.getName(), message);
                } else {
                    if (MasterControl.scToggled.get(sender.getName())) {
                        MCUtils.informativeMessage((ProxiedPlayer) sender, "You've exited the Staff Chat.");
                        MasterControl.scToggled.put(sender.getName(), false);
                    } else {
                        MCUtils.informativeMessage((ProxiedPlayer) sender, "You've entered the Staff Chat.");
                        MasterControl.scToggled.put(sender.getName(), true);
                    }
                }
            } else MCUtils.noPerm((ProxiedPlayer) sender);
        }
    }
}
