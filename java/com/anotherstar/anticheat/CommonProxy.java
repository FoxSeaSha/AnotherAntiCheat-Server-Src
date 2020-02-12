package com.anotherstar.anticheat;

import com.anotherstar.anticheat.AntiCheat;
import com.anotherstar.anticheat.AntiCheatCTSPacketMessage;
import com.anotherstar.anticheat.AntiCheatCTSPacketMessageHandler;
import com.anotherstar.anticheat.AntiCheatSTCPacketMessage;
import com.anotherstar.anticheat.AntiCheatSTCPacketMessageHandler;
import com.anotherstar.anticheat.ConfigLoader;
import com.anotherstar.anticheat.PlayerJoinEvent;
import com.anotherstar.anticheat.ReloadCommand;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.command.ICommand;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        AntiCheat.antiCheatNetwork.registerMessage((IMessageHandler)new AntiCheatSTCPacketMessageHandler(), AntiCheatSTCPacketMessage.class, 0, Side.CLIENT);
        AntiCheat.antiCheatNetwork.registerMessage((IMessageHandler)new AntiCheatCTSPacketMessageHandler(), AntiCheatCTSPacketMessage.class, 1, Side.SERVER);
        ConfigLoader.load((FMLPreInitializationEvent)event);
    }

    public void init(FMLInitializationEvent event) { 
        FMLCommonHandler.instance().bus().register(new PlayerJoinEvent());
    }

    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new ReloadCommand());
    }
}
