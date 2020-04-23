package net.excentrix.mastercontrol;

import net.excentrix.mastercontrol.commands.*;
import net.excentrix.mastercontrol.eventListeners.staffJoin;
import net.excentrix.mastercontrol.eventListeners.staffTalk;
import net.excentrix.mastercontrol.eventListeners.switchServer;
import net.excentrix.mastercontrol.quickCommands.creative;
import net.excentrix.mastercontrol.quickCommands.hub;
import net.excentrix.mastercontrol.quickCommands.prison;
import net.excentrix.mastercontrol.quickCommands.skyblock;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class MasterControl extends Plugin {
    public static ArrayList<String> restrictedServers = new ArrayList<>();
    public static HashMap<String, Boolean> scEnabled = new HashMap<>();
    @SuppressWarnings("CanBeFinal")
    public static HashMap<ServerInfo, Boolean> networkedServers = new HashMap<>();
    private Configuration configuration;
    // --Commented out by Inspection (4/16/2020 2:00 AM):private Plugin plugin = this;

    @Override
    public void onEnable() {
        //Setup

        networkedServers.put(ProxyServer.getInstance().getServerInfo("hub"), true);
        networkedServers.put(ProxyServer.getInstance().getServerInfo("creative"), true);
        networkedServers.put(ProxyServer.getInstance().getServerInfo("skyblock"), true);
        networkedServers.put(ProxyServer.getInstance().getServerInfo("prison"), false);
        networkedServers.put(ProxyServer.getInstance().getServerInfo("dev-a"), false);


        // Plugin startup logic
        getProxy().getPluginManager().registerListener(this, new staffTalk());
        getProxy().getPluginManager().registerListener(this, new staffJoin());
        getProxy().getPluginManager().registerListener(this, new switchServer());

        // Administrative Commands
        getProxy().getPluginManager().registerCommand(this, new staffchat("sc"));
        getProxy().getPluginManager().registerCommand(this, new masterControl());
        getProxy().getPluginManager().registerCommand(this, new lock());
        getProxy().getPluginManager().registerCommand(this, new announce());
        getProxy().getPluginManager().registerCommand(this, new find("find"));
        getProxy().getPluginManager().registerCommand(this, new toggleStaffChat("togglesc", "mastercontrol.use.staffchat", "hush"));

        //Player Commands
//        getProxy().getPluginManager().registerCommand(this, new join());
        getProxy().getPluginManager().registerCommand(this, new info("info"));
//        getProxy().getPluginManager().registerCommand(this, new onlineStaff("onlinestaff"));

        // Direct Join
        getProxy().getPluginManager().registerCommand(this, new hub("hub"));
        getProxy().getPluginManager().registerCommand(this, new creative("creative"));
        getProxy().getPluginManager().registerCommand(this, new prison("prison"));
        getProxy().getPluginManager().registerCommand(this, new skyblock("skyblock"));
        File file = new File(ProxyServer.getInstance().getPluginsFolder() + "/config.yml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            configuration.set("disabled-servers", "");
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        restrictedServers = (ArrayList<String>) configuration.getStringList("restricted-servers");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getProxy().getLogger().info(ChatColor.translateAlternateColorCodes('&', "Saving all server-states to disk."));
        try {
            configuration.set("restricted-servers", networkedServers);
            getProxy().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aSave Complete!"));
        } catch (NullPointerException ignored) {
            getProxy().getLogger().warning(ChatColor.translateAlternateColorCodes('&', "&cCould not save server-states to disk, once the proxy"));
            getProxy().getLogger().warning(ChatColor.translateAlternateColorCodes('&', "&crestarts all servers will be available"));
        }

    }
}
