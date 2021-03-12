package org.explorersbay.dungeons;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.explorersbay.dungeons.loot.ChestHandler;
import org.explorersbay.dungeons.loot.LootTable;
import org.explorersbay.dungeons.objects.DungeonInstance;
import org.explorersbay.dungeons.objects.DungeonMob;
import org.explorersbay.dungeons.protection.DungeonProtectEvents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    This is the lists used for command auto filling for the plugin.
     */
    @Getter @Setter List<String> availableLootTables = new ArrayList<String>();

    /*
    Actual plugin data being stored, relevant for the use of the plugin.
     */
    @Getter @Setter public List<LootTable> lootTables = new ArrayList<LootTable>();
    @Getter @Setter public List<DungeonMob> dungeonMobs = new ArrayList<DungeonMob>();
    @Getter @Setter public List<DungeonInstance> instances = new ArrayList<DungeonInstance>();
    @Getter @Setter ChestHandler chestHandler;

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
        generateConfigs();

        //Generating the loot tables based on the configuration for them.
        Set<String> keys = configActual.getKeys(false);
        for (String s : keys) {
            Difficulty difficulty = Difficulty.valueOf(configActual.getString(s + ".difficulty").toUpperCase());
            LootTable.Type type = LootTable.Type.valueOf(configActual.getString(s + ".type").toUpperCase());

            if (type != null && difficulty != null) {
                LootTable lootTable = new LootTable(difficulty, type);
                lootTable.setName(s);
                lootTables.add(lootTable);
            }
        }

        //Registering the protection events for dungeons.
        getServer().getPluginManager().registerEvents(new DungeonProtectEvents(this), this);

        //Registering the chest handler, used to refill dungeon chests.
        chestHandler = new ChestHandler(this);
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

    //Config Generation Logic

    /*

      -> Generates config.yml
      -> Generates data.yml
      -> Generates droptables.yml
      -> Generates dungeons.yml
      -> Generates mobs.yml
      -> Not responsible for playerdata generation.

      Logic:
      Checks if the File exists, does not check file contents.
      If file does not exist, it generates a new file with the default contents.
      This system never overrides other files/data, making it 100% redundant.

     */

    public void generateConfigs() {
        configFile = new File(getDataFolder(), "config.yml");
        dataFile = new File(getDataFolder(), "data.yml");
        droptablesFile = new File(getDataFolder(), "droptables.yml");
        dungeonsFile = new File(getDataFolder(), "dungeons.yml");
        mobsFile = new File(getDataFolder(), "mobs.yml");

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        if (!droptablesFile.exists()) {
            droptablesFile.getParentFile().mkdirs();
            saveResource("droptables.yml", false);
        }

        if (!dungeonsFile.exists()) {
            dungeonsFile.getParentFile().mkdirs();
            saveResource("dungeons.yml", false);
        }

        if (!mobsFile.exists()) {
            mobsFile.getParentFile().mkdirs();
            saveResource("mobs.yml", false);
        }

        configActual = new YamlConfiguration();
        dataActual = new YamlConfiguration();
        droptablesActual = new YamlConfiguration();
        dungeonsActual = new YamlConfiguration();
        mobsActual = new YamlConfiguration();
        try {
            configActual.load(configFile);
            dataActual.load(dataFile);
            droptablesActual.load(droptablesFile);
            dungeonsActual.load(dungeonsFile);
            mobsActual.load(mobsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
     }

}
