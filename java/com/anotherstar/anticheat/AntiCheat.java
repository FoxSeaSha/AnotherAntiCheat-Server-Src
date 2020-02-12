package com.anotherstar.anticheat;

import com.anotherstar.anticheat.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid="anotheranticheat", name="AnotherAntiCheat Mod", version="1.1.2", acceptedMinecraftVersions="1.7.10")
public class AntiCheat {
    public static final String MODID = "anotheranticheat";
    public static final String NAME = "AnotherAntiCheat Mod";
    public static final String VERSION = "1.1.2";
    @SidedProxy(clientSide="com.anotherstar.anticheat.ClientProxy", serverSide="com.anotherstar.anticheat.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance(value="anotheranticheat")
    public static AntiCheat instance;
    public static SimpleNetworkWrapper antiCheatNetwork;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        antiCheatNetwork = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
