package com.Nicogeta.AssaSheep;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
/*import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import com.garbagemule.MobArena.MobArena;
import com.garbagemule.MobArena.MobArenaHandler;*/

public class AssaSheep extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	private PluginDescriptionFile info;
	private final AssaSheepEntityListener entityListener = new AssaSheepEntityListener(this);
	//public static MobArenaHandler maHandler;

	public void onEnable() {
		info = getDescription();
		PluginManager pm = getServer().getPluginManager();
		//setupMobArenaHandler();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Monitor, this);
		log.info(info.getName() + " ver " + info.getVersion() + " by Nicogeta " + " ENABLED" );
	}

	/*public void setupMobArenaHandler()
	{
		Plugin maPlugin = (MobArena)Bukkit.getServer().getPluginManager().getPlugin("MobArena");

		if (maPlugin == null)
			return;

		maHandler = new MobArenaHandler();
	}*/

	public void onDisable() {
		log.info(info.getName() + " DISABLEd");
	}


}
