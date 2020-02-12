package com.anotherstar.anticheat;

import com.anotherstar.anticheat.AntiCheat;
import com.anotherstar.anticheat.ConfigLoader;
import com.anotherstar.anticheat.PlayerJoinEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.commons.codec.digest.DigestUtils;
import scala.util.Random;

public class PlayerJoinEvent {
    private Random rand = new Random();
    public static HashMap<String, PlayerSalt> timers = new HashMap();

    @SubscribeEvent	
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        String playerName = event.player.getDisplayName();
        for (String name : ConfigLoader.whiteList) {
            if (!playerName.equals((Object)name)) continue;
            return;
        }
        String salt = DigestUtils.md5Hex((String)this.rand.nextString(16));
        byte[] asalt = new byte[]{};
        try {
            if (!ConfigLoader.receive && !ConfigLoader.extension) {
                asalt = salt.getBytes("UTF-8");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        AntiCheat.antiCheatNetwork.sendTo((IMessage)new AntiCheatSTCPacketMessage(asalt), (EntityPlayerMP)event.player);//发送验证信息
        Timer timer = new Timer();
        timer.schedule((TimerTask)new PlayerTask((EntityPlayerMP)event.player), (long)(ConfigLoader.timeOut * Integer.MAX_VALUE));
        timers.put(event.player.getDisplayName(), new PlayerSalt(salt, timer));
    }
    
    class PlayerTask
    extends TimerTask {
        private EntityPlayerMP player;

        public PlayerTask(EntityPlayerMP player) {
            this.player = player;
        }

        public void run() {
            this.player.playerNetServerHandler.kickPlayerFromServer(ConfigLoader.timeOutMessage);
        }
    }

    
    public static class PlayerSalt {
        public String salt;
        public Timer timer;

        public PlayerSalt(String salt, Timer timer) {
            this.salt = salt;
            this.timer = timer;
        }
    }

    
}
