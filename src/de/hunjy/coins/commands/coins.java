package de.hunjy.coins.commands;

import de.hunjy.Core;
import de.hunjy.coins.events.PlayerCoinsChangeEvent;
import de.hunjy.utils.PrefixBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
    Create by hunjy on 24.06.2019
    @auther: hunjy
    @date: 24.06.2019
    @time: 01:02
    @projekt: CoinAPI
*/
public class coins implements CommandExecutor {

    String prefix = new PrefixBuilder("§6§lCoins").build();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;
        if(args.length == 0) {
            player.sendMessage(prefix + "Du hast §e" + Core.getInstance().getCoinsSystem().getCoins(player) + " Coins§7!"); return true;
        }
        if(args.length == 1 && player.hasPermission("command.coins.seeother") && !(args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help"))) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage( prefix + "§cDer Spieler ist nicht Online!");
                return false;
            } else {
                player.sendMessage(prefix + "§a"+ target.getDisplayName()  +" hat §e" + Core.instance.getCoinsSystem().getCoins(target) + " Coins§7!");
                return true;
            }
        }
        if(player.hasPermission("command.coins.admin")) {
            if(args.length >= 1) {

                int coins = 0;
                Player target = null;
                if(args.length == 3) {
                    target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        player.sendMessage(prefix + "§cDer Spieler ist nicht Online!");
                        return false;
                    }
                    try {
                        coins = Integer.valueOf(args[2]);
                    } catch (IllegalArgumentException e) {
                        player.sendMessage(prefix + "§cBitte gebe einen gültigen Zahlenwert ein!");
                        return false;
                    }
                }
                switch (args[0]) {
                    case "help": case "?":
                        player.sendMessage(prefix + "/coins add «Spieler» «Coins»");
                        player.sendMessage(prefix + "/coins remove «Spieler» «Coins»");
                        player.sendMessage(prefix + "/coins set «Spieler» «Coins»");
                        break;
                    case "add": case "+":
                        if(args.length == 3) {
                            Core.instance.getCoinsSystem().addCoins(target, coins);
                            Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(player, target, coins));
                            player.sendMessage(prefix + "Du hast §c" + target.getDisplayName() + " §e" + coins + " Coins §7hinzugefügt");
                        }else {
                            player.sendMessage(prefix + "/coins add «Spieler» «Coins»");
                        }
                        break;
                    case "remove": case "-":
                        if(args.length == 3) {
                            Core.instance.getCoinsSystem().removeCoins(target, coins);
                            Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(player, target, coins));
                            player.sendMessage(prefix + "Du hast §c" + target.getDisplayName() + " §e" + coins + " Coins §7weggenommen");
                        }else {
                            player.sendMessage(prefix + "/coins remove «Spieler» «Coins»");
                        }
                        break;
                    case "set": case "=":
                        if(args.length == 3) {
                            Core.instance.getCoinsSystem().setCoins(target, coins);
                            Bukkit.getPluginManager().callEvent(new PlayerCoinsChangeEvent(player, target, coins));
                            player.sendMessage(prefix + "Du hast den Kontostand von  §c" + target.getDisplayName() + " §7auf §e" + coins + " Coins §7gesetzt");
                        }else {
                            player.sendMessage(prefix + "/coins set «Spieler» «Coins»");
                        }
                        break;
                    default:
                        player.sendMessage(prefix + "/coins «command»");
                        break;
                }
            }
        }else{
            player.sendMessage(prefix + "§cDazu hast du keine Rechte!");
        }
        return false;
    }
}
