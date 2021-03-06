package net.excentrix.mastercontrol;

import net.excentrix.mastercontrol.commands.*;
import net.excentrix.mastercontrol.eventListeners.staffJoin;
import net.excentrix.mastercontrol.eventListeners.staffTalk;
import net.excentrix.mastercontrol.eventListeners.switchServer;
import net.excentrix.mastercontrol.messagingService.*;
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
    public final static HashMap<ServerInfo, Boolean> networkedServers = new HashMap<>();
    public static ArrayList<String> restrictedServers = new ArrayList<>();
    public static HashMap<String, Boolean> watchingStaffChat = new HashMap<>();
    public static HashMap<String, Boolean> activeStaffChat = new HashMap<>();
    public static HashMap<String, Boolean> DoNotDisturb = new HashMap<>();
    public static HashMap<String, Boolean> socialSpy = new HashMap<>();
    public static HashMap<String, String> replyService = new HashMap<>();
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
        networkedServers.put(ProxyServer.getInstance().getServerInfo("event"), false);


        // Plugin startup logic
        getProxy().getPluginManager().registerListener(this, new messageService());
        getProxy().getPluginManager().registerListener(this, new staffJoin());
        getProxy().getPluginManager().registerListener(this, new staffTalk());
        getProxy().getPluginManager().registerListener(this, new switchServer());

        // Administrative Commands
        getProxy().getPluginManager().registerCommand(this, new staffchat("sc", "mastercontrol.use.staffchat", "staffchat", "staff"));
        getProxy().getPluginManager().registerCommand(this, new grant("grant", "mastercontrol.admin.grant"));
        getProxy().getPluginManager().registerCommand(this, new grants("grants", "mastercontrol.admin.grants"));
        getProxy().getPluginManager().registerCommand(this, new socialspy("socialspy", "mastercontrol.use.adminspying"));
        getProxy().getPluginManager().registerCommand(this, new masterControl());
        getProxy().getPluginManager().registerCommand(this, new lock());
        getProxy().getPluginManager().registerCommand(this, new announce());
        getProxy().getPluginManager().registerCommand(this, new find("find", "mastercontrol.command.find", "lookup"));
        getProxy().getPluginManager().registerCommand(this, new toggleStaffChat("tsm", "mastercontrol.use.staffchat", "hush"));

        //ProxiedPlayer Commands
//        getProxy().getPluginManager().registerCommand(this, new join());
        getProxy().getPluginManager().registerCommand(this, new info("info"));
        getProxy().getPluginManager().registerCommand(this, new message("message", "", "msg", "emsg", "w", "ew", "whisper", "tell", "ewhisper"));
        getProxy().getPluginManager().registerCommand(this, new reply("r", "", "reply", "er", "ereply"));
        getProxy().getPluginManager().registerCommand(this, new togglepm("togglepm", "", "dnd", "donotdisturb", "msgtoggle", "tpm"));
        //getProxy().getPluginManager().registerCommand(this, new onlineStaff("onlinestaff"));

        // Direct Join
        getProxy().getPluginManager().registerCommand(this, new hub("hub"));
        getProxy().getPluginManager().registerCommand(this, new creative("creative", "", "plots"));
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
        DoNotDisturb.clear();
        watchingStaffChat.clear();
        activeStaffChat.clear();
        replyService.clear();
        socialSpy.clear();
    }
}
