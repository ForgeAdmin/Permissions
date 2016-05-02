package com.github.forgeadmin.permissions.common;

import com.github.forgeadmin.permissions.permissions.Group;
import com.github.forgeadmin.permissions.permissions.Permission;
import com.github.forgeadmin.forgeadmincore.api.database.DatabaseHandler;
import net.minecraftforge.fml.common.event.*;
import com.github.forgeadmin.forgeadmincore.api.permissions.PermissonHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class CommonProxy {

  private HashMap<String, Group> groups;

  public void preload(FMLPreInitializationEvent event) {}

  public void load(FMLInitializationEvent event) {}

  public void postload(FMLPostInitializationEvent event) {}

  public void serverAboutToStart(FMLServerAboutToStartEvent event) {
    PermissonHandler.addPermissonHandler(new Permission());
    try {
      groups = DatabaseHandler.getDatabase().fromDatabase("groups", Group.class);
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
        DatabaseHandler.getDatabase().toDatabase("groups", group.getId(), group);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private HashMap<String, Group> defaultGroups() {
    HashMap<String, Group> defaultGroups = new HashMap<>();
    Group member = new Group();
    member.setId("member");
    member.setDisplayName("Member");
    member.setNextGroup("moderator");
    member.setDefault(true);
    member.setPermissions(new ArrayList<>(Arrays.asList(
        "minecraft.command.help",
        "minecraft.command.seed"
    )));
    defaultGroups.put(member.getId(), member);

    Group moderator = new Group();
    moderator.setId("moderator");
    moderator.setDisplayName("Moderator");
    moderator.setNextGroup("admin");
    moderator.setPrevGroup("member");
    moderator.setPermissions(new ArrayList<>(Arrays.asList(
        "minecraft.command.kick",
        "minecraft.command.ban"
    )));
    defaultGroups.put(moderator.getId(), moderator);

    Group admin = new Group();
    admin.setId("admin");
    admin.setDisplayName("Admin");
    admin.setPrevGroup("moderator");
    admin.setPermissions(new ArrayList<>(Arrays.asList("*")));
    defaultGroups.put(admin.getId(), admin);

    return defaultGroups;
  }

  public void serverStarted(FMLServerStartedEvent event) {}

  public void serverStopped(FMLServerStoppedEvent event) {}
}