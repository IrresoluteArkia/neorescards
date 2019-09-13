package irar.neorescards.world;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import irar.neorescards.card.Card;
import irar.neorescards.network.PCDMessage;
import irar.neorescards.network.PacketHandler;
import irar.neorescards.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget;

public class PlayerCardData implements INBTSerializable<CompoundNBT> {
	
	private UUID playerUUID;
	private List<CardData> cardData = new ArrayList<>();
	private AllCardData allData;

	public PlayerCardData(AllCardData allData, CompoundNBT nbt) {
		this.allData = allData;
		this.deserializeNBT(nbt);
	}
	
	public PlayerCardData(AllCardData allData, PlayerEntity playerIn) {
		this.allData = allData;
		playerUUID = PlayerEntity.getUUID(playerIn.getGameProfile());
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putString("UUID", playerUUID.toString());
		ListNBT cardDataNbt = new ListNBT();
		for(CardData data : cardData) {
			cardDataNbt.add(data.serializeNBT());
		}
		nbt.put("CARDDATA", cardDataNbt);
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if(nbt.contains("UUID")) {
			playerUUID = UUID.fromString(nbt.getString("UUID"));
		}
		if(nbt.contains("CARDDATA")) {
			ListNBT cardDataNbt = (ListNBT) nbt.get("CARDDATA");
			for(INBT data : cardDataNbt) {
				cardData.add(new CardData(this, (CompoundNBT) data));
			}
		}
		this.sendPlayerCardData();
	}

	public boolean matchesPlayer(PlayerEntity player) {
		return PlayerEntity.getUUID(player.getGameProfile()).equals(playerUUID);
	}

	public boolean hasCardInTier(Card card, int tier) {
		for(CardData data : cardData) {
			if(data.getCard().equals(card) && data.getTier() == tier) {
				return true;
			}
		}
		return false;
	}

	public boolean hasCard(Card card) {
		for(CardData data : cardData) {
			if(data.getCard().equals(card)) {
				return true;
			}
		}
		return false;
	}

	public int getTierForCard(Card card) {
		CardData data = getCardData(card);
		return data.getTier();
	}

	public CardData getCardData(Card card) {
		for(CardData data : cardData) {
			if(data.getCard().equals(card)) {
				return data;
			}
		}
		return null;
	}

	public void setCardInTier(Card card, int tier) {
		removeCard(card, false);
		CardData data = new CardData(this, card, tier);
		cardData.add(data);
		this.sendPlayerCardData();
	}

	public void removeCard(Card card) {
		this.removeCard(card, true);
	}
	
	public void removeCard(Card card, boolean sendUpdatePacket) {
		cardData.remove(getCardData(card));
		if(sendUpdatePacket) {
			this.sendPlayerCardData();
		}
	}

	public void removeAllCards() {
		cardData.clear();
	}

	public boolean hasCards() {
		return !cardData.isEmpty();
	}

	public void applyEffects(PlayerEntity player) {
		for(CardData data : cardData) {
			data.apply(player);
		}
	}

	public List<CardData> getCardData() {
		List<CardData> data = new ArrayList<>();
		data.addAll(cardData);
		return data;
	}

	public List<CardData> inTier(int tier) {
		List<CardData> inTier = new ArrayList<>();
		for(CardData data : cardData) {
			if(data.getTier() == tier) {
				inTier.add(data);
			}
		}
		return inTier;
	}

	public void markDirty() {
		allData.markDirty();
	}
	
	private void sendPlayerCardData() {
		if(EffectiveSide.get().equals(LogicalSide.SERVER)) {
			ServerPlayerEntity player = getPlayer();
			if(player != null) {
				CompoundNBT data = this.serializeNBT();
				sendPlayerCardData(player, data);
			}
		}
	}

	private void sendPlayerCardData(ServerPlayerEntity player, CompoundNBT data) {
		PacketHandler.HANDLER.sendTo(new PCDMessage(data), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
	}

	private ServerPlayerEntity getPlayer() {
		if(CommonProxy.world == null) {
			return null;
		}
		List<ServerPlayerEntity> players = CommonProxy.world.getServer().getPlayerList().getPlayers();
		for(ServerPlayerEntity player : players) {
			if(this.matchesPlayer(player)) {
				return player;
			}
		}
		return null;
	}

	public UUID getUUID() {
		return this.playerUUID;
	}

}
