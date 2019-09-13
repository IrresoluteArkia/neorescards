package irar.neorescards.network;

import java.util.function.Supplier;

import irar.neorescards.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.network.NetworkEvent.Context;

// The params of the IMessageHandler are <REQ, REPLY>
// This means that the first param is the packet you are receiving, and the second is the packet you are returning.
// The returned packet can be used as a "response" from a sent packet.
public class PCDMessageHandler{
	// Do note that the default constructor is required, but implicitly defined in this case
	
	public static void handleMessage(PCDMessage message, Supplier<Context> context) {
		Minecraft.getInstance().enqueue(() -> {
			CommonProxy.saveData.recievePlayerData(message.data);
		});
	}
}