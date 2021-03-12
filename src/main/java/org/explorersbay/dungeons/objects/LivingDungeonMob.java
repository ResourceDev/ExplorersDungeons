package org.explorersbay.dungeons.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;

public class LivingDungeonMob {

    /*
    The intances of LivingDungeonMobs are registered to each dungeon instance.
     */

    @Getter @Setter private DungeonMob dungeonMob;
    @Getter @Setter private boolean isAlive;
    @Getter @Setter private Entity spawnedEntity;

    public LivingDungeonMob(DungeonMob mob) {
        this.isAlive = true;
        this.dungeonMob = mob;
    }

}
