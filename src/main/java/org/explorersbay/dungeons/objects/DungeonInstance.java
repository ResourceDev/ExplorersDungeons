package org.explorersbay.dungeons.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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

    @Getter @Setter boolean isJoinable;
    @Getter @Setter boolean isActive;
    @Getter @Setter long startTime = System.currentTimeMillis();
    @Getter @Setter long timeRemaining;
    @Getter @Setter String timeRemainingFormat;
    @Getter @Setter Location spawnPoint;
    @Getter @Setter Main.Difficulty difficulty;
    @Getter @Setter List<DungeonPlayer> players = new ArrayList<>();
    @Getter @Setter LootTable mobLootTable;
    @Getter @Setter LootTable chestLootTable;
    @Getter @Setter List<DungeonMob> mobs = new ArrayList<>();
    @Getter @Setter List<DungeonChest> chests = new ArrayList<>();

    public DungeonInstance(boolean isJoinable, Location spawnPoint, Main.Difficulty difficulty, LootTable lootTable, LootTable chestLootTable) {
        isActive = false;
        this.isJoinable = isJoinable;
        this.spawnPoint = spawnPoint;
        this.difficulty = difficulty;
        this.mobLootTable = lootTable;
        this.chestLootTable = chestLootTable;
    }

    public DungeonInstance(Player player, boolean isNew, LootTable lootTable, LootTable chestLootTable, Main.Difficulty difficulty) {
        isJoinable = false;
        isActive = false;
        timeRemainingFormat = "";
        timeRemaining = 0;
        spawnPoint = player.getLocation();
        this.mobLootTable = lootTable;
        this.chestLootTable = chestLootTable;
        this.difficulty = difficulty;
    }

    public void makeUnjoinable() {
        isJoinable = false;
        if (isActive) {
            for (DungeonPlayer player : players) {
                player.teleportToLastLocation();
            }
            deactivateDungeon();
        }
    }

    public void deactivateDungeon() {
        isActive = false;
        players.clear();
    }

    public void closeDungeon() {
        for (DungeonPlayer player : players) {
            player.teleportToLastLocation();
        }
        deactivateDungeon();
    }

    public void addPlayerDungeon(DungeonPlayer player) {
        players.add(player);
    }

    public void playerQuitDungeon(DungeonPlayer player) {
        players.remove(player);
    }

    public void formatTime() {
        String day = "d";
        String hour = "h";
        String minute = "m";
        String second = "s";

        long minutes = timeRemaining/60;
        long seconds = timeRemaining-(minutes*60);

        timeRemainingFormat = "" + minutes + minute + seconds + second;
    }

    public void dungeonStartup(Main main) {
        if (!isActive) {
            timeRemaining = 900;
            startTime = System.currentTimeMillis();
            main.getChestHandler().refillChests(this);
            for (DungeonPlayer player : players) {
                Player player1 = player.getPlayer();
                if (player1 != null) {
                    player1.teleport(spawnPoint);
                }
            }
        }
    }

}
