package irar.neorescards.network;

import net.minecraft.network.PacketBuffer;

public class CardMessage{

	public int toSend;
	
	public CardMessage(){}
	
	public CardMessage(int toSend) {
		this.toSend = toSend;
	}

	public static void encode(CardMessage packet, PacketBuffer buf) {
		buf.writeVarInt(packet.toSend);
	}

	public static CardMessage decode(PacketBuffer buf) {
		return new CardMessage(buf.readVarInt());
	}
}
