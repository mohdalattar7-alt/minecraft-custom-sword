package com.customsword.sword;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import com.customsword.CustomSwordPlugin;
import java.util.Arrays;
import java.util.List;

public class CustomSword {
    
    public enum SwordType {
        INFERNO("Inferno Blade", "Fire sword with burning effects", 8.0),
        FROSTBITE("Frostbite", "Ice sword with freeze effects", 7.0),
        THUNDER("Thunderstrike", "Lightning sword with shock effects", 9.0),
        SHADOW("Shadow's Edge", "Dark sword with life steal", 7.5),
        CELESTIAL("Celestial", "Holy sword with healing and smite", 8.5),
        VOID_RIPPER("Void Ripper", "Tears reality apart - summons void rifts", 10.5),
        CHRONO_BLADE("Chrono Blade", "Bends time - slow enemies, haste for you", 8.0),
        GRAVITY_WELL("Gravity Well", "Warps space - pulls enemies & their loot", 7.5),
        SOUL_REAVER("Soul Reaver", "Consumes souls - gain exp & strength", 9.0),
        NECRO_SCYTHE("Necro Scythe", "Commands the dead - summons zombie allies", 6.5),
        PRISMA_EDGE("Prisma Edge", "Rainbow chaos - random status effects", 7.0),
        PHOENIX_WING("Phoenix Wing", "Rises from ashes - auto revive & burning", 8.5),
        QUANTUM_BLADE("Quantum Blade", "Exists in superposition - teleport strikes", 9.5),
        ECHO_SWORD("Echo Sword", "Creates clones - damage multiplies", 8.0),
        ENTROPY_SABER("Entropy Saber", "Decays all - slow poison spread", 7.5);
        
        private final String displayName;
        private final String description;
        private final double damageBonus;
        
        SwordType(String displayName, String description, double damageBonus) {
            this.displayName = displayName;
            this.description = description;
            this.damageBonus = damageBonus;
        }
        
        public String getDisplayName() { return displayName; }
        public String getDescription() { return description; }
        public double getDamageBonus() { return damageBonus; }
    }
    
    public static ItemStack createCustomSword(SwordType type) {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();
        
        if (meta != null) {
            meta.setDisplayName("§e⚔ " + type.getDisplayName() + " ⚔");
            
            List<String> lore = Arrays.asList(
                "§7" + type.getDescription(),
                "§c",
                "§6Damage Bonus: +" + type.getDamageBonus(),
                "§6Special Ability: §aYES §c[ULTRA UNIQUE]",
                "§c",
                "§7Left-click: Normal Attack",
                "§7Right-click: LEGENDARY ABILITY (RB)",
                "§6(10 second cooldown)",
                "§c",
                "§6⭐ LEGENDARY TIER ⭐"
            );
            meta.setLore(lore);
            
            meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
            meta.addEnchant(Enchantment.DURABILITY, 3, true);
            meta.addEnchant(Enchantment.KNOCKBACK, 2, true);
            
            NamespacedKey key = new NamespacedKey(getPlugin(), "sword_type");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, type.name());
            
            sword.setItemMeta(meta);
        }
        
        return sword;
    }
    
    public static SwordType getSwordType(ItemStack item) {
        if (item == null || item.getItemMeta() == null) return null;
        
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(getPlugin(), "sword_type");
        String typeStr = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        
        try {
            return SwordType.valueOf(typeStr);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static boolean isCustomSword(ItemStack item) {
        return getSwordType(item) != null;
    }
    
    private static CustomSwordPlugin getPlugin() {
        return (CustomSwordPlugin) org.bukkit.Bukkit.getPluginManager().getPlugin("CustomSword");
    }
}