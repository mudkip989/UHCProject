package us.mudkip989.plugins.uHCThing.Commands;

import it.unimi.dsi.fastutil.*;
import net.kyori.adventure.pointer.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
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
                Scoreboard sb =  Bukkit.getScoreboardManager().getMainScoreboard();


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

                        //create box
                        //teleport player
                        memberi++;
                    }

                    teami++;
                }






            }


        }


        return false;

    }
}
