package de.hunjy.team.commands;

import de.hunjy.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 23:47
    @projekt: CoreSystem
*/
public class vanish implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if (Core.getInstance().getPlayerManager(player).isTeam()) {
            if (Core.getInstance().getInVanish().contains(player)) {
                player.sendMessage(Core.prefix + "§7Vanish: §cAus");
                Core.getInstance().getInVanish().remove(player);
                updateVanish(player);
            } else {
                player.sendMessage(Core.prefix + "§7Vanish: §aAn");
                Core.getInstance().getInVanish().add(player);
                updateVanish(player);
            }
        } else {
            player.sendMessage(Core.prefix + "§cDazu hast du keine Rechte!");
            return true;
        }
        return false;
    }

    private void updateVanish(Player player) {
        if(Core.getInstance().getInVanish().contains(player)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (!(Core.getInstance().getInVanish().contains(all))) {
                    if(all != player)
                        all.hidePlayer(player);
                }
            }
            for(Player inVanish : Core.getInstance().getInVanish()) {
                if (Core.getInstance().getInVanish().contains(player)) {
                    player.showPlayer(inVanish);
                }
            }
        }else {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.showPlayer(player);
            }
            for(Player inVanish : Core.getInstance().getInVanish()) {
                if (!(Core.getInstance().getInVanish().contains(player))) {
                    if(inVanish != player)
                     player.hidePlayer(inVanish);
                }
            }
        }
    }

}
