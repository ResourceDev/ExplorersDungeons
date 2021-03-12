package org.explorersbay.dungeons.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DungeonPlayer {

    @Getter @Setter UUID uuid;
    @Getter @Setter String name;
    @Getter @Setter Player player;
    @Getter @Setter DungeonInstance currentDungeon;
    @Getter @Setter int experience;
    @Getter @Setter int level;
    @Getter @Setter int dungeonPoints;
    @Getter @Setter Location lastLocation;

    public DungeonPlayer(Player player, int experience, int level, int dungeonPoints) {
        this.uuid = player.getUniqueId();
        this.player = player;
        this.name = player.getName();
        this.experience = experience;
        this.level = level;
        this.dungeonPoints = dungeonPoints;
    }

    public DungeonPlayer(UUID uuid, String name, int experience, int level, int dungeonPoints) {
        this.player = null;
        this.uuid = uuid;
        this.name = name;
        this.experience = experience;
        this.level = level;
        this.dungeonPoints = dungeonPoints;
    }

    public void leaveDungeon() {
        this.currentDungeon = null;
    }

}
