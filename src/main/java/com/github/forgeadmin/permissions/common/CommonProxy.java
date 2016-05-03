package com.github.forgeadmin.permissions.common;

import com.github.forgeadmin.permissions.permissions.Group;
import com.github.forgeadmin.permissions.permissions.Permission;
import com.github.forgeadmin.forgeadmincore.api.database.DatabaseHandler;
import com.github.forgeadmin.permissions.permissions.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import com.github.forgeadmin.forgeadmincore.api.permissions.PermissonHandler;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;


public class CommonProxy {

  private HashMap<String, Group> groups;
  private HashMap<String, Player> players = new HashMap<>();

  public void preload(FMLPreInitializationEvent event) {
  }

  public void load(FMLInitializationEvent event) {}

  public void postload(FMLPostInitializationEvent event) {}

  public void serverAboutToStart(FMLServerAboutToStartEvent event) {
    PermissonHandler.addPermissonHandler(new Permission());
    try {
      groups = DatabaseHandler.getDatabase().fromDatabase("permissions_groups", Group.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (groups == null || groups.size() == 0) {
      groups = defaultGroups();
    }

    saveAllGroups();
  }

  private void saveAllGroups() {
    try {
      for (Group group : groups.values()) {
        DatabaseHandler.getDatabase().toDatabase("permissions_groups", group.getId(), group);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private HashMap<String, Group> defaultGroups() {
    HashMap<String, Group> defaultGroups = new HashMap<>();
    Group member = new Group("member");
    member.setDisplayName("Member");
    member.setNextGroup("moderator");
    member.setDefault(true);
    member.setPermissions(new ArrayList<>(Arrays.asList(
        "minecraft.command.help",
        "minecraft.command.seed"
    )));
    defaultGroups.put(member.getId(), member);

    Group moderator = new Group("moderator");
    moderator.setDisplayName("Moderator");
    moderator.setNextGroup("admin");
    moderator.setPrevGroup("member");
    moderator.setPermissions(new ArrayList<>(Arrays.asList(
        "minecraft.command.kick",
        "minecraft.command.ban"
    )));
    defaultGroups.put(moderator.getId(), moderator);

    Group admin = new Group("admin");
    admin.setDisplayName("Admin");
    admin.setPrevGroup("moderator");
    admin.setPermissions(new ArrayList<>(Arrays.asList("*")));
    defaultGroups.put(admin.getId(), admin);

    return defaultGroups;
  }

  public void serverStarted(FMLServerStartedEvent event) {}

  public void serverStopped(FMLServerStoppedEvent event) {
    saveAllGroups();
    saveAllPlayers();

  }

  private void savePlayer(Player player) {
    try {
      DatabaseHandler.getDatabase().toDatabase("permissions_players", player.getUuid().toString(), player);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadPlayer(String uuid) {
    try {
      players.put(uuid, DatabaseHandler.getDatabase().fromDatabase("permissions_players", uuid, Player.class));
    } catch (IOException e) {
      Player player = new Player(UUID.fromString(uuid));
      players.put(uuid, player);
      savePlayer(player);
      e.printStackTrace();
    }
  }

  private void saveAllPlayers() {
    players.values().forEach(this::savePlayer);
  }

  @Mod.EventHandler
  public void loggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
    loadPlayer(event.player.getUniqueID().toString());
  }

  @Mod.EventHandler
  public void loggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
    savePlayer(players.get(event.player.getUniqueID().toString()));
    players.remove(event.player.getUniqueID().toString());
  }
}