package de.hunjy;

import de.hunjy.team.admin.gamemode;
import de.hunjy.team.commands.fly;
import de.hunjy.cloudnet.PlayerManager;
import de.hunjy.coins.commands.coins;
import de.hunjy.coins.manager.CoinSystem;
import de.hunjy.scoreboard.ScoreboardHandler;
import de.hunjy.scoreboard.events.JoineEvent;
import de.hunjy.team.commands.time;
import de.hunjy.team.commands.vanish;
import de.hunjy.team.events.TeamJoinEvent;
import de.hunjy.utils.MySQL;
import de.hunjy.utils.PrefixBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 22:36
    @projekt: CoreSystem
*/
public class Core extends JavaPlugin {


    private Collection<Player> inVanish;

    public static String prefix = new PrefixBuilder("§4§lL§c§lP§4§lMC§c§l.de").build();

    public static Core instance;

    private CoinSystem coinSystem;
    private MySQL coinsMySQL;



    @Override
    public void onEnable() {
        instance = this;
        coinsMySQL = new MySQL("Coins", "GlTz63yEktMtPKD9");
// COINS
        coinSystem = new CoinSystem();
        getCommand("coins").setExecutor(new coins());




// TEAM
        inVanish = new ArrayList<>();
        getCommand("fly").setExecutor(new fly());
        getCommand("gamemode").setExecutor(new gamemode());
        getCommand("vanish").setExecutor(new vanish());
        getCommand("settime").setExecutor(new time());
        Bukkit.getPluginManager().registerEvents(new TeamJoinEvent(), this);



//SCOREBOARD
        Bukkit.getPluginManager().registerEvents(new JoineEvent(), this);

        ScoreboardHandler.startAnimation();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Core getInstance() {
        return instance;
    }

    public MySQL getCoinsMySQL() {
        return coinsMySQL;
    }

    public CoinSystem getCoinsSystem() {
        return coinSystem;
    }


    public PlayerManager getPlayerManager(Player player) {
        return new PlayerManager(player);
    }

    public PlayerManager getPlayerManager(UUID uuid) {
        return new PlayerManager(uuid);
    }

    public Collection<Player> getInVanish() {
        return inVanish;
    }
}
