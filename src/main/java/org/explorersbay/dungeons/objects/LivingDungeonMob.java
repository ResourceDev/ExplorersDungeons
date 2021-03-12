package org.explorersbay.dungeons.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.explorersbay.dungeons.utils.Chat;

public class LivingDungeonMob {

    /*
    The intances of LivingDungeonMobs are registered to each dungeon instance.
     */

    @Getter @Setter private DungeonMob dungeonMob;
    @Getter @Setter private boolean isAlive;
    @Getter @Setter private Entity spawnedEntity;

    public LivingDungeonMob(DungeonMob mob, Location spawnLocation) {
        this.isAlive = true;
        this.dungeonMob = mob;
        World world = Bukkit.getWorld("DungeonWorld");
        spawnedEntity = world.spawnEntity(spawnLocation, mob.getEntityType());
        spawnedEntity.setCustomNameVisible(true);
        spawnedEntity.setCustomName(Chat.translate(mob.getName()));
        if (spawnedEntity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) spawnedEntity;
            livingEntity.setHealth(mob.getHealth());
        }

        ItemStack head = mob.getHeadPiece();
        ItemStack chestplate = mob.getChestPiece();
        ItemStack leggings = mob.getLegsPiece();
        ItemStack boots = mob.getBootsPiece();

        if (spawnedEntity instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) spawnedEntity;
            EntityEquipment entityEquipment = skeleton.getEquipment();

            if (entityEquipment != null) {
                if (head != null) {
                    entityEquipment.setHelmet(head);
                }

                if (chestplate != null) {
                    entityEquipment.setChestplate(chestplate);
                }

                if (leggings != null) {
                    entityEquipment.setLeggings(leggings);
                }

                if (boots != null) {
                    entityEquipment.setBoots(boots);
                }
            }

        }

    }

}
