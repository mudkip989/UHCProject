package us.mudkip989.plugins.uHCThing;

import net.kyori.adventure.text.*;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.*;
import us.mudkip989.plugins.uHCThing.Commands.*;
import us.mudkip989.plugins.uHCThing.event.*;

public final class UHCThing extends JavaPlugin {

    public static UHCThing instance;

    public static Boolean hasStarted = false;

    public static Boolean gameWon = false;

    public static Integer ticksUntilBorderShrink = -1;

    public static Integer borderStage = 0;

    public static Boolean PVP = false;

    @Override
    public void onEnable() {

        instance = this;

        this.saveDefaultConfig();

        hasStarted = this.getConfig().getBoolean("Started");

        this.getCommand("uhc").setExecutor(new CommandListener());

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        new BukkitRunnable() {
            @Override
            public void run() {

            if(ticksUntilBorderShrink == 0){
                int level = instance.getConfig().getInt("WorldBorder."+String.valueOf(borderStage+1));
                if(level != 0){
                    Bukkit.getWorld("world").getWorldBorder().setSize(level*2, instance.getConfig().getInt("WorldBorderTime."+String.valueOf(borderStage+1)));
                    Bukkit.getWorld("world").getWorldBorder().setWarningDistance(500);
                    Bukkit.broadcast(Component.text("The Border Shrinks..."));
                    borderStage++;
                    PVP = true;
                    ticksUntilBorderShrink = 36000;
                }

            }
            ticksUntilBorderShrink--;

//            Bukkit.getWorld("world").getWorldBorder().setSize();


            }
        }.runTaskTimer(this, 1, 1);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
