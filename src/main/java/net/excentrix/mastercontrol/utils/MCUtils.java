package net.excentrix.mastercontrol.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MCUtils {
    public static void scNotif(String sender, String message) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(sender);
        for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if (p.hasPermission("mastercontrol.use.staffchat")) {
                if (sender.equalsIgnoreCase("console")) {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&a&lMasterControl&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                } else if (sender.equalsIgnoreCase("qr0")) {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&f" + player.getServer().getInfo().getName() + "&7] &b&l" + sender + "&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                } else
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&f" + player.getServer().getInfo().getName() + "&7] &a" + sender + "&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));

            }
        }
        ProxyServer.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&f&l<&5&lSC&f&l> &a" + sender + "&f: " + message));
    }

    public static void informativeMessage(ProxiedPlayer sender, String message) {
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&a&lMC &f" + message)));
    }

    public static void errorMessage(ProxiedPlayer sender, String message) {
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&lMC &c" + message)));
    }

    public static void actionForbidden(ProxiedPlayer player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &cYou are forbidden perform this action."));
    }

    public static void noPerm(ProxiedPlayer player) {
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &cYou do not have permission for this command!")));
    }

    public static void playerNotFound(ProxiedPlayer player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &cThere is no player by that name connected to this server!"));
    }

    public static void printUsage(ProxiedPlayer player, String command, String args) {
//        player.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/" + command + " " + ChatColor.WHITE + args);
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&cUsage: &6/" + command + " &f" + args)));
    }
}
