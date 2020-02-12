package com.anotherstar.anticheat;

import java.lang.management.*;
import net.minecraft.client.*;
import org.apache.commons.codec.digest.*;
import cpw.mods.fml.relauncher.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import java.io.*;
import cpw.mods.fml.common.event.*;

public class ClientProxy extends CommonProxy
{
    public void preInit(final FMLPreInitializationEvent event) {
        final boolean isWin = System.getProperty("os.name").toLowerCase().startsWith("win");
        final boolean is64 = ManagementFactory.getRuntimeMXBean().getVmName().contains("64");
        final String dllName = "mods/AnotherStar/" + (isWin ? ("ANOTHERSTARANTICHEAT" + (is64 ? 64 : 32) + ".dll") : "libAnotherStarAntiCheatForLinux.so");
        final File dllFile = new File(Minecraft.getMinecraft().mcDataDir, dllName);
        if (dllFile.exists()) {
            String dllmd5 = "";
            try (final InputStream is65 = new FileInputStream(dllFile)) {
                dllmd5 = DigestUtils.md5Hex(is65);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            final String dllsmd5 = isWin ? (is64 ? "cf4a624955e4b08fb85bea41f1a92031" : "1ea5049ee76489e599fc2f308e86040f") : "1f4fcd6526ecd08db1c5989681a47ae4";
            if (!dllmd5.equals(dllsmd5)) {
                dllFile.delete();
            }
        }
        if (!dllFile.exists()) {
            dllFile.getParentFile().mkdirs();
            try (final InputStream is66 = this.getClass().getResourceAsStream(isWin ? ("/ANOTHERSTARANTICHEAT" + (is64 ? 64 : 32) + ".dll") : "/libAnotherStarAntiCheatForLinux.so");
                 final FileOutputStream fos = new FileOutputStream(dllFile)) {
                final byte[] buf = new byte[8192];
                int len;
                while ((len = is66.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        System.load(dllFile.getAbsolutePath());
        AntiCheat.antiCheatNetwork.registerMessage((IMessageHandler)new AntiCheatSTCPacketMessageHandler(), (Class)AntiCheatSTCPacketMessage.class, 0, Side.CLIENT);
        AntiCheat.antiCheatNetwork.registerMessage((IMessageHandler)new AntiCheatCTSPacketMessageHandler(), (Class)AntiCheatCTSPacketMessage.class, 1, Side.SERVER);
    }
    
    public void init(final FMLInitializationEvent event) {
    }
}
