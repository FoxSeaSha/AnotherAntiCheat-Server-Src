package com.anotherstar.anticheat;

import com.anotherstar.anticheat.AntiCheatCTSPacketMessage;
import com.anotherstar.anticheat.ConfigLoader;
import com.anotherstar.anticheat.PlayerJoinEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import org.apache.commons.codec.digest.DigestUtils;

public class AntiCheatCTSPacketMessageHandler
implements IMessageHandler<AntiCheatCTSPacketMessage, IMessage> {
	
    public IMessage onMessage(AntiCheatCTSPacketMessage message, MessageContext ctx) {
        PlayerJoinEvent.PlayerSalt playerSalt = (PlayerJoinEvent.PlayerSalt)PlayerJoinEvent.timers.remove(ctx.getServerHandler().playerEntity.getDisplayName());
        playerSalt.timer.cancel();
        if (ConfigLoader.receive) {
            ConfigLoader.md5s = new String[message.md5s.length];
            ConfigLoader.necessaryMd5s = new String[message.md5s.length];
            for (int i = 0; i < message.md5s.length; ++i) {
                ConfigLoader.md5s[i] = new String(message.md5s[i]);
                ConfigLoader.necessaryMd5s[i] = new String(message.md5s[i]);
            }
            ConfigLoader.save();
        } else if (ConfigLoader.extension) {
            ConfigLoader.md5s = new String[message.md5s.length];
            for (int i = 0; i < message.md5s.length; ++i) {
                ConfigLoader.md5s[i] = new String(message.md5s[i]);
            }
            ConfigLoader.save();
        } else {
            if (message.md5s.length == 0) {
                ctx.getServerHandler().playerEntity.playerNetServerHandler.kickPlayerFromServer(ConfigLoader.antiCheatMessage);
            }
            HashSet<String> md5s = new HashSet<String>();
            HashSet<String> necessaryMd5s = new HashSet<String>();
            for (String md5 : ConfigLoader.md5s) {
                md5s.add((String)DigestUtils.md5Hex((String)(md5 + playerSalt.salt)));
            }
            for (String md5 : ConfigLoader.necessaryMd5s) {
                necessaryMd5s.add((String)DigestUtils.md5Hex((String)(md5 + playerSalt.salt)));
            }
            HashSet<String> clientMd5s = new HashSet<String>();
            for (byte[] md5data : message.md5s) {
                try {
                    String md5 = new String(md5data, "UTF-8");
//                    if (!md5s.contains(md5)) {
//                        ctx.getServerHandler().playerEntity.playerNetServerHandler.kickPlayerFromServer(ConfigLoader.antiCheatMessage);
//                        break;
//                    }
                    clientMd5s.add(md5);
                }catch (Exception e) {
                    ctx.getServerHandler().playerEntity.playerNetServerHandler.kickPlayerFromServer(ConfigLoader.antiCheatMessage);
                }
            }
            for (String md5 : necessaryMd5s) {
                if (clientMd5s.contains(md5)) continue;
//                ctx.getServerHandler().playerEntity.playerNetServerHandler.kickPlayerFromServer(ConfigLoader.antiCheatExtensionMessage);
//                break;
            }
        }
        return null;
    }
}
