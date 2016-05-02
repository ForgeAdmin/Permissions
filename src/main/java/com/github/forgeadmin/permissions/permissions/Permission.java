package com.github.forgeadmin.permissions.permissions;

import com.github.forgeadmin.forgeadmincore.api.permissions.IPermission;
import com.github.forgeadmin.forgeadmincore.api.permissions.Permissible;
import net.minecraft.entity.player.EntityPlayer;

import java.util.UUID;

public class Permission implements IPermission {
  @Override
  public String handlerId() {
    return "forgeadmin-permissions";
  }

  @Override
  public boolean hasPermission(UUID player, String permission) {
    return false;
  }

  @Override
  public boolean hasPermission(UUID player, Permissible permissable) {
    return false;
  }

  @Override
  public boolean hasPermission(EntityPlayer entityPlayer, String permission) {
    return false;
  }

  @Override
  public boolean hasPermission(EntityPlayer entityPlayer, Permissible permissable) {
    return false;
  }
}
