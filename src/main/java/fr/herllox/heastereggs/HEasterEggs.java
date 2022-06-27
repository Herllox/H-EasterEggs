package fr.herllox.heastereggs;

import fr.herllox.heastereggs.command.EasterEgg;
import fr.herllox.heastereggs.event.headClick;
import fr.herllox.heastereggs.utils.fileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HEasterEggs extends JavaPlugin {

    private static HEasterEggs instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        fileUtils.createFolder();

        getCommand("easteregg").setExecutor(new EasterEgg());

        Bukkit.getPluginManager().registerEvents(new headClick(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HEasterEggs getInstance() {
        return instance;
    }
}
