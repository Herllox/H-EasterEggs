package fr.herllox.heastereggs.utils;

import fr.herllox.heastereggs.HEasterEggs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    /**Here I get the amount of eastereggs*/
    public static Integer getMaxAmount(){
        List<String> list = HEasterEggs.getInstance().getConfig().getStringList("eastereggs");
        return list.size();
    }

    /**Here I get how much eastereggs the player have find*/
    public static Integer getPlayerAmount(Player p){
        File file = new File("plugins/H-EasterEggs/PlayerData/"+p.getName()+".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        List<String> list = config.getStringList("eastereggs");
        return list.size();

    }

    /**Here I get the prefix for thr messages*/
    public static String getPrefix(){return HEasterEggs.getInstance().getConfig().getString("messages.prefix").replace("&","§");}

    /**Here I get all authorized worlds where you can set a easteregg*/
    public static List<String> getWorld(){
        List<String> result = new ArrayList<>();
        for (String s: HEasterEggs.getInstance().getConfig().getStringList("settings.worlds")){
            result.add(s);
        }
        return result;
    }

    /**Here I get all the gifts and I get a random integer for give a random gift to the player*/
    public void giveGift(Player p){
        List<String> keys = new ArrayList<>();

        for(String key: HEasterEggs.getInstance().getConfig().getConfigurationSection("settings.gifts.").getKeys(false)) {
            keys.add(key);
        }

        Random rdm = new Random();
        int i = rdm.nextInt(keys.size());

        if (HEasterEggs.getInstance().getConfig().getBoolean("settings.gifts." + keys.get(i) + ".command") == true) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), HEasterEggs.getInstance().getConfig().getString("settings.gifts." + keys.get(i) + ".gift"));
        } else {
            String[] item = HEasterEggs.getInstance().getConfig().getString("settings.gifts." + keys.get(i) + ".gift").split(":");
            ItemStack it = new ItemStack(Material.getMaterial(item[0]), Integer.parseInt(item[1]));
            p.getInventory().addItem(it);
        }
    }


    /**Here I prepare the message "validated"*/
    public static void sendValidation(Player p){
        if(HEasterEggs.getInstance().getConfig().getBoolean("settings.title") == true){
            List<String> getTitle = HEasterEggs.getInstance().getConfig().getStringList("messages.validated-title");
            List<String> title = new ArrayList<String>();
            for(String s:getTitle){
                title.add(s.replace("%prefix%", Utils.getPrefix())
                        .replace("&","§")
                        .replace("%max-amount%", getMaxAmount().toString())
                        .replace("%amount%", getPlayerAmount(p).toString()));
            }
            p.sendTitle(title.get(0), title.get(1), 40, 20,20);
        }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.validated")
                .replace("%prefix%", Utils.getPrefix())
                .replace("&","§")
                .replace("%max-amount%", getMaxAmount().toString())
                .replace("%amount%", getPlayerAmount(p).toString()));
    }

    /**Here I prepare the message "already validated"*/
    public static void sendAlreadyValidated(Player p){
        if(HEasterEggs.getInstance().getConfig().getBoolean("settings.title") == true){
            List<String> getTitle = HEasterEggs.getInstance().getConfig().getStringList("messages.already-validated-title");
            List<String> title = new ArrayList<String>();
            for(String s:getTitle){
                title.add(s.replace("%prefix%", Utils.getPrefix()).replace("&","§"));
            }
            p.sendTitle(title.get(0), title.get(1), 40, 20,20);
        }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.already-validated"));
    }

}
