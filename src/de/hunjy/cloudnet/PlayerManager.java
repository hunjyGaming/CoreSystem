package de.hunjy.cloudnet;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/*
    Create by hunjy on 24.11.2019
    @auther: hunjy
    @date: 24.11.2019
    @time: 22:51
    @projekt: CoreSystem
*/
public class PlayerManager {


    private Player player;

    public PlayerManager(Player player)
    {
        this.player = player;
    }

    public PlayerManager(UUID uuid)
    {
        this.player = Bukkit.getPlayer(uuid);
    }

    public PermissionGroup getPermissionGroup ()
    {
        if (player.isOnline())
        {
            return CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());
        } else
        {
            return CloudAPI.getInstance().getOfflinePlayer(player.getUniqueId()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool());
        }
    }

    public String getGroupName()
    {
        return getPermissionGroup().getName();
    }
    public int getTagId()
    {
        return getPermissionGroup().getTagId();
    }

    public String getDisplay ()
    {
        return getPermissionGroup().getDisplay() + player.getName();
    }

    public String getDisplayWithoutName ()
    {
        return getPermissionGroup().getDisplay();
    }

    public String getPrefix ()
    {
        return getPermissionGroup().getPrefix() + player.getName();
    }

    public String getPrefixWithoutName ()
    {
        return getPermissionGroup().getPrefix();
    }

    public boolean isTeam ()
    {
        if (!getGroupName().equalsIgnoreCase("Helfer"))
            if (!getGroupName().equalsIgnoreCase("SrHelfer"))
                if (!getGroupName().equalsIgnoreCase("Dev"))
                    if (!getGroupName().equalsIgnoreCase("Admin"))
                        return false;
        return true;
    }

    public boolean isOperator () {
        return (getGroupName().equalsIgnoreCase("Admin"));
    }

    public String getNameFromUUID(String uuid) {
        return CloudAPI.getInstance().getOfflinePlayer(UUID.fromString(uuid)).getName();
    }

    public UUID getUUIDfromName(String name) {
        return CloudAPI.getInstance().getPlayerUniqueId(name);
    }

}
