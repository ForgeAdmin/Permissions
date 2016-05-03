package com.github.forgeadmin.permissions;

import com.github.forgeadmin.permissions.common.CommonProxy;
import com.github.forgeadmin.permissions.permissions.Permission;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = "forgeadminpermissions",
    name = "ForgeAdmin Permissions",
    acceptedMinecraftVersions = "1.8.9",
    canBeDeactivated = true,
    dependencies = "required-after:Forge@[11.15.1.1722,);" +
        "required-after:forgeadmincore@[0.1.0.28,)")
public class Permissions {

  @Mod.Instance("fadminpermissions")
  public static Permissions INSTANCE;

  @Mod.Metadata
  public static ModMetadata meta;

  @SidedProxy(clientSide = "ClientProxy",
      serverSide = "CommonProxy")
  public static CommonProxy proxy;

  public static boolean isServer() {
    return FMLCommonHandler.instance().getEffectiveSide().isServer();
  }

  public static boolean isClient() {
    return FMLCommonHandler.instance().getEffectiveSide().isClient();
  }

  @Mod.EventHandler
  public void preload(FMLPreInitializationEvent event) {
    proxy.preload(event);
  }

  @Mod.EventHandler
  public void load(FMLInitializationEvent event) {
    proxy.load(event);
  }

  @Mod.EventHandler
  public void postload(FMLPostInitializationEvent event) {
    proxy.postload(event);
  }

  @Mod.EventHandler
  public void serverStarted(FMLServerAboutToStartEvent event) {
    proxy.serverAboutToStart(event);
  }

  @Mod.EventHandler
  public void serverStarted(FMLServerStartedEvent event) {
    proxy.serverStarted(event);
  }

  @Mod.EventHandler
  public void serverStarted(FMLServerStoppedEvent event) {
    proxy.serverStopped(event);
  }
}