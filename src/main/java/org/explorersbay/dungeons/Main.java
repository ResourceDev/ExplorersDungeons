package org.explorersbay.dungeons;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.explorersbay.dungeons.loot.LootTable;
import org.explorersbay.dungeons.objects.DungeonInstance;
import org.explorersbay.dungeons.objects.DungeonMob;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    /*
    All the plugin's configuration files.
     */
    @Getter @Setter File configFile;
    @Getter @Setter FileConfiguration configActual;
    @Getter @Setter File dataFile;
    @Getter @Setter FileConfiguration dataActual;
    @Getter @Setter File droptablesFile;
    @Getter @Setter FileConfiguration droptablesActual;
    @Getter @Setter File mobsFile;
    @Getter @Setter FileConfiguration mobsActual;
    @Getter @Setter File dungeonsFile;
    @Getter @Setter FileConfiguration dungeonsActual;

    /*
    Actual plugin data being stored, relevant for the use of the plugin.
     */
    @Getter @Setter public List<LootTable> lootTables = new ArrayList<LootTable>();
    @Getter @Setter public List<DungeonMob> dungeonMobs = new ArrayList<DungeonMob>();
    @Getter @Setter public List<DungeonInstance> instances = new ArrayList<DungeonInstance>();

    /*
    Enum for defining the difficulty of a dungeon, and the difficulty associated with a loot table.
     */
    public enum Difficulty {
        EASY, MEDIUM, HARD;
    }

    //OnEnable Logic

    /*

      -> Fetches loot tables from config.
      -> Fetches mob types from config.
      -> Fetches dungeon types from config.
      -> Fetches levels from config.
      -> Fetches misc information from various configs.
      -> Calculates initial leaderboard, recalculation is 1h.
      -> Fetches player data from data folder.
      -> Creates instance of DungeonPlayer for each player online.
      -> Registers events & listeners.

     */

    public void onEnable() {

    }

    //OnDisable Logic

    /*

      -> Saves individual player data for all DungeonPlayer instances.
      -> Kicks all dungeon players from their dungeons.
      -> Resets the dungeons before shutdown.
      -> Resets dungeon loot for all dungeon containers.

     */

    public void onDisable() {

    }

}
