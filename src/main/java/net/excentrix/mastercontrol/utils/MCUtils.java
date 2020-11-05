package net.excentrix.mastercontrol.utils;

import net.excentrix.mastercontrol.MasterControl;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MCUtils {
    static LuckPerms api = LuckPermsProvider.get();

    public static void scNotif(String sender, String message) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(sender);
        if (sender.equalsIgnoreCase("console")) {
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if (p.hasPermission("mastercontrol.notify")) {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&a&lMasterControl&7: ") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                }
            }
        } else
            for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if (p.hasPermission("mastercontrol.use.staffchat")) {
                    if (MasterControl.watchingStaffChat.get(p.getName())) {
                        if (sender.equalsIgnoreCase("qr0")) {
                            p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l<&5&lSC&f&l> &f[" + findPlayer(player) + "&f] &b&l" + sender + "&7: &f") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                        } else
                            p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l<&5&lSC&f&l> &f[" + findPlayer(player) + "&f] &a" + sender + "&7: &f") + ChatColor.translateAlternateColorCodes('&', String.join(" ", message))));
                    }
                }
            }
        ProxyServer.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&f&l<&5&lSC&f&l> &a" + sender + "&f: " + message));
    }

    public static void informativeMessage(ProxiedPlayer sender, String message) {
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&a&lMC &7» &f" + message)));
    }

    public static void errorMessage(ProxiedPlayer sender, String message) {
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&lMC &7» &c" + message)));
    }

    public static void actionForbidden(ProxiedPlayer player) {
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &7» &cYou are forbidden perform this action.")));
    }

    public static void noPerm(ProxiedPlayer player) {
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &7» &cYou do not have permission for this command!")));
    }

    public static void playerNotFound(ProxiedPlayer player) {
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&c&lClarke &7» &cThere is no player by that name connected the network!")));
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
        String serverName;
        if (ProxyServer.getInstance().getPlayers().contains(player)) {
            ServerInfo foundServer = player.getServer().getInfo();
            serverName = getString(foundServer);
        } else serverName = "&enull";
        return serverName;
    }

    public static String getRank(String player) {
        String rank;
        rank = api.getUserManager().getUser(player).getPrimaryGroup();
        return rank;
    }

    private static String getString(ServerInfo foundServer) {
        String serverName;
        switch (foundServer.getName()) {
            case "hub":
                serverName = "&aHub";
                break;
            case "prison":
                serverName = "&3Prison";
                break;
            case "creative":
                serverName = "&eCreative";
                break;
            case "skyblock":
                serverName = "&bSkyBlock";
                break;
            case "dev-a":
                serverName = "&4Dev-a";
                break;
            case "event":
                serverName = "&6Event";
                break;
            default:
                serverName = foundServer.getName().toLowerCase();
                break;

        }
        return serverName;
    }

    public static String fixServer(ServerInfo serverInfo) {
        return getString(serverInfo);
    }
}
