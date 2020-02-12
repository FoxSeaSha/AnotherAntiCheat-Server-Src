package com.anotherstar.anticheat;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import java.util.HashSet;
import net.minecraftforge.common.config.Configuration;

public class ConfigLoader {
    public static Configuration config = null;
    public static String[] md5s = null;
    public static HashSet<String> md5Set = new HashSet();
    public static String[] necessaryMd5s = null;
    public static HashSet<String> necessaryMd5Set = new HashSet();
    public static boolean receive = false;
    public static boolean extension = false;
    public static String antiCheatMessage = null;
    public static String antiCheatExtensionMessage = null;
    public static String timeOutMessage = null;
    public static int timeOut = 0;
    public static String[] whiteList = null;

    public static void load(FMLPreInitializationEvent event) {
        if (config == null) {
            File configFile = event.getSuggestedConfigurationFile();
            config = new Configuration(configFile);
            config.load();
            md5s = config.getStringList("md5s", "general", new String[0], "");
            necessaryMd5s = config.getStringList("necessaryMd5s", "general", new String[0], "");
            receive = config.getBoolean("receive", "general", true, "\u5f55\u5165\u5fc5\u8981mod");
            extension = config.getBoolean("extension", "general", false, "\u5f55\u5165\u53ef\u9009mod");
            antiCheatMessage = config.getString("antiCheatMessage", "general", "\u8bf7\u52ff\u81ea\u884c\u5b89\u88c5mod", "\u8e22\u51fa\u73a9\u5bb6\u663e\u793a\u4fe1\u606f");
            antiCheatExtensionMessage = config.getString("antiCheatExtensionMessage", "general", "\u8bf7\u52ff\u81ea\u884c\u5220\u9664mod", "\u8e22\u51fa\u73a9\u5bb6\u663e\u793a\u4fe1\u606f");
            timeOutMessage = config.getString("timeOutMessage", "general", "\u9a8c\u8bc1\u8d85\u65f6", "\u9a8c\u8bc1\u8d85\u65f6\u663e\u793a\u4fe1\u606f");
            timeOut = config.getInt("timeOut", "general", 10, 0, 120, "\u9a8c\u8bc1\u8d85\u65f6\u65f6\u95f4(s)");
            whiteList = config.getStringList("whiteList", "general", new String[0], "\u73a9\u5bb6\u767d\u540d\u5355");
            config.save();
            md5Set.clear();
            necessaryMd5Set.clear();
            for (String md5 : md5s) {
                md5Set.add((String)md5);
            }
            for (String md5 : necessaryMd5s) {
                necessaryMd5Set.add((String)md5);
            }
        }
    }

    public static void save() {
        if (config != null) {
            config.getConfigFile().delete();
            config.load();
            config.getStringList("md5s", "general", md5s, "");
            config.getStringList("necessaryMd5s", "general", necessaryMd5s, "");
            receive = config.getBoolean("receive", "general", false, "");
            extension = config.getBoolean("extension", "general", false, "");
            antiCheatMessage = config.getString("antiCheatMessage", "general", antiCheatMessage, "\u8e22\u51fa\u73a9\u5bb6\u663e\u793a\u4fe1\u606f");
            antiCheatExtensionMessage = config.getString("antiCheatExtensionMessage", "general", "\u8bf7\u52ff\u81ea\u884c\u5220\u9664mod", "\u8e22\u51fa\u73a9\u5bb6\u663e\u793a\u4fe1\u606f");
            timeOutMessage = config.getString("timeOutMessage", "general", timeOutMessage, "\u9a8c\u8bc1\u8d85\u65f6\u663e\u793a\u4fe1\u606f");
            timeOut = config.getInt("timeOut", "general", timeOut, 0, 120, "\u9a8c\u8bc1\u8d85\u65f6\u65f6\u95f4(s)");
            whiteList = config.getStringList("whiteList", "general", new String[0], "\u73a9\u5bb6\u767d\u540d\u5355");
            config.save();
            md5Set.clear();
            necessaryMd5Set.clear();
            for (String md5 : md5s) {
                md5Set.add((String)md5);
            }
            for (String md5 : necessaryMd5s) {
                necessaryMd5Set.add((String)md5);
            }
        }
    }

    public static void reload() {
        if (config != null) {
            config.load();
            md5s = config.getStringList("md5s", "general", new String[0], "");
            necessaryMd5s = config.getStringList("necessaryMd5s", "general", new String[0], "");
            receive = config.getBoolean("receive", "general", true, "");
            extension = config.getBoolean("extension", "general", false, "");
            antiCheatMessage = config.getString("antiCheatMessage", "general", "\u8bf7\u52ff\u81ea\u884c\u5b89\u88c5mod", "\u8e22\u51fa\u73a9\u5bb6\u663e\u793a\u4fe1\u606f");
            antiCheatExtensionMessage = config.getString("antiCheatExtensionMessage", "general", "\u8bf7\u52ff\u81ea\u884c\u5220\u9664mod", "\u8e22\u51fa\u73a9\u5bb6\u663e\u793a\u4fe1\u606f");
            timeOutMessage = config.getString("timeOutMessage", "general", "\u9a8c\u8bc1\u8d85\u65f6", "\u9a8c\u8bc1\u8d85\u65f6\u663e\u793a\u4fe1\u606f");
            timeOut = config.getInt("timeOut", "general", 10, 0, 120, "\u9a8c\u8bc1\u8d85\u65f6\u65f6\u95f4(s)");
            whiteList = config.getStringList("whiteList", "general", new String[0], "\u73a9\u5bb6\u767d\u540d\u5355");
            md5Set.clear();
            necessaryMd5Set.clear();
            for (String md5 : md5s) {
                md5Set.add((String)md5);
            }
            for (String md5 : necessaryMd5s) {
                necessaryMd5Set.add((String)md5);
            }
        }
    }
}
