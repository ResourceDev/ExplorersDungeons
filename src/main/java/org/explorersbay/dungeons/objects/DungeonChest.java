package org.explorersbay.dungeons.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

public class DungeonChest {

    /*
    Dungeon chests store the chest location & block as well as storing
    the dungeon and therefore the loot table that the chests belong to.
     */

    @Getter @Setter
    private Location location;
    @Getter @Setter
    private Chest chest;
    @Getter @Setter
    private DungeonInstance instance;

    public DungeonChest(DungeonInstance instance, Location location) {
        this.instance = instance;
        this.location = location;
        Block block = location.getBlock();
        if (block instanceof Chest) {
            this.chest = (Chest) block;
        }
    }

}
