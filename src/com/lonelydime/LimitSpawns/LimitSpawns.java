package com.lonelydime.LimitSpawns;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LimitSpawns extends JavaPlugin{
	private final LSCreatureSpawnEvent spawnListener = new LSCreatureSpawnEvent(this);
	
	private final Logger log = Logger.getLogger("Minecraft");
	public static String nospawnblocks; 

	public void onDisable() {
		log.info("[LimitSpawns] Disabled");
	}

	public void onEnable() {
		File yml = new File(getDataFolder()+"/config.yml");
        
        if (!yml.exists()) {
        	new File(getDataFolder().toString()).mkdir();
    	    try {
    	    	yml.createNewFile();
    	    }
    	    catch (IOException ex) {
    	    	System.out.println("cannot create file "+yml.getPath());
    	    }
        }	
        
		nospawnblocks = getConfiguration().getString("no-spawn-blocks", "17,18");
		
        //Create the pluginmanage pm.
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.CREATURE_SPAWN, spawnListener, Priority.Normal, this);
        
        //Get the infomation from the plugin.yml file.
        PluginDescriptionFile pdfFile = this.getDescription();
        //Print that the plugin has been enabled!
        log.info("[LimitSpawns] version " + pdfFile.getVersion() + " by lonelydime is enabled!");
	}
}
