package com.Nicogeta.AssaSheep;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.entity.Player;

public class AssaSheepEntityListener extends EntityListener{
	final AssaSheep plugin;

	public AssaSheepEntityListener(AssaSheep instance) {
		plugin = instance;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		Entity mobThatJustDied = event.getEntity();
		Server serv = event.getEntity().getServer();
		if(mobThatJustDied instanceof Sheep) {
			EntityDamageEvent ldc = mobThatJustDied.getLastDamageCause();
			DamageCause theCause = ldc.getCause();
			if(theCause == DamageCause.ENTITY_ATTACK || theCause == DamageCause.PROJECTILE) {
				EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent)event;
				Entity e = event2.getDamager();
				if (e instanceof Player) {
					Player player = (Player)e;
					int damage = event2.getDamage();
					LivingEntity mob = (LivingEntity)event.getEntity();
					int health = mob.getHealth();
					int life = health - damage;
					if(life <= 0) {
						player.getWorld().strikeLightning(player.getLocation());
						player.getServer().broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + player.getName() + " IS AN ASSASSIN !!!");
					} else {
						serv.broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + " Save the sheep, save the player !");
					}
				} else if (e instanceof Projectile) {
					Projectile proj = (Projectile)e;
					if (proj instanceof Arrow) {
						if (proj.getShooter() instanceof Player) {
							Player player = (Player)proj.getShooter();
							int damage = event2.getDamage();
							LivingEntity mob = (LivingEntity)event.getEntity();
							int health = mob.getHealth();
							int life = health - damage;
							if(life <= 0) {
								player.getWorld().strikeLightning(player.getLocation());
								player.getServer().broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + player.getName() + " IS AN ASSASSIN !!!");
							} else {
								serv.broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + " Save the sheep, save the player !");
							}
						}
					} else if (proj instanceof Egg || proj instanceof Snowball) {
						Player player = (Player)proj.getShooter();
						player.sendMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + "Stop that, bad little boy !");
					}
				}
			}
		}
	}
}
