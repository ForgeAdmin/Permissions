package com.github.fadmin.fadminpermissions.common;

import com.github.fadmin.fadminpermissions.FAdminPermissions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FALog {

  private static final Logger logger = LogManager.getLogger(FAdminPermissions.class);

  public static Logger getLogger() {
    return logger;
  }
}
