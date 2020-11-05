package net.excentrix.mastercontrol.commands;

import net.excentrix.mastercontrol.MasterControl;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class masterControl extends Command {
    public masterControl() {
        super("mastercontrol", "", "mc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6MasterControl&f Running version v" + ProxyServer.getInstance().getPluginManager().getPlugin("MasterControl").getDescription().getVersion())));
        if (sender.getName().equals("qr0")) {
            try {
                if (args[0].equalsIgnoreCase("debugss")) {
                    sender.sendMessage(new TextComponent(MasterControl.socialSpy.toString()));
                } else if (args[0].equalsIgnoreCase("debugdnd")) {
                    sender.sendMessage(new TextComponent(MasterControl.DoNotDisturb.toString()));
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }
}
