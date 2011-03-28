package com.lonelydime.LimitSpawns;

import org.bukkit.block.Block;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityListener;

public class LSCreatureSpawnEvent extends EntityListener{
	public static LimitSpawns plugin;
	
	public LSCreatureSpawnEvent(LimitSpawns instance) {
        plugin = instance;
    }
	
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		boolean allowSpawn = true;
		//gets the block at the location of spawn
		Block spawnblock = event.getEntity().getLocation().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(),
				event.getEntity().getLocation().getBlockY()-1,
				event.getEntity().getLocation().getBlockZ());
		int spawnbid = spawnblock.getTypeId();
		
		String curWorld = event.getEntity().getLocation().getWorld().getName();

		if (LimitSpawns.worldConfigs.get(curWorld).indexOf(spawnbid) != -1)
			allowSpawn = false;
		
		if (!allowSpawn) {
			event.setCancelled(true);
		}
	}

}