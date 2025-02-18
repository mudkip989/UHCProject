package us.mudkip989.plugins.uHCThing;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import us.mudkip989.plugins.uHCThing.Commands.*;

public final class UHCThing extends JavaPlugin {

    @Override
    public void onEnable() {


        this.saveDefaultConfig();


        this.getCommand("uhc").setExecutor(new CommandListener());

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
