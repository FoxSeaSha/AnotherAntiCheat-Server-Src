package com.anotherstar.anticheat;

import com.anotherstar.anticheat.ConfigLoader;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class ReloadCommand
extends CommandBase {
    public String getCommandName() {
        return "anotherstaranticheatreload";
    }

    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            throw new WrongUsageException("commands.position.usage", new Object[0]);
        }
        ConfigLoader.reload();
    }

    public List getCommandAliases() {
        return Arrays.asList((Object[])new String[]{"aacreload"});
    }

}
