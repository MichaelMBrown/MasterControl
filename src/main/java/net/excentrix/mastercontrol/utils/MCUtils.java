package net.excentrix.mastercontrol.utils;

import net.excentrix.mastercontrol.MasterControl;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MCUtils {
    public static void scNotif(String sender, String message) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(sender);
        for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if (p.hasPermission("mastercontrol.use.staffchat")) {
                if (MasterControl.scEnabled.get(p.getName())) {
                    if (sender.equalsIgnoreCase("console")) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&a&lMasterControl&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                    } else if (sender.equalsIgnoreCase("qr0")) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&f" + player.getServer().getInfo().getName() + "&7] &b&l" + sender + "&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                    } else
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&f" + player.getServer().getInfo().getName() + "&7] &a" + sender + "&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                }
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
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &cThere is no player by that name connected the network!"));
    }

    public static void printUsage(ProxiedPlayer player, String command, String args) {
//        player.sendMessage(ChatColor.YELLOW + "Usage: " + ChatColor.GOLD + "/" + command + " " + ChatColor.WHITE + args);
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&cUsage: &6/" + command + " &f" + args)));
    }

    public static void excentrixAnnounce(String message) {
        for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&l *** Excentrix Announcement ***\n&7  &7&o") + ChatColor.GRAY + "" + ChatColor.ITALIC + message));
        }
    }

    public static String findPlayer(ProxiedPlayer player) {
        String serverName = "null";
        if (ProxyServer.getInstance().getPlayers().contains(player)) {
            ServerInfo foundServer = player.getServer().getInfo();
            switch (foundServer.getName()) {
                case "hub":
                    serverName = "&aHub";
                    break;
                case "kitpvp":
                    serverName = "&6KitPvP";
                    break;
                case "creative":
                    serverName = "&2Creative";
                    break;
                case "skyblock":
                    serverName = "&bSkyBlock";
                    break;
                case "dev-a":
                    serverName = "&cdev-a";
                    break;
                default:
                    serverName = foundServer.getName();
                    break;

            }
        } else serverName = "&cN/A";
        return serverName;
    }
}
