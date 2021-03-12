package org.explorersbay.dungeons.protection;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.explorersbay.dungeons.Main;
import org.explorersbay.dungeons.objects.DungeonInstance;
import org.explorersbay.dungeons.objects.DungeonPlayer;

import java.util.List;

public class DungeonProtectEvents implements Listener {

    /*
    This class handles various aspects of keeping the dungeons in one piece.

      -> Block breaking protection
      -> Block placement protection
      -> Enderman grief protection
      -> TNT/Creeper explosion protection
      -> Fire spread protection

     More events might be added to this class depending on what issues pop up.
     */

    private Main main;
    public DungeonProtectEvents(Main main) {
        this.main = main;
    }

    /*
    This event protects dungeons against block breaking within the dungeon.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakEvent(BlockBreakEvent e) {
        World world = e.getBlock().getWorld();
        String worldName = world.getName();

        if (worldName.equalsIgnoreCase("DungeonWorld")) {
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

    /*
    This event protects dungeons from block placement within the dungeon.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        World world = e.getBlockPlaced().getWorld();
        String worldName = world.getName();

        if (worldName.equalsIgnoreCase("DungeonWorld")) {
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

    /*
    This event protects the dungeons from explosion block damage.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockExplodeEvent(EntityExplodeEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getWorld();
        String worldName = world.getName();

        if (worldName.equalsIgnoreCase("DungeonWorld")) {
            e.setCancelled(true);
        }
    }

    /*
    This event protects the dungeon from any potentual enderman mob damage.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEndermanGriefEvent(EntityChangeBlockEvent e) {
        Entity entity = e.getEntity();
        World world = entity.getWorld();
        String worldName = world.getName();
        if (worldName.equalsIgnoreCase("DungeonWorld")) {
            e.setCancelled(true);
        }
    }

    /*
    This protects the dungeon from fire spread/block damage from fire.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onFireSpreadEvent(BlockFormEvent e) {
        Block block = e.getBlock();
        if (block.getType() != null) {
            if (block.getType() == Material.FIRE) {
                e.setCancelled(true);
            }
        }
    }

}
