package org.explorersbay.dungeons.loot;

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

    private Type type;
    private List<LootItem> items = new ArrayList<LootItem>();
    private Main.Difficulty difficulty;

    public LootTable(Main.Difficulty difficulty, Type type) {
        this.difficulty = difficulty;
    }

}
