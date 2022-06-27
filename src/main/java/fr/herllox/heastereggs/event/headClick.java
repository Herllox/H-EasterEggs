package fr.herllox.heastereggs.event;

import fr.herllox.heastereggs.HEasterEggs;
import fr.herllox.heastereggs.utils.Utils;
import fr.herllox.heastereggs.utils.fileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class headClick implements Listener {

    @EventHandler
    public void eastereggClick (PlayerInteractEvent e) throws IOException {

        if(e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock().getType() == null)return;
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.PLAYER_HEAD || e.getClickedBlock().getType() == Material.PLAYER_WALL_HEAD){
                Player p = e.getPlayer();
                fileUtils.fileExist(p);
                File file = new File("plugins/H-EasterEggs/PlayerData/"+p.getName()+".yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                Block b = e.getClickedBlock();

                int x = b.getX();
                int y = b.getY();
                int z = b.getZ();
                String world = b.getWorld().getName();
                String result = world + "!" + x + "!" + y + "!" + z;
                List<String> list = new ArrayList<>();

                if(HEasterEggs.getInstance().getConfig().getStringList("eastereggs").contains(result)) {
                    if(config.getStringList("eastereggs") != null || !config.getStringList("eastereggs").isEmpty()){

                        list = config.getStringList("eastereggs");
                        if(e.getHand() == EquipmentSlot.HAND) {
                            if (list.contains(result)) {
                                Utils.sendAlreadyValidated(p);
                                return;
                            }
                            if (!list.contains(result)) {
                                list.add(world + "!" + x + "!" + y + "!" + z);
                                config.set("eastereggs", list);
                                config.save(file);
                                Utils.sendValidation(p);
                                return;
                            }
                        }
                    }else{
                        Utils.sendValidation(p);
                        list.add(world+"!"+x+"!"+y+"!"+z);
                        config.set("eastereggs", list);
                        config.save(file);
                        Utils.sendValidation(p);
                        return;
                    }
                }
            }
        }
    }
}
