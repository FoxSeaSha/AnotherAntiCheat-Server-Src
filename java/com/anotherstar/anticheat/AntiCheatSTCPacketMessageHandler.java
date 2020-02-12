package com.anotherstar.anticheat;

import com.anotherstar.anticheat.AntiCheatCTSPacketMessage;
import com.anotherstar.anticheat.AntiCheatSTCPacketMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class AntiCheatSTCPacketMessageHandler
implements IMessageHandler<AntiCheatSTCPacketMessage, AntiCheatCTSPacketMessage> {
    public AntiCheatCTSPacketMessage onMessage(AntiCheatSTCPacketMessage message, MessageContext ctx) {
        return this.messageHandler(message, ctx);
    }

    private native AntiCheatCTSPacketMessage messageHandler(AntiCheatSTCPacketMessage var1, MessageContext var2);
}
