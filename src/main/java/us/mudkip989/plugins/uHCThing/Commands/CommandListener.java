package us.mudkip989.plugins.uHCThing.Commands;

import it.unimi.dsi.fastutil.*;
import net.kyori.adventure.pointer.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.*;
import us.mudkip989.plugins.uHCThing.*;

import java.util.*;
import java.util.Arrays;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        switch(strings[0]){
            case "start" -> {
                Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();


                int teamCount = sb.getTeams().size();

                List<List<Integer>> ChunkCords = new ArrayList<>();



                //For each team
                int teami = 0;
                for(Team t: sb.getTeams()){
                    //Load Team Chunk
                    int rad = UHCThing.instance.getConfig().getInt("Radius");

                    int x = ((int) Math.floor(rad * Math.sin((teami / teamCount) * (Math.PI * 2)) / 16));
                    int z = ((int) Math.floor(rad * Math.cos((teami / teamCount) * (Math.PI * 2)) / 16));

                    ChunkCords.add(Arrays.asList(x,z));
                    Bukkit.getWorld("world").loadChunk(x,z);


                    //For each player
                    List<Player> members = new ArrayList<>();

                    for(String p: t.getEntries()){
                        Player p2 = Bukkit.getPlayerExact(p);
                        if(p2 != null){
                            members.add(p2);
                        }

                    }
                    int memberCount = members.size();

                    int memberi = 0;
                    for(Player p: members){

                        //create box (Another Season)
                        //teleport player
                        int Spawnx = UHCThing.instance.getConfig().getInt("Spawn.x");
                        int Spawnz = UHCThing.instance.getConfig().getInt("Spawn.z");
                        p.teleport(new Location(Bukkit.getWorld("world"), Spawnx + rad * Math.sin((teami / teamCount) * (Math.PI * 2)), 255, Spawnz + rad * Math.cos((teami / teamCount) * (Math.PI * 2))));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 30, 255, true, false));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 30, 1, true, false));
                        memberi++;
                    }

                    teami++;
                }
                UHCThing.hasStarted = true;
                UHCThing.borderStage = 0;
                UHCThing.ticksUntilBorderShrink = 3600;
                UHCThing.gameWon = false;
                UHCThing.PVP = false;

            }


        }


        return false;

    }
}
