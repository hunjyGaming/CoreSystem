package de.hunjy.coins.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/*
    Create by hunjy on 24.06.2019
    @auther: hunjy
    @date: 24.06.2019
    @time: 01:27
    @projekt: NoXTeXAPI
*/
public class PlayerCoinsChangeEvent extends Event implements Cancellable{
    public static HandlerList handlers = new HandlerList();
    public boolean cancelled = false;

    Player player;
    Player target;
    int coins;

    public PlayerCoinsChangeEvent(Player player, Player target, int coins) {
        this.player = player;
        this.target = target;
        this.coins = coins;
    }
    public PlayerCoinsChangeEvent(Player target, int coins) {
        this.target = target;
        this.coins = coins;
    }
    @Override
    public boolean isCancelled() {
        return cancelled;
    }
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public Player getPlayer() {
        return player;
    }
    public int getCoins() {
        return coins;
    }
    public Player getTarget() {
        return target;
    }
}
