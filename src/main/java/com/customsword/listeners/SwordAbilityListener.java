package com.customsword.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.particle.Particle;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import com.customsword.sword.CustomSword;
import com.customsword.sword.CustomSword.SwordType;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SwordAbilityListener implements Listener {
    
    private static final long ABILITY_COOLDOWN = 10000;
    private static final Map<Player, Long> lastAbilityUse = new HashMap<>();
    private static final Random random = new Random();
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        
        if (!CustomSword.isCustomSword(item)) return;
        
        if (isOnCooldown(player)) {
            player.sendMessage("§c⏱ Ability on cooldown!");
            return;
        }
        
        SwordType type = CustomSword.getSwordType(item);
        activateAbility(player, type);
        lastAbilityUse.put(player, System.currentTimeMillis());
        
        event.setCancelled(true);
    }
    
    private void activateAbility(Player player, SwordType type) {
        switch (type) {
            case INFERNO: infernoAbility(player); break;
            case FROSTBITE: frostbiteAbility(player); break;
            case THUNDER: thunderAbility(player); break;
            case SHADOW: shadowAbility(player); break;
            case CELESTIAL: celestialAbility(player); break;
            case VOID_RIPPER: voidRipperAbility(player); break;
            case CHRONO_BLADE: chronoBladeAbility(player); break;
            case GRAVITY_WELL: gravityWellAbility(player); break;
            case SOUL_REAVER: soulReaverAbility(player); break;
            case NECRO_SCYTHE: necroScytheAbility(player); break;
            case PRISMA_EDGE: prismaEdgeAbility(player); break;
            case PHOENIX_WING: phoenixWingAbility(player); break;
            case QUANTUM_BLADE: quantumBladeAbility(player); break;
            case ECHO_SWORD: echoSwordAbility(player); break;
            case ENTROPY_SABER: entropySaberAbility(player); break;
        }
    }
    
    private void infernoAbility(Player player) {
        player.getWorld().spawnParticle(Particle.FLAME, player.getEyeLocation(), 50);
        player.sendMessage("§c🔥 Inferno Burst!");
        for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                target.setFireTicks(100);
                target.damage(5.0, player);
                player.getWorld().spawnParticle(Particle.FLAME, target.getLocation(), 20);
            }
        }
    }
    
    private void frostbiteAbility(Player player) {
        player.getWorld().spawnParticle(Particle.SNOWBALL, player.getEyeLocation(), 50);
        player.sendMessage("§b❄ Frozen Storm!");
        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 2, false, false));
                target.damage(3.0, player);
                player.getWorld().spawnParticle(Particle.SNOWBALL, target.getLocation(), 15);
            }
        }
    }
    
    private void thunderAbility(Player player) {
        player.sendMessage("§e⚡ Lightning Strike!");
        player.getWorld().strikeLightningEffect(player.getEyeLocation());
        for (Entity entity : player.getNearbyEntities(15, 15, 15)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                player.getWorld().strikeLightningEffect(target.getLocation());
                target.damage(8.0, player);
                target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0, false, false));
            }
        }
    }
    
    private void shadowAbility(Player player) {
        player.sendMessage("§8🌙 Life Steal!");
        player.getWorld().spawnParticle(Particle.SMOKE, player.getEyeLocation(), 50);
        double totalDamage = 0;
        for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                double damage = 6.0;
                target.damage(damage, player);
                totalDamage += damage;
                player.getWorld().spawnParticle(Particle.SMOKE, target.getLocation(), 15);
            }
        }
        double heal = Math.min(totalDamage / 2, 10.0);
        player.setHealth(Math.min(player.getHealth() + heal, 20.0));
    }
    
    private void celestialAbility(Player player) {
        player.sendMessage("§b✨ Divine Protection!");
        player.getWorld().spawnParticle(Particle.COMPOSTER, player.getEyeLocation(), 50);
        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
            if (entity instanceof Player) {
                Player ally = (Player) entity;
                ally.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2, false, false));
                ally.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 1, false, false));
                player.getWorld().spawnParticle(Particle.COMPOSTER, ally.getLocation(), 15);
            }
        }
    }
    
    private void voidRipperAbility(Player player) {
        player.sendMessage("§4§l▼▼▼ VOID RIPPER: REALITY TEAR! ▼▼▼");
        Location center = player.getEyeLocation();
        for (int i = 0; i < 3; i++) {
            for (Entity entity : player.getNearbyEntities(15 + (i * 5), 15 + (i * 5), 15 + (i * 5))) {
                if (entity instanceof LivingEntity && entity != player) {
                    LivingEntity target = (LivingEntity) entity;
                    Vector direction = center.toVector().subtract(target.getLocation().toVector()).normalize();
                    target.setVelocity(direction.multiply(2));
                    target.damage(6.0 + i, player);
                    player.getWorld().spawnParticle(Particle.SOUL, target.getLocation(), 30);
                }
            }
        }
        player.getWorld().spawnParticle(Particle.SOUL, center, 100);
    }
    
    private void chronoBladeAbility(Player player) {
        player.sendMessage("§b⏱ CHRONO BLADE: TIME WARP!");
        for (Entity entity : player.getNearbyEntities(14, 14, 14)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 3, false, false));
                target.damage(4.0, player);
                player.getWorld().spawnParticle(Particle.REDSTONE, target.getLocation(), 20);
            }
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 200, 1, false, false));
        player.sendMessage("§b✓ You gained HASTE!");
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 50);
    }
    
    private void gravityWellAbility(Player player) {
        player.sendMessage("§d◯ GRAVITY WELL: SPATIAL DISTORTION!");
        Location center = player.getLocation();
        for (Entity entity : player.getNearbyEntities(20, 20, 20)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                Vector pull = center.toVector().subtract(target.getLocation().toVector()).normalize().multiply(3);
                target.setVelocity(pull);
                target.damage(3.0, player);
            }
            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, entity.getLocation(), 15);
        }
        player.getWorld().spawnParticle(Particle.WITCH, center, 50);
    }
    
    private void soulReaverAbility(Player player) {
        player.sendMessage("§5☠ SOUL REAVER: CONSUME SOULS!");
        int soulsConsumed = 0;
        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                target.damage(7.0, player);
                soulsConsumed++;
                player.giveExp(50);
                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, target.getLocation(), 20);
            }
        }
        if (soulsConsumed > 0) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 160, 1 + (soulsConsumed - 1), false, false));
            player.sendMessage("§5✓ Consumed §c" + soulsConsumed + "§5 souls! STRENGTH: §c" + (soulsConsumed) + "!");
        }
    }
    
    private void necroScytheAbility(Player player) {
        player.sendMessage("§8⚰ NECRO SCYTHE: RAISE THE DEAD!");
        int zombiesSpawned = 0;
        Location playerLoc = player.getLocation();
        for (int i = 0; i < 5; i++) {
            Location spawnLoc = playerLoc.clone();
            spawnLoc.add((random.nextDouble() - 0.5) * 10, 1, (random.nextDouble() - 0.5) * 10);
            Zombie zombie = player.getWorld().spawn(spawnLoc, Zombie.class);
            zombie.setCustomName("§8Undead Servant");
            zombie.setCustomNameVisible(true);
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
            zombiesSpawned++;
        }
        player.sendMessage("§8✓ Summoned §c" + zombiesSpawned + "§8 zombie servants!");
        player.getWorld().spawnParticle(Particle.ASH, playerLoc, 100);
    }
    
    private void prismaEdgeAbility(Player player) {
        player.sendMessage("§6✦ PRISMA EDGE: RAINBOW CHAOS!");
        PotionEffectType[] effects = {PotionEffectType.SPEED, PotionEffectType.STRENGTH, PotionEffectType.BLINDNESS, PotionEffectType.WEAKNESS, PotionEffectType.SLOW, PotionEffectType.NAUSEA};
        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                for (int i = 0; i < 2; i++) {
                    PotionEffectType randomEffect = effects[random.nextInt(effects.length)];
                    target.addPotionEffect(new PotionEffect(randomEffect, 100, random.nextInt(3)));
                }
                target.damage(5.0, player);
                player.getWorld().spawnParticle(Particle.ITEM_CRACK, target.getLocation(), 30);
            }
        }
        player.sendMessage("§6✓ Pure CHAOS unleashed!");
    }
    
    private void phoenixWingAbility(Player player) {
        player.sendMessage("§c🔥 PHOENIX WING: RISE FROM ASHES!");
        double maxHealth = player.getMaxHealth();
        for (Entity entity : player.getNearbyEntities(13, 13, 13)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                target.setFireTicks(150);
                target.damage(8.0, player);
                player.getWorld().spawnParticle(Particle.FLAME, target.getLocation(), 30);
            }
        }
        player.setHealth(Math.min(player.getHealth() + 6, maxHealth));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 0));
        player.sendMessage("§c✓ Regeneration & Fire Protection activated!");
    }
    
    private void quantumBladeAbility(Player player) {
        player.sendMessage("§e⚛ QUANTUM BLADE: SUPERPOSITION STRIKE!");
        Location originalLoc = player.getLocation().clone();
        Entity nearestEnemy = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Entity entity : player.getNearbyEntities(20, 20, 20)) {
            if (entity instanceof LivingEntity && entity != player) {
                double distance = entity.getLocation().distance(player.getLocation());
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearestEnemy = entity;
                }
            }
        }
        if (nearestEnemy != null) {
            Location teleportLoc = nearestEnemy.getLocation().clone().add(0, 1, 0);
            player.teleport(teleportLoc);
            if (nearestEnemy instanceof LivingEntity) {
                ((LivingEntity) nearestEnemy).damage(9.0, player);
            }
            player.getWorld().spawnParticle(Particle.PORTAL, teleportLoc, 50);
            player.getWorld().spawnParticle(Particle.PORTAL, originalLoc, 50);
            player.sendMessage("§e✓ Teleport strike complete!");
        }
    }
    
    private void echoSwordAbility(Player player) {
        player.sendMessage("§9♪ ECHO SWORD: CLONE STRIKE!");
        int echoCount = 0;
        for (Entity entity : player.getNearbyEntities(11, 11, 11)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                for (int i = 0; i < 3; i++) {
                    target.damage(2.5, player);
                    echoCount++;
                }
                player.getWorld().spawnParticle(Particle.END_ROD, target.getLocation(), 20);
            }
        }
        player.sendMessage("§9✓ Delivered §b" + echoCount + "§9 echo strikes!");
    }
    
    private void entropySaberAbility(Player player) {
        player.sendMessage("§7∞ ENTROPY SABER: DECAY CASCADE!");
        for (Entity entity : player.getNearbyEntities(13, 13, 13)) {
            if (entity instanceof LivingEntity && entity != player) {
                LivingEntity target = (LivingEntity) entity;
                target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 240, 1));
                target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 240, 1));
                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                target.damage(4.0, player);
                player.getWorld().spawnParticle(Particle.SCULK_SOUL, target.getLocation(), 25);
            }
        }
        player.sendMessage("§7✓ Entropy cascade initiated!");
    }
    
    private boolean isOnCooldown(Player player) {
        Long lastUse = lastAbilityUse.get(player);
        if (lastUse == null) return false;
        return (System.currentTimeMillis() - lastUse) < ABILITY_COOLDOWN;
    }
}