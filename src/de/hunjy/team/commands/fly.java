package de.hunjy.team.commands;

import de.hunjy.Core;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 23:08
    @projekt: CoreSystem
*/
public class fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if(Core.getInstance().getPlayerManager(player).isTeam()) {
            if(!(player.getGameMode() == GameMode.CREATIVE)) {
                if (player.isFlying() && player.getAllowFlight()) {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage(Core.prefix + "§7Flugmodus: §cAus");
                    return true;
                } else {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.sendMessage(Core.prefix + "§7Flugmodus: §aAn");
                    return true;
                }
            }else {
                player.sendMessage(Core.prefix + "§7Dieser Befehl ist im §cCreativModus §7gesperrt!");
                return true;
            }
        }else {
            player.sendMessage(Core.prefix + "§cDazu hast du keine Rechte!");
            return true;
        }
    }
}
