package net.excentrix.mastercontrol.messagingService;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class togglepm extends Command {
    public togglepm(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer commandSender = (ProxiedPlayer) sender;
        if (MasterControl.DoNotDisturb.get(commandSender.getName())) {
            MasterControl.DoNotDisturb.put(commandSender.getName(), false);
            MCUtils.informativeMessage((ProxiedPlayer) sender, "You will now receive private messages from players.");
        } else {
            MasterControl.DoNotDisturb.put(commandSender.getName(), true);
            MCUtils.informativeMessage((ProxiedPlayer) sender, "You will no longer receive private messages from players.");
        }
    }
}
