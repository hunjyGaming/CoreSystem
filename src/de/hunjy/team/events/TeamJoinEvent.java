package de.hunjy.team.events;

import de.hunjy.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 23:25
    @projekt: CoreSystem
*/
public class TeamJoinEvent implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        if(Core.getInstance().getPlayerManager(player).isTeam()) {

            if(!(Core.getInstance().getInVanish().contains(player)))
                Core.getInstance().getInVanish().add(player);

            for(Player all : Bukkit.getOnlinePlayers()) {
                if (!(Core.getInstance().getInVanish().contains(all))) {
                    all.hidePlayer(player);
                }
            }
            for(Player inVanish : Core.getInstance().getInVanish()) {
                if (Core.getInstance().getInVanish().contains(player)) {
                    player.showPlayer(inVanish);
                }
            }

            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(Core.prefix + "§7Flugmodus: §aAn");
        }else {
            for(Player inVanish : Core.getInstance().getInVanish()) {
                if (!(Core.getInstance().getInVanish().contains(player))) {
                    player.hidePlayer(inVanish);
                }
            }
        }
    }

}
