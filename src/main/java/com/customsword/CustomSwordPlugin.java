package com.customsword;

import org.bukkit.plugin.java.JavaPlugin;
import com.customsword.commands.CustomSwordCommand;
import com.customsword.commands.SwordStatsCommand;
import com.customsword.commands.SwordHelpCommand;
import com.customsword.listeners.SwordDamageListener;
import com.customsword.listeners.SwordAbilityListener;

public class CustomSwordPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("✨ CustomSword Plugin enabled! Prepare for epic battles!");
        getLogger().info("🔥 Loading 15 ULTRA LEGENDARY swords...");
        
        // Register commands
        getCommand("customsword").setExecutor(new CustomSwordCommand());
        getCommand("swordstats").setExecutor(new SwordStatsCommand());
        getCommand("swordhelp").setExecutor(new SwordHelpCommand());
        
        // Register event listeners
        getServer().getPluginManager().registerEvents(new SwordDamageListener(), this);
        getServer().getPluginManager().registerEvents(new SwordAbilityListener(), this);
        
        getLogger().info("✨ All systems ready! Use /customsword to get started!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomSword Plugin disabled.");
    }
}