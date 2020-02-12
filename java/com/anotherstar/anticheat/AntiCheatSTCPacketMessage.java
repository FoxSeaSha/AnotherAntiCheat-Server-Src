package com.anotherstar.anticheat;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class AntiCheatSTCPacketMessage
implements IMessage {
    public byte[] salt;

    public AntiCheatSTCPacketMessage() {
    }

    public AntiCheatSTCPacketMessage(byte[] salt) {
        this.salt = salt;
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound nbt = ByteBufUtils.readTag((ByteBuf)buf);
        this.salt = nbt.getByteArray("salt");
    }

    public void toBytes(ByteBuf buf) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByteArray("salt", this.salt);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)nbt);
    }
}
