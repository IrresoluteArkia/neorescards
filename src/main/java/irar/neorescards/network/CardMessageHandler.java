package irar.neorescards.network;

import java.util.function.Supplier;

import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.card.effect.ICardEffect;
import irar.neorescards.item.ItemCard;
import irar.neorescards.proxy.CommonProxy;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraftforge.fml.network.NetworkEvent.Context;

// The params of the IMessageHandler are <REQ, REPLY>
// This means that the first param is the packet you are receiving, and the second is the packet you are returning.
// The returned packet can be used as a "response" from a sent packet.
public class CardMessageHandler {
	// Do note that the default constructor is required, but implicitly defined in this case
	
	public static void handleMessage(CardMessage message, Supplier<Context> context) {
		// This is the player the packet was sent to the server from
		ServerPlayerEntity serverPlayer = context.get().getSender();
		// The value that was sent
		int amount = message.toSend;
		// Execute the action on the main server thread by adding it as a scheduled task
		MinecraftServer server = serverPlayer.getServerWorld().getServer();
		server.enqueue(new TickDelayedTask(server.getTickCounter()+1, () -> {
			Card card = Cards.allCards.get(amount);
			int currentTier = CommonProxy.saveData.hasCard(card, serverPlayer) ? CommonProxy.saveData.getTierForCard(card, serverPlayer) : 0;
			CommonProxy.saveData.removeCard(card, serverPlayer);
			if(currentTier > 0) {
				for(ICardEffect effect : card.cardEffects) {
					effect.removeEffect(serverPlayer, currentTier);
				}
			}
//			serverPlayer.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
			ItemStack stack = ItemCard.getItemStackWithTierCardAndMetadata(card, currentTier + 1, 1, false);
			serverPlayer.world.addEntity(new ItemEntity(serverPlayer.world, serverPlayer.posX, serverPlayer.posY, serverPlayer.posZ, stack));
			// CommonProxy.saveData.upgradeCard(Cards.allCards.get(amount), serverPlayer);
		}));
	}
}