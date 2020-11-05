package net.excentrix.mastercontrol.messagingService;

import net.excentrix.mastercontrol.MasterControl;
import net.excentrix.mastercontrol.utils.MCUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class messagingUtils {

    public static boolean messageEligibility(ProxiedPlayer player) {
        return !MasterControl.DoNotDisturb.get(player.getName());
    }

    public static void messagePlayer(ProxiedPlayer sender, ProxiedPlayer receipt, String message) {
        for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if (p != sender && p != receipt) {
                if (MasterControl.socialSpy.get(p.getName()))
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8[" + MCUtils.fixServer(sender.getServer().getInfo()) + "&8] &e" + sender.getName() + " &7-> &e" + receipt.getName() + "&7 ") + message));
            }
        }
        ProxyServer.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&e" + sender.getName() + " - > " + receipt.getName() + " " + message));
        receipt.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8[" + MCUtils.fixServer(sender.getServer().getInfo()) + "&8] &e" + sender.getName() + " &7-> &eMe &7") + message));
        MasterControl.replyService.put(receipt.getName(), sender.getName());
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8[" + MCUtils.fixServer(sender.getServer().getInfo()) + "&8] &eMe &7-> &e" + receipt.getName() + "&7 ") + message));
    }
}
