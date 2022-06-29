package fr.herllox.heastereggs.command;

import fr.herllox.heastereggs.HEasterEggs;
import fr.herllox.heastereggs.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class EasterEgg implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("add")){
                    if(p.hasPermission("heastereggs.add")){
                        if(Utils.getWorld().contains(p.getWorld().getName())){
                            if(p.getTargetBlockExact(5).getType() == Material.PLAYER_HEAD || p.getTargetBlockExact(5).getType() == Material.PLAYER_WALL_HEAD) {
                                int x = p.getTargetBlockExact(5).getX();
                                int y = p.getTargetBlockExact(5).getY();
                                int z = p.getTargetBlockExact(5).getZ();
                                String world = p.getWorld().getName();
                                List<String> list = HEasterEggs.getInstance().getConfig().getStringList("eastereggs");
                                list.add(world + "!" + x + "!" + y + "!" + z);
                                HEasterEggs.getInstance().getConfig().set("eastereggs", list);
                                HEasterEggs.getInstance().saveConfig();
                                p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.added").replace("%prefix%", Utils.getPrefix()).replace("&", "§"));
                            }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.not-head").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
                        }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.world-error").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
                    }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.permission").replace("%prefix%", Utils.getPrefix()).replace("&","§"));

                } else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")) {
                    if(p.hasPermission("heastereggs.remove")) {
                        if (Utils.getWorld().contains(p.getWorld().getName())) {
                            if(p.getTargetBlockExact(5).getType() == Material.PLAYER_HEAD || p.getTargetBlockExact(5).getType() == Material.PLAYER_WALL_HEAD) {
                                int x = p.getTargetBlockExact(5).getX();
                                int y = p.getTargetBlockExact(5).getY();
                                int z = p.getTargetBlockExact(5).getZ();
                                String world = p.getWorld().getName();

                                String result = world + "!" + x + "!" + y + "!" + z;
                                List<String> list = HEasterEggs.getInstance().getConfig().getStringList("eastereggs");

                                if (list.contains(result)) {
                                    list.remove(result);
                                    HEasterEggs.getInstance().getConfig().set("eastereggs", list);
                                    HEasterEggs.getInstance().saveConfig();
                                    p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.deleted").replace("%prefix%", Utils.getPrefix()).replace("&", "§"));
                                }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.not-existing").replace("%prefix%", Utils.getPrefix()).replace("&", "§"));
                            }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.not-head").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
                        }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.world-error").replace("%prefix%", Utils.getPrefix()).replace("&", "§"));
                    }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.permission").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
                }else if(args[0].equalsIgnoreCase("amount")){
                    if(p.hasPermission("heastereggs.amount")){
                        p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.amount").replace("%prefix%", Utils.getPrefix()).replace("%amount%", Utils.getMaxAmount().toString()).replace("&", "§"));
                    }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.permission").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
                }else if(args[0].equalsIgnoreCase("help")){
                    if(p.hasPermission("heastereggs.help")){
                        List<String> list = HEasterEggs.getInstance().getConfig().getStringList("messages.help");
                        for(int i = 0; i < list.size(); i++){
                            p.sendMessage(list.get(i).replace("&","§"));
                        }
                    }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.permission").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
                }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.utilisation").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
            }else p.sendMessage(HEasterEggs.getInstance().getConfig().getString("messages.utilisation").replace("%prefix%", Utils.getPrefix()).replace("&","§"));
        }else System.out.println(HEasterEggs.getInstance().getConfig().getString("messages.console"));

        return false;
    }
}
