package com.github.forgeadmin.permissions.common;

import com.github.forgeadmin.permissions.Permissions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PermissonsLog {

  private static final Logger logger = LogManager.getLogger(Permissions.class);

  public static Logger getLogger() {
    return logger;
  }
}
