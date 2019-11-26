package de.hunjy.scoreboard;

import de.dytanic.cloudnet.bridge.CloudServer;
import de.hunjy.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 22:50
    @projekt: CoreSystem
*/
public class ScoreboardHandler {

    private static int animationCount;
    private static String title = "LostPlaceMC.de";

    private static String mainColor = ChatColor.DARK_RED.toString();
    private static String seccColor = ChatColor.RED.toString();
    private static String fillColor = ChatColor.WHITE.toString();
    private static int maxCount;


    public static void setScore(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective score = board.getObjective(DisplaySlot.SIDEBAR);
        if (score != null)
        {
            score.unregister();
        }
        score = board.registerNewObjective("sg", "sgg");

        score.setDisplayName(" " + getAnimationString(animationCount) + " ");
        score.setDisplaySlot(DisplaySlot.SIDEBAR);
        score.getScore("§7").setScore(12);
        score.getScore("§8│ §7Dein Profil").setScore(11);
        score.getScore("§8 ➥  §7" + player.getName()).setScore(10);
        score.getScore("§5").setScore(9);
        score.getScore("§8│ §7Dein Rang").setScore(8);
        score.getScore("§8 ➥  " + Core.getInstance().getPlayerManager(player).getDisplay().substring(0, 2) + Core.getInstance().getPlayerManager(player).getGroupName()).setScore(7);
        score.getScore("§6").setScore(6);
        score.getScore("§8│ §7Deine Coins").setScore(5);
        score.getScore("§8 ➥  §6" + Core.getInstance().getCoinsSystem().getCoins(player)).setScore(4);
        score.getScore("§4").setScore(3);
        score.getScore("§8│ §7Unser Forum").setScore(2);
        score.getScore("§8 ➥  §eSOON").setScore(1);
        player.setScoreboard(board);
        CloudServer.getInstance().updateNameTags(player);
    }

    public static void startAnimation() {
        animationCount = 0;
        new BukkitRunnable()
        {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(current -> {
                    if(current.getScoreboard() == null)
                        setScore(current);

                    current.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(" " + getAnimationString(animationCount) + " ");
                });

                animationCount++;
                if(animationCount == title.length())
                    animationCount = -40;
            }
        }.runTaskTimerAsynchronously(Core.getInstance(), 0, 1);
    }

    public static String getAnimationString(int currentCount) {
        maxCount = title.length();

        if(currentCount < 0) {
            return mainColor + "§l" + title;
        }

        String end = title.substring((currentCount + 1), maxCount);
        String mainLetter = title.substring(currentCount, (currentCount + 1));
        String seccLetter = "";
        String begin = "";
        if(currentCount > 0) {
            seccLetter = title.substring((currentCount - 1), (currentCount));
        }
        if(currentCount > 1) {
            begin = title.substring(0, (currentCount - 1));
        }
        String s = "";
        s += mainColor + "§l" + begin;
        s += seccColor + "§l" + seccLetter;
        s += fillColor + "§l" + mainLetter;
        s += mainColor + "§l" + end;


        return s;
    }

}
