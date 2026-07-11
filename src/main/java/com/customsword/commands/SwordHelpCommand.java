package com.customsword.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SwordHelpCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§6╔════════════════════════════════════════════════════════╗");
        sender.sendMessage("§6║  §e⚔ CUSTOM SWORD PLUGIN GUIDE ⚔§6      ║");
        sender.sendMessage("§6╚════════════════════════════════════════════════════════╝");
        sender.sendMessage("");
        sender.sendMessage("§e🔥 COMMANDS §6──────────────────────────────────");
        sender.sendMessage("§e/customsword <type> §7- Get a custom sword");
        sender.sendMessage("§e/swordstats §7- View your sword's stats (hold it)");
        sender.sendMessage("§e/swordhelp §7- Show this help menu");
        sender.sendMessage("");
        sender.sendMessage("§e🎮 HOW TO USE §6────────────────────────────");
        sender.sendMessage("§e1. Get a sword: §a/customsword quantum_blade");
        sender.sendMessage("§e2. Check stats: §a/swordstats");
        sender.sendMessage("§e3. Left-click: §aNormal attack");
        sender.sendMessage("§e4. Right-click: §aUNIQUE ABILITY (10s cooldown)");
        sender.sendMessage("");
        sender.sendMessage("§c⚡ ULTRA LEGENDARY SWORD ABILITIES ⚡§6─────────");
        sender.sendMessage("");
        sender.sendMessage("§4▼ VOID RIPPER §7→ Tears reality - pulls all enemies!");
        sender.sendMessage("§b⏱ CHRONO BLADE §7→ Slows enemies, gives you HASTE!");
        sender.sendMessage("§d◯ GRAVITY WELL §7→ Warps space, pulls everything!");
        sender.sendMessage("§5☠ SOUL REAVER §7→ Consumes souls, gains STRENGTH!");
        sender.sendMessage("§8⚰ NECRO SCYTHE §7→ Summons 5 zombie servants!");
        sender.sendMessage("§6✦ PRISMA EDGE §7→ Random chaos status effects!");
        sender.sendMessage("§c🔥 PHOENIX WING §7→ Auto-heal & fire resistance!");
        sender.sendMessage("§e⚛ QUANTUM BLADE §7→ Teleport strike to nearest foe!");
        sender.sendMessage("§9♪ ECHO SWORD §7→ Triple damage echo strikes!");
        sender.sendMessage("§7∞ ENTROPY SABER §7→ Decay cascade on all enemies!");
        sender.sendMessage("");
        sender.sendMessage("§6═══════════════════════════════════════════════════════");
        sender.sendMessage("§e✓ Each ability has a 10-second cooldown");
        sender.sendMessage("§e✓ Abilities scale with nearby enemies");
        sender.sendMessage("§e✓ Different abilities for unique playstyles!");
        
        return true;
    }
}