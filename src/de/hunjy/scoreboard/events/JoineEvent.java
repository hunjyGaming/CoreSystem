package de.hunjy.scoreboard.events;

import de.hunjy.Core;
import de.hunjy.coins.events.PlayerCoinsChangeEvent;
import de.hunjy.scoreboard.ScoreboardHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 22:54
    @projekt: CoreSystem
*/
public class JoineEvent implements Listener {

    @EventHandler
    private void on(PlayerJoinEvent event) {
        ScoreboardHandler.setScore(event.getPlayer());
    }

    @EventHandler
    public void on(PlayerCoinsChangeEvent event) {
        ScoreboardHandler.setScore(event.getTarget());
        ScoreboardHandler.setScore(event.getPlayer()
        );
    }

}
