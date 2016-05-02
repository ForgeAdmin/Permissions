package com.github.forgeadmin.permissions.permissions;

import java.util.List;

public class Group {

  private String id;
  private String displayName = "DefaultName";
  private String nextGroup;
  private String prevGroup;
  private boolean isDefault = false;
  private List<String> permissions;
  private List<String> disallowedPermissions;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean aDefault) {
    isDefault = aDefault;
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
