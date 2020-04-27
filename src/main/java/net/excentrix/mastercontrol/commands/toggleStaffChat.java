package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class toggleStaffChat extends Command {
    public toggleStaffChat(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }
        if (MasterControl.scEnabled.get(sender.getName())) {
            MasterControl.scEnabled.put(sender.getName(), false);
            MCUtils.informativeMessage((ProxiedPlayer) sender, "You have hidden staff messages.");
        } else {
            MasterControl.scEnabled.put(sender.getName(), true);
            MCUtils.informativeMessage((ProxiedPlayer) sender, "You're now receiving staff messages.");
        }
    }
}
