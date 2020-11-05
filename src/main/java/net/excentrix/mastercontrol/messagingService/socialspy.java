package net.excentrix.mastercontrol.messagingService;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class socialspy extends Command {
    public socialspy(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer commandSender = (ProxiedPlayer) sender;
        if (MasterControl.socialSpy.get(commandSender.getName())) {
            MasterControl.socialSpy.put(commandSender.getName(), false);
            MCUtils.informativeMessage(commandSender, "You have disabled admin spying.");
        } else {
            MasterControl.socialSpy.put(commandSender.getName(), true);
            MCUtils.informativeMessage(commandSender, "You have enabled admin spying.");
        }

    }
}
