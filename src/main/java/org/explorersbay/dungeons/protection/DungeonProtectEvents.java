package org.explorersbay.dungeons.protection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.explorersbay.dungeons.Main;
import org.explorersbay.dungeons.objects.DungeonInstance;
import org.explorersbay.dungeons.objects.DungeonPlayer;

import java.util.List;

public class DungeonProtectEvents implements Listener {

    private Main main;
    public DungeonProtectEvents(Main main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakEvent(BlockBreakEvent e) {
        List<DungeonInstance> instances = main.instances;
        for (DungeonInstance instance : instances) {
            List<DungeonPlayer> players = instance.getPlayers();
            for (DungeonPlayer player : players) {
                if (player.getCurrentDungeon() != null) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        List<DungeonInstance> instances = main.instances;
        for (DungeonInstance instance : instances) {
            List<DungeonPlayer> players = instance.getPlayers();
            for (DungeonPlayer player : players) {
                if (player.getCurrentDungeon() != null) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
