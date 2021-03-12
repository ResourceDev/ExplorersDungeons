package org.explorersbay.dungeons.loot;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class LootItem {

    /*
    Information about the items which can be dropped by the mobs in the dungeon.
     */

    @Getter @Setter Material material;
    @Getter @Setter List<Enchantment> enchantments = new ArrayList<Enchantment>();
    @Getter @Setter String name;
    @Getter @Setter List<String> lore = new ArrayList<String>();
    @Getter @Setter boolean glowing;
    @Getter @Setter boolean unbreakable;
    @Getter @Setter int amount;

}
