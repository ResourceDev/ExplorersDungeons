package org.explorersbay.dungeons.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.explorersbay.dungeons.Main;
import org.explorersbay.dungeons.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class DungeonInstance {

    /*
    Dungeon Instances are the instances of each individual dungeon.
    This stores information about each dungeon instance, whether it's active
    and all the chests and the likes within the dungeon.
     */

    @Getter @Setter boolean isActive;
    @Getter @Setter long startTime = System.currentTimeMillis();
    @Getter @Setter Location spawnPoint;
    @Getter @Setter Main.Difficulty difficulty;
    @Getter @Setter List<DungeonPlayer> players = new ArrayList<DungeonPlayer>();
    @Getter @Setter LootTable lootTable;
    @Getter @Setter List<DungeonMob> mobs = new ArrayList<DungeonMob>();
    @Getter @Setter List<DungeonChest> chests = new ArrayList<DungeonChest>();

}
