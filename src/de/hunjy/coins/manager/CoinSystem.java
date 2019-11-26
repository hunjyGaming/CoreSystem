package de.hunjy.coins.manager;

import de.hunjy.Core;
import de.hunjy.coins.events.PlayerCoinsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
    Create by hunjy on 23.06.2019
    @auther: hunjy
    @date: 23.06.2019
    @time: 23:31
    @projekt: Core
*/
public class CoinSystem {

    public boolean playerExist(Player player) {
        try {
            PreparedStatement st = Core.getInstance().getCoinsMySQL().getConnection().prepareStatement("SELECT * FROM Coins WHERE UUID=?");
            st.setString(1, player.getUniqueId().toString());
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void createPlayer(Player player) {
        Core.getInstance().getCoinsMySQL().doUpdate("INSERT INTO Coins(UUID, Coins) VALUES ('"+ player.getUniqueId().toString() +"','1000')");
    }

    public int getCoins(Player player) {
        if(!playerExist(player))
            createPlayer(player);
        int coins = 0;

        ResultSet rs = Core.getInstance().getCoinsMySQL().getResult("SELECT * FROM Coins WHERE UUID='"+ player.getUniqueId().toString() +"'");
        try {
            if(rs.next()) {
                coins = rs.getInt("Coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coins;
    }

    public void setCoins(Player player, int amount) {
        if(!playerExist(player))
            createPlayer(player);
        Core.getInstance().getCoinsMySQL().doUpdate("UPDATE Coins Set Coins='" + amount + "' WHERE UUID='" + player.getUniqueId().toString() + "'");
        Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(player, amount));

    }

    public void addCoins(Player player,  int amount) {
        if(!playerExist(player))
            createPlayer(player);
        int coins = getCoins(player);
        coins += amount;
        setCoins(player, coins);
        Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(player, amount));
    }

    public void removeCoins(Player player,  int amount) {
        if(!playerExist(player))
            createPlayer(player);
        int coins = getCoins(player);
        coins -= amount;
        if(coins >= 0) {
            setCoins(player, coins);
            Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(player, amount));
        }
    }
}
