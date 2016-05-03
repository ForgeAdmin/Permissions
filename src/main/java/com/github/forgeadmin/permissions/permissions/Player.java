package com.github.forgeadmin.permissions.permissions;

import java.util.List;
import java.util.UUID;

public class Player {

  private UUID uuid;
  private String displayName;
  private String nextGroup;
  private String prevGroup;
  private List<String> permissions;
  private List<String> disallowedPermissions;

  public Player(UUID uuid) {
    this.uuid = uuid;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getNextGroup() {
    return nextGroup;
  }

  public void setNextGroup(String nextGroup) {
    this.nextGroup = nextGroup;
  }

  public String getPrevGroup() {
    return prevGroup;
  }

  public void setPrevGroup(String prevGroup) {
    this.prevGroup = prevGroup;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public void addPermission(String permission) {
    this.permissions.add(permission);
  }

  public void removePermission(String permission) {
    this.permissions.remove(permission);
  }

  public List<String> getDisallowedPermissions() {
    return disallowedPermissions;
  }

  public void setDisallowedPermissions(List<String> permissions) {
    this.disallowedPermissions = permissions;
  }

  public void addDisallowedPermission(String permission) {
    this.disallowedPermissions.add(permission);
  }

  public void removeDisallowedPermission(String permission) {
    this.disallowedPermissions.remove(permission);
  }
}
