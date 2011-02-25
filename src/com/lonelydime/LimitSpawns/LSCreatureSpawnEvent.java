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
		String[] blockids;
		boolean allowSpawn = true;
		//gets the block at the location of spawn
		Block spawnblock = event.getEntity().getLocation().getWorld().getBlockAt(event.getEntity().getLocation().getBlockX(),
				event.getEntity().getLocation().getBlockY()-1,
				event.getEntity().getLocation().getBlockZ());
		int spawnbid = spawnblock.getTypeId();
		
		blockids = LimitSpawns.nospawnblocks.split(",");
		
		for (int i=0;i<blockids.length;i++) {
			if (Integer.parseInt(blockids[i]) == spawnbid)
				allowSpawn = false;
		}
		
		if (!allowSpawn) {
			event.setCancelled(true);
		}
	}

}