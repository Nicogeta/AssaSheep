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
			if(theCause == DamageCause.ENTITY_ATTACK) {
				serv.broadcastMessage(ChatColor.RED + "INFO: DAMAGE BY ENTITY_ATTACK");
				EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent)event;
				Entity e = event2.getDamager();
				if (e instanceof Player) {
					Player player = (Player)e;
					//				if(!isInArena(player)) {
					serv.broadcastMessage(ChatColor.RED + "INFO: ARENA CHECK ON HIT: OK");
					int damage = event2.getDamage();
					LivingEntity mob = (LivingEntity)event.getEntity();
					int health = mob.getHealth();
					int life = health - damage;
					serv.broadcastMessage(ChatColor.RED + "INITIAL HEATH: " + health);
					serv.broadcastMessage(ChatColor.RED + "DAMAGE: " + damage);
					serv.broadcastMessage(ChatColor.RED + "LIFE: " + life);
					if(life <= 0) {
						player.getWorld().strikeLightning(player.getLocation());
						player.getServer().broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + player.getName() + " IS AN ASSASSIN !!!");
					} else {
						serv.broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + " Save the sheep, save the player !");
						//			}
					}
				} 
			} else if (theCause == DamageCause.PROJECTILE){
				EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent)event;
				Entity e = event2.getDamager();
				int d = event2.getDamage();
				if (e instanceof Projectile) {
					serv.broadcastMessage(ChatColor.RED + "INFO: DAMAGE BY PROJECTILE");
					Projectile proj = (Projectile)e;
					if (proj instanceof Arrow) {
						serv.broadcastMessage(ChatColor.RED + "INFO: DAMAGE BY ARROW");
						if (proj.getShooter() instanceof Player) {
							Player player = (Player)proj.getShooter();
							//				if(!isInArena(player)) {
							serv.broadcastMessage(ChatColor.RED + "INFO: ARENA CHECK ON HIT: OK");
							int damage = event2.getDamage();
							LivingEntity mob = (LivingEntity)event.getEntity();
							int health = mob.getHealth();
							int life = health - damage;
							serv.broadcastMessage(ChatColor.RED + "INITIAL HEATH: " + health);
							serv.broadcastMessage(ChatColor.RED + "DAMAGE EVENT 1: " + damage);
							serv.broadcastMessage(ChatColor.RED + "DAMAGE EVENT 2: " + d);
							serv.broadcastMessage(ChatColor.RED + "LIFE: " + life);
							if(life <= 0) {
								player.getWorld().strikeLightning(player.getLocation());
								player.getServer().broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + player.getName() + " IS AN ASSASSIN !!!");
							} else {
								serv.broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + " Save the sheep, save the player !");
								//				}
							}
						}
					} else if (proj instanceof Egg || proj instanceof Snowball) {
						serv.broadcastMessage(ChatColor.RED + "INFO: DAMAGE BY AN EGG OR A SNOWBALL");
						Player player = (Player)proj.getShooter();
						//	if(!isInArena(player)) {
						serv.broadcastMessage(ChatColor.RED + "INFO: ARENA CHECK ON HIT: OK");
						int damage = event2.getDamage();
						LivingEntity mob = (LivingEntity)event.getEntity();
						int health = mob.getHealth();
						int life = health - damage;
						serv.broadcastMessage(ChatColor.RED + "INITIAL HEATH: " + health);
						serv.broadcastMessage(ChatColor.RED + "DAMAGE EVENT 1: " + damage);
						serv.broadcastMessage(ChatColor.RED + "DAMAGE EVENT 2: " + d);
						serv.broadcastMessage(ChatColor.RED + "LIFE: " + life);
						if(damage >= 1) {
							if(life <= 0) {
								player.getWorld().strikeLightning(player.getLocation());
								player.getServer().broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + player.getName() + " IS AN ASSASSIN !!!");
							} else {
								serv.broadcastMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + " Save the sheep, save the player !");
							}
						} else {
							player.sendMessage(ChatColor.RED + "The Big Sheep: " + ChatColor.GREEN + "Stop that, bad little boy !");
						}
						//	}
					}
				}
			}
		}
	}

	/*public boolean isInArena(Player player) {
		if(AssaSheep.maHandler != null && AssaSheep.maHandler.isPlayerInArena(player)) {
			return true;
		} else {
			return false;
		}
	}*/
}
