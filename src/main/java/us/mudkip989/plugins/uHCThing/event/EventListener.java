package us.mudkip989.plugins.uHCThing.event;

import net.kyori.adventure.text.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.world.*;
import org.bukkit.scoreboard.*;
import us.mudkip989.plugins.uHCThing.*;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        Scoreboard sb =  Bukkit.getScoreboardManager().getMainScoreboard();

        if(UHCThing.hasStarted && sb.getPlayerTeam(Bukkit.getOfflinePlayer(p.getName())).getName().equals(null)){
            p.setGameMode(GameMode.SPECTATOR);
        }else if(!UHCThing.hasStarted){
            //teleport to spawn
            p.teleport(new Location(Bukkit.getWorld("world"), UHCThing.instance.getConfig().getDouble("Spawn.x"), UHCThing.instance.getConfig().getDouble("Spawn.y"), UHCThing.instance.getConfig().getDouble("Spawn.z")));
            //send spawn message
            p.sendMessage(Component.text("Welcome to UHC."));
        }

    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e){

    }


    @EventHandler
    public void onPVP(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player && UHCThing.hasStarted && !UHCThing.PVP) {
            e.setCancelled(true);

        }

    }

}
