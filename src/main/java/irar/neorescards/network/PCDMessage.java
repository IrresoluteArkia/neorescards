package irar.neorescards.network;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

public class PCDMessage{

	public CompoundNBT data;
	
	public PCDMessage(){}
	
	public PCDMessage(CompoundNBT data) {
		this.data = data;
	}
	
	public static void encode(PCDMessage packet, PacketBuffer buf) {
		buf.writeCompoundTag(packet.data);
	}
	
	public static PCDMessage decode(PacketBuffer buf) {
		return new PCDMessage(buf.readCompoundTag());
	}
}
