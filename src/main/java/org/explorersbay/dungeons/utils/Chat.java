package org.explorersbay.dungeons.utils;

import org.bukkit.ChatColor;

public class Chat {

    /*
    Chat utils are used for applying colour codes to various
    forms of in-game text, this includes scoreboard text as well
    as the messages sent to players in chat.
     */

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
