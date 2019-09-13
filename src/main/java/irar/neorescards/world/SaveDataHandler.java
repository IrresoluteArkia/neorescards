package irar.neorescards.world;

import java.util.ArrayList;
import java.util.UUID;

import irar.neorescards.Ref;
import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.proxy.CommonProxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class SaveDataHandler extends WorldSavedData {
	private static final String DATA_NAME = Ref.MODID + "_PLAYER_CARD_DATA";
//	private ArrayList<Object[]> playerCardData = new ArrayList<Object[]>();
	AllCardData allCardData = new AllCardData(this);
	
	// Required constructors
	public SaveDataHandler() {
		super(DATA_NAME);
	}
	
	public SaveDataHandler(String s) {
		super(s);
	}
	
	@Override
	public void read(CompoundNBT nbt) {
		if(nbt.contains(DATA_NAME)) {
			allCardData.deserializeNBT((ListNBT) nbt.get(DATA_NAME));
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put(DATA_NAME, allCardData.serializeNBT());
		return compound;
	}
	
	public static SaveDataHandler get(World worldbase) {
		SaveDataHandler instance = null;
		if(worldbase instanceof ServerWorld) {
			ServerWorld world = (ServerWorld) worldbase;
			DimensionSavedDataManager storage = world.getSavedData();
			instance = storage.getOrCreate(SaveDataHandler::new, DATA_NAME);
		}
		if (instance == null) {
			instance = new SaveDataHandler();
		}
		return instance;
	}
	
	public boolean isPlayerInList(PlayerEntity player){
		return allCardData.getPlayerData(player) != null;
	}
	
	public void addPlayer(PlayerEntity playerIn, Card selectedCard){
		allCardData.addPlayer(playerIn);
		if(selectedCard != null) {
			allCardData.setCardTier(playerIn, selectedCard, 1);
		}
		this.markDirty();
	}

	public PlayerCardData getCardDataForPlayer(PlayerEntity player) {
		return allCardData.getPlayerData(player);
	}

	public boolean hasCardInTier(Card currentCard, int tier, PlayerEntity player) {
		return getCardDataForPlayer(player).hasCardInTier(currentCard, tier);
	}

	public boolean hasCard(Card currentCard, PlayerEntity player) {
		return getCardDataForPlayer(player).hasCard(currentCard);
	}
	
	public int getTierForCard(Card card, PlayerEntity player){
		return getCardDataForPlayer(player).getTierForCard(card);
	}

	public void upgradeCard(Card card, PlayerEntity player) {
		PlayerCardData data = getCardDataForPlayer(player);
		int tier = data.getTierForCard(card);
		if(tier <= 0) {
			data.setCardInTier(card, 1);
		}else {
			data.setCardInTier(card, tier + 1);
		}
		markDirty();
	}

	public void setCardInTier(Card card, int tier, PlayerEntity player) {
		getCardDataForPlayer(player).setCardInTier(card, tier);
	}

	public void removeCard(Card card, PlayerEntity player) {
		getCardDataForPlayer(player).removeCard(card);
		this.markDirty();
	}

	public void removePlayer(PlayerEntity playerIn) {
		getCardDataForPlayer(playerIn).removeAllCards();
		this.markDirty();
	}

	public void recievePlayerData(CompoundNBT data) {
		allCardData.addOrReplacePlayerData(data);
	}
	
}