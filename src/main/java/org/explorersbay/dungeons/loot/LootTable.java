package org.explorersbay.dungeons.loot;

import lombok.Getter;
import lombok.Setter;
import org.explorersbay.dungeons.Main;

import java.util.ArrayList;
import java.util.List;

public class LootTable {

    /*
    Loot tables store all the items belonging to a specific loot table.
    They also store what the loot table is being used for, whether it's
    for mob drops or items to be found inside of chests.
     */

    public enum Type {
        MOB, CHEST
    }

    @Getter @Setter private Type type;
    @Getter @Setter private List<LootItem> items = new ArrayList<LootItem>();
    @Getter @Setter private Main.Difficulty difficulty;
    @Getter @Setter private String name;

    public LootTable(Main.Difficulty difficulty, Type type) {
        this.difficulty = difficulty;
    }

    public void addLootItem(LootItem item) {
        items.add(item);
    }

    public void removeLootItem(LootItem item) {
        items.remove(item);
    }

}
