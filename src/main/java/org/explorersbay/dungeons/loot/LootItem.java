package org.explorersbay.dungeons.loot;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.explorersbay.dungeons.utils.Chat;

import java.util.ArrayList;
import java.util.List;

public class LootItem {

    /*
    Information about the items which can be dropped by the mobs in the dungeon.

    Stores the following information:
      -> Material data
      -> Enchantment information
      -> Name
      -> Lore
      -> Glowing Status
      -> Unbreakablity
      -> Stack Size
      -> NBT Data

     This is all that is required to make a fully custom item solution and give items from plugins.
     */

    @Getter @Setter Material material;
    @Getter @Setter List<Enchantment> enchantments = new ArrayList<Enchantment>();
    @Getter @Setter String name;
    @Getter @Setter List<String> lore = new ArrayList<String>();
    @Getter @Setter boolean glowing;
    @Getter @Setter boolean unbreakable;
    @Getter @Setter int amount;

    public LootItem(Material material, String name, List<String> loreTemp, boolean glowing, boolean unbreakable, int amount) {
        this.material = material;
        this.name = name;
        this.glowing = glowing;
        this.unbreakable = unbreakable;
        this.amount = amount;

        for (String a : loreTemp) {
            lore.add(Chat.translate(a));
        }
    }

}
