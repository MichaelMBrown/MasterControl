package net.excentrix.mastercontrol.commands;

import de.myzelyam.api.vanish.BungeeVanishAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class onlineStaff extends Command {
    String group;
    LuckPerms api = LuckPermsProvider.get();

    public onlineStaff(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ArrayList<String> rankedOwner = new ArrayList<>();
        ArrayList<String> rankedDeveloper = new ArrayList<>();
        ArrayList<String> rankedAdmin = new ArrayList<>();
        ArrayList<String> rankedSrMod = new ArrayList<>();
        ArrayList<String> rankedMod = new ArrayList<>();
        ArrayList<String> rankedHelper = new ArrayList<>();
        List<UUID> onlineVanished;
        boolean hasOwner = false;
        boolean hasDeveloper = false;
        boolean hasAdmin = false;
        boolean hasSrMod = false;
        boolean hasMod = false;
        boolean hasHelper = false;
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8Processing Request...")));
        for (final ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
            onlineVanished = BungeeVanishAPI.getInvisiblePlayers();
            if (!onlineVanished.contains(proxiedPlayer.getUniqueId())) {
                group = api.getUserManager().getUser(proxiedPlayer.getName()).getPrimaryGroup();
                switch (group) {
                    case "owner":
                        rankedOwner.add(proxiedPlayer.getName());
                        break;
                    case "developer":
                        rankedDeveloper.add(proxiedPlayer.getName());
                        break;
                    case "admin":
                        rankedAdmin.add(proxiedPlayer.getName());
                        break;
                    case "senior-mod":
                        rankedSrMod.add(proxiedPlayer.getName());
                        break;
                    case "mod":
                        rankedMod.add(proxiedPlayer.getName());
                        break;
                    case "helper":
                        rankedHelper.add(proxiedPlayer.getName());
                        break;
                    default:
                        break;
                }
            }
        }
        if (!rankedOwner.isEmpty()) {
            hasOwner = true;
        }
        if (!rankedDeveloper.isEmpty()) {
            hasDeveloper = true;
        }
        if (!rankedAdmin.isEmpty()) {
            hasAdmin = true;
        }
        if (!rankedSrMod.isEmpty()) {
            hasSrMod = true;
        }
        if (!rankedMod.isEmpty()) {
            hasMod = true;
        }
        if (!rankedHelper.isEmpty()) {
            hasHelper = true;
        }
        String ownerOutput = Arrays.toString(rankedOwner.toArray()).replace('[', ' ').replace(']', ' ').trim();
        String developerOutput = Arrays.toString(rankedDeveloper.toArray()).replace('[', ' ').replace(']', ' ').trim();
        String adminOutput = Arrays.toString(rankedAdmin.toArray()).replace('[', ' ').replace(']', ' ').trim();
        String srmodOutput = Arrays.toString(rankedSrMod.toArray()).replace('[', ' ').replace(']', ' ').trim();
        String modOutput = Arrays.toString(rankedMod.toArray()).replace('[', ' ').replace(']', ' ').trim();
        String helperOutput = Arrays.toString(rankedHelper.toArray()).replace('[', ' ').replace(']', ' ').trim();

        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&eOnline Staff:")));
        if (hasOwner)
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&4Owner&f: " + ownerOutput)));
        if (hasDeveloper)
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&b&lDeveloper&f: " + developerOutput)));
        if (hasAdmin)
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&cAdmin&f: " + adminOutput)));
        if (hasSrMod)
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6&oSrMod&f: " + srmodOutput)));
        if (hasMod)
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6Mod&f: " + modOutput)));
        if (hasHelper)
            sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&eHelper&f: " + helperOutput)));
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7Total staff online: &a" + Math.addExact(Math.addExact(Math.addExact(Math.addExact(Math.addExact(rankedHelper.size(), rankedMod.size()), rankedSrMod.size()), rankedAdmin.size()), rankedDeveloper.size()), rankedOwner.size()))));
    }
}
