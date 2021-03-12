package org.explorersbay.dungeons.loot;

import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.explorersbay.dungeons.Main;
import org.explorersbay.dungeons.objects.DungeonChest;
import org.explorersbay.dungeons.objects.DungeonInstance;
import org.explorersbay.dungeons.utils.Chat;

import java.util.List;
import java.util.Random;

public class ChestHandler {

    Main main;
    public ChestHandler(Main main) {
        this.main = main;
    }

    public void refillChests(DungeonInstance instance) {
        for (DungeonChest chest : instance.getChests()) {
            Chest chest1 = chest.getChest();
            Inventory inventory = chest1.getBlockInventory();
            LootTable lootTable = instance.getChestLootTable();
            List<LootItem> items = lootTable.getItems();
            int size = items.size();

            for (int a = 0; a<=3; a++) {
                Random random = new Random();
                int select = random.nextInt(size);
                LootItem lootItem = items.get(select);
                ItemStack itemStack = new ItemStack(lootItem.getMaterial());
                itemStack.setAmount(lootItem.getAmount());
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta != null) {
                    itemMeta.setDisplayName(Chat.translate(lootItem.getName()));
                    itemMeta.setLore(lootItem.getLore());
                    itemStack.setItemMeta(itemMeta);
                }
                inventory.addItem(itemStack);
            }
        }
    }

}

