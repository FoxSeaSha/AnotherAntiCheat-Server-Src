package com.anotherstar.anticheat;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AntiCheatCTSPacketMessage
implements IMessage {
    public byte[][] md5s;

    public AntiCheatCTSPacketMessage() {
    }

    public AntiCheatCTSPacketMessage(byte[][] md5s) {
        this.md5s = md5s;
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound nbt = ByteBufUtils.readTag((ByteBuf)buf);
        NBTTagList md5List = nbt.getTagList("md5s", 7);
        this.md5s = new byte[md5List.tagCount()][];
        for (int i = this.md5s.length - 1; i >= 0; --i) {
        	//func_150292_c GetByteArray
            this.md5s[i] = ((NBTTagByteArray)md5List.removeTag(i)).func_150292_c();
        }
    }

    public void toBytes(ByteBuf buf) {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList strList = new NBTTagList();
        for (byte[] md5 : this.md5s) {
            strList.appendTag((NBTBase)new NBTTagByteArray(md5));
        }
        nbt.setTag("md5s", (NBTBase)strList);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)nbt);
    }
}
