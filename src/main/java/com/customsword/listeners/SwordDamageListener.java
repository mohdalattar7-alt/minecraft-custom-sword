package com.customsword.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.customsword.sword.CustomSword;
import com.customsword.sword.CustomSword.SwordType;

public class SwordDamageListener implements Listener {
    
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        
        Player attacker = (Player) event.getDamager();
        ItemStack weapon = attacker.getInventory().getItemInMainHand();
        
        if (!CustomSword.isCustomSword(weapon)) return;
        
        SwordType type = CustomSword.getSwordType(weapon);
        double damageBonus = type.getDamageBonus();
        
        event.setDamage(event.getDamage() + damageBonus);
        
        applyHitEffect(type, event, attacker);
    }
    
    private void applyHitEffect(SwordType type, EntityDamageByEntityEvent event, Player attacker) {
        switch (type) {
            case INFERNO:
                event.getEntity().setFireTicks(80);
                break;
            case FROSTBITE:
                if (event.getEntity() instanceof Player) {
                    Player victim = (Player) event.getEntity();
                    victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1, false, false));
                }
                break;
            case SHADOW:
                double healAmount = Math.min(event.getDamage() / 3, 5.0);
                attacker.setHealth(Math.min(attacker.getHealth() + healAmount, 20.0));
                break;
            default:
                break;
        }
    }
}