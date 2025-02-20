package us.mudkip989.plugins.uHCThing;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import us.mudkip989.plugins.uHCThing.Commands.*;
import us.mudkip989.plugins.uHCThing.event.*;

public final class UHCThing extends JavaPlugin {

    public static UHCThing instance;

    public static Boolean hasStarted = false;

    public static Boolean PVP = false;

    @Override
    public void onEnable() {

        instance = this;

        this.saveDefaultConfig();

        hasStarted = this.getConfig().getBoolean("Started");

        this.getCommand("uhc").setExecutor(new CommandListener());

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
