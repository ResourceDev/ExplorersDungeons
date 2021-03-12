package org.explorersbay.dungeons.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.explorersbay.dungeons.Main;

public class DungeonMob {

    /*
    DungeonMobs store data about what information LivingDungeonMobs should be made with.
    DungeonMobs are not Living! They are permanent objects for whenever the plugin is running.
     */

    @Getter @Setter int health;
    @Getter @Setter String name;
    @Getter @Setter EntityType entityType;
    @Getter @Setter ItemStack headPiece;
    @Getter @Setter ItemStack chestPiece;
    @Getter @Setter ItemStack legsPiece;
    @Getter @Setter ItemStack bootsPiece;
    @Getter @Setter Main.Difficulty difficulty;

    public DungeonMob(FileConfiguration config) {

    }

}
