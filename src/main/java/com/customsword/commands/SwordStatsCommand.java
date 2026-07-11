package com.customsword.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.customsword.sword.CustomSword;
import com.customsword.sword.CustomSword.SwordType;

public class SwordStatsCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }
        
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        
        if (!CustomSword.isCustomSword(item)) {
            player.sendMessage("§c✗ You must hold a custom sword!");
            return true;
        }
        
        SwordType type = CustomSword.getSwordType(item);
        
        player.sendMessage("§6╔═══════════════════════════════════════╗");
        player.sendMessage("§6║  ⚔ SWORD STATS ⚔");
        player.sendMessage("§6╚═══════════════════════════════════════╝");
        player.sendMessage("§eType: §a" + type.getDisplayName());
        player.sendMessage("§eDescription: §a" + type.getDescription());
        player.sendMessage("§eDamage Bonus: §c+" + type.getDamageBonus());
        player.sendMessage("§eAbility: §a" + getAbilityName(type));
        player.sendMessage("§eEnchantments: §aSharpness V, Unbreaking III, Knockback II");
        player.sendMessage("§6");
        player.sendMessage("§eAbility Cooldown: §c10 seconds");
        player.sendMessage("§eAbility Range: §c10-20 blocks (varies by sword)");
        player.sendMessage("§6");
        player.sendMessage("§e✓ Right-click to use your special ability!");
        
        return true;
    }
    
    private String getAbilityName(SwordType type) {
        switch (type) {
            case INFERNO: return "🔥 Inferno Burst - Sets enemies on fire";
            case FROSTBITE: return "❄ Frozen Storm - Freezes enemies";
            case THUNDER: return "⚡ Lightning Strike - Calls down lightning";
            case SHADOW: return "🌙 Life Steal - Restores health";
            case CELESTIAL: return "✨ Divine Protection - Heals nearby allies";
            case VOID_RIPPER: return "▼ Void Ripper - Tears reality, pulls enemies";
            case CHRONO_BLADE: return "⏱ Chrono Blade - Slows enemies, haste for you";
            case GRAVITY_WELL: return "◯ Gravity Well - Pulls all nearby entities";
            case SOUL_REAVER: return "☠ Soul Reaver - Consumes souls, gains strength";
            case NECRO_SCYTHE: return "⚰ Necro Scythe - Summons 5 zombie servants";
            case PRISMA_EDGE: return "✦ Prisma Edge - Random chaos effects";
            case PHOENIX_WING: return "🔥 Phoenix Wing - Auto-heal & fire resistance";
            case QUANTUM_BLADE: return "⚛ Quantum Blade - Teleport strike";
            case ECHO_SWORD: return "♪ Echo Sword - Triple damage clones";
            case ENTROPY_SABER: return "∞ Entropy Saber - Decay cascade";
            default: return "Unknown";
        }
    }
}