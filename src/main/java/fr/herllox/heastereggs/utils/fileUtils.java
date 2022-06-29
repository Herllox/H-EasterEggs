package fr.herllox.heastereggs.utils;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class fileUtils {


    /**Here I check if the folder exists*/
    public static void createFolder(){
        File folder = new File("plugins/H-EasterEggs/PlayerData");
        if(!folder.exists()){
            folder.mkdir();
        }else System.out.println("Folder exists");
    }

    /**Here I check if the player file exists*/
    public static void fileExist(Player player) throws IOException {
        File file = new File("plugins/H-EasterEggs/PlayerData/"+player.getName()+".yml");
        if(!file.exists()){
            file.createNewFile();
        }
    }

}
