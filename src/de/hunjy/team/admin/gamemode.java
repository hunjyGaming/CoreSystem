package de.hunjy.team.admin;

import de.hunjy.Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 23:27
    @projekt: CoreSystem
*/
public class gamemode implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if(Core.getInstance().getPlayerManager(player).isOperator()) {
            if(args.length == 1) {
                switch (args[0]) {
                    case "0": case "s": case "survival":
                        changeGamemode(player, GameMode.SURVIVAL);
                        break;
                    case "1": case "c": case "creative":
                        changeGamemode(player, GameMode.CREATIVE);
                        break;
                    case "2": case "a": case "adventure":
                        changeGamemode(player, GameMode.ADVENTURE);
                        break;
                    case "3": case "spec": case "spectator":
                        changeGamemode(player, GameMode.SPECTATOR);
                        break;
                }
            }else if(args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if(target != null) {
                    switch (args[0]) {
                        case "0": case "s": case "survival":
                            changeGamemode(target, GameMode.SURVIVAL);
                            player.sendMessage(Core.prefix + "§c" + target.getDisplayName() + " §7ist jetzt im §c" + GameMode.SURVIVAL.toString() + "-Modus§7.");
                            break;
                        case "1": case "c": case "creative":
                            changeGamemode(target, GameMode.CREATIVE);
                            player.sendMessage(Core.prefix + "§c" + target.getDisplayName() + " §7ist jetzt im §c" + GameMode.CREATIVE.toString() + "-Modus§7.");
                            break;
                        case "2": case "a": case "adventure":
                            changeGamemode(target, GameMode.ADVENTURE);
                            player.sendMessage(Core.prefix + "§c" + target.getDisplayName() + " §7ist jetzt im §c" + GameMode.ADVENTURE.toString() + "-Modus§7.");
                            break;
                        case "3": case "spec": case "spectator":
                            changeGamemode(target, GameMode.SPECTATOR);
                            player.sendMessage(Core.prefix + "§c" + target.getDisplayName() + " §7ist jetzt im §c" + GameMode.SPECTATOR.toString() + "-Modus§7.");
                            break;
                    }
                }else {
                    player.sendMessage(Core.prefix + "Der Spieler §c" + args[1] + " §7ist nicht Online!");
                }
            }else {
                sendHelp(s, player);
            }
        }

        return false;
    }


    private void changeGamemode(Player player, GameMode gameMode) {
        player.setGameMode(gameMode);
        player.sendMessage(Core.prefix + "§7Du bist jetzt im §c" + gameMode.toString() + "-Modus§7.");
    }

    private void sendHelp(String s, Player player) {
        player.sendMessage(Core.prefix + "§7/" + s + " §8[§c0-3§8] §8<Spieler>");
    }


}
