package com.customsword.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.customsword.sword.CustomSword;
import com.customsword.sword.CustomSword.SwordType;

public class CustomSwordCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (args.length == 0) {
            player.sendMessage("§6╔════════════════════════════════════════╗");
            player.sendMessage("§6║  §e⚔ CUSTOM SWORD PLUGIN ⚔§6  ║");
            player.sendMessage("§6╚════════════════════════════════════════╝");
            player.sendMessage("§eUsage: /customsword <type>");
            player.sendMessage("§c");
            player.sendMessage("§6═══ ORIGINAL SWORDS ═══");
            player.sendMessage("§7• §einferno §7- Fire sword with burning effects");
            player.sendMessage("§7• §efrostbite §7- Ice sword with freeze effects");
            player.sendMessage("§7• §ethunder §7- Lightning sword with shock effects");
            player.sendMessage("§7• §eshadow §7- Dark sword with life steal");
            player.sendMessage("§7• §ecelestial §7- Holy sword with healing");
            player.sendMessage("§c");
            player.sendMessage("§4═══ ULTRA LEGENDARY SWORDS ═══");
            player.sendMessage("§7• §cvoid_ripper §7- Tears reality apart!");
            player.sendMessage("§7• §bchrono_blade §7- Bends time!");
            player.sendMessage("§7• §dgravity_well §7- Warps space!");
            player.sendMessage("§7• §5soul_reaver §7- Consumes souls!");
            player.sendMessage("§7• §8necro_scythe §7- Commands the dead!");
            player.sendMessage("§7• §6prisma_edge §7- Rainbow chaos!");
            player.sendMessage("§7• §cphoenix_wing §7- Rises from ashes!");
            player.sendMessage("§7• §equantum_blade §7- Teleport strikes!");
            player.sendMessage("§7• §9echo_sword §7- Creates clones!");
            player.sendMessage("§7• §7entropy_saber §7- Decays all!");
            player.sendMessage("§6");
            player.sendMessage("§eExample: §a/customsword quantum_blade");
            return true;
        }
        
        try {
            SwordType type = SwordType.valueOf(args[0].toUpperCase());
            player.getInventory().addItem(CustomSword.createCustomSword(type));
            player.sendMessage("§a✓ You received the §e" + type.getDisplayName() + "§a!");
            player.sendMessage("§7Type §a/swordstats §7to view details!");
            return true;
        } catch (IllegalArgumentException e) {
            player.sendMessage("§c✗ Unknown sword type: " + args[0]);
            player.sendMessage("§7Use /customsword for available types.");
            return false;
        }
    }
}