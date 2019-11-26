package de.hunjy.team.commands;

import de.hunjy.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 25.11.2019
    @auther: hunjy
    @date: 25.11.2019
    @time: 04:51
    @projekt: CoreSystem
*/
public class time implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        if (Core.getInstance().getPlayerManager(player).isTeam()) {
            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("day")) {
                    player.getLocation().getWorld().setTime(120);
                    player.sendMessage(Core.prefix + "§7Es ist jetzt §aTag§7.");
                } else if (args[0].equalsIgnoreCase("night")) {
                    player.getLocation().getWorld().setTime(165000);
                    player.sendMessage(Core.prefix + "§7Es ist jetzt §cNacht§7.");
                } else {
                    player.sendMessage(Core.prefix + "§7/settime <day | night>");
                }
            }else {
                player.sendMessage(Core.prefix + "§7/settime <day | night>");
            }
            return true;
        }else {
            player.sendMessage(Core.prefix + "§cDazu hast du keine Rechte!");
            return true;
        }
    }

}
