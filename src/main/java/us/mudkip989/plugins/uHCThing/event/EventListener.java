package us.mudkip989.plugins.uHCThing.event;

import net.kyori.adventure.text.*;
import net.kyori.adventure.text.format.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.world.*;
import org.bukkit.scoreboard.*;
import us.mudkip989.plugins.uHCThing.*;

import java.util.*;
import java.util.concurrent.atomic.*;

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

    @EventHandler
    public void onDeath(PlayerDeathEvent e){

        e.setCancelled(true);
        Player p = e.getPlayer();
        p.setGameMode(GameMode.SPECTATOR);

        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        long matchTeamcount = sb.getTeams().stream().filter(team -> team.getEntries().stream().filter(pstring -> p.equals(Bukkit.getPlayerExact(pstring))).count() >= 1).count();
        sb.getTeams().stream().filter(team -> team.getEntries().stream().filter(pstring -> p.equals(Bukkit.getPlayerExact(pstring))).count() >= 1).forEach(team -> team.removePlayer(Bukkit.getOfflinePlayer(p.getUniqueId())));
        if(matchTeamcount > 0){
            Bukkit.broadcast(Component.text(p.getName()+ " has died.").color(TextColor.fromHexString("#ffaa00")));

        }

        AtomicInteger remainingTeams = new AtomicInteger();
        List<Team> teamsRemaining = new ArrayList<>();
        sb.getTeams().forEach(team -> {
            if(team.getEntries().stream().filter(pstring -> (Bukkit.getPlayerExact(pstring).getGameMode() == GameMode.SURVIVAL)).count() <= 0){
                remainingTeams.getAndIncrement();
                teamsRemaining.add(team);
            }

        });
        if(remainingTeams.get() <= 1){
            Bukkit.broadcast(Component.text("Game Over. " + teamsRemaining.get(0).getName() + " won."));
            UHCThing.hasStarted = false;
            UHCThing.borderStage = 0;
            UHCThing.ticksUntilBorderShrink = -1;
            UHCThing.gameWon = true;
            UHCThing.PVP = false;

            //Game End here
            //At this point Even If I dont finish the Boundary, it can be handled manually. 
        }


    }

}
