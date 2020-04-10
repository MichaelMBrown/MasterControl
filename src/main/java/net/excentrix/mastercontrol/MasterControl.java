package net.excentrix.mastercontrol;

import net.excentrix.mastercontrol.commands.*;
import net.excentrix.mastercontrol.eventListeners.staffJoin;
import net.excentrix.mastercontrol.eventListeners.staffTalk;
import net.excentrix.mastercontrol.eventListeners.switchServer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class MasterControl extends Plugin {
    public static ArrayList<String> restrictedServers = new ArrayList<>();
    private static File file;
    private Configuration configuration;
    private Plugin plugin = this;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getProxy().getPluginManager().registerListener(this, new staffTalk());
        getProxy().getPluginManager().registerListener(this, new staffJoin());
        getProxy().getPluginManager().registerListener(this, new switchServer());
        getProxy().getPluginManager().registerCommand(this, new staffchat());
        getProxy().getPluginManager().registerCommand(this, new masterControl());
        getProxy().getPluginManager().registerCommand(this, new join());
        getProxy().getPluginManager().registerCommand(this, new lockdown());
        getProxy().getPluginManager().registerCommand(this, new hub());
        getProxy().getPluginManager().registerCommand(this, new creative());
        getProxy().getPluginManager().registerCommand(this, new kitpvp());
        file = new File(ProxyServer.getInstance().getPluginsFolder() + "/config.yml");
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
            configuration.set("restricted-servers", restrictedServers);
            getProxy().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aSave Complete!"));
        } catch (NullPointerException ignored) {
            getProxy().getLogger().warning(ChatColor.translateAlternateColorCodes('&', "&cCould not save server-states to disk, once the proxy"));
            getProxy().getLogger().warning(ChatColor.translateAlternateColorCodes('&', "&crestarts all servers will be available"));
        }

    }
}
