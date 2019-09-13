package irar.neorescards.world;

import java.util.ArrayList;
import java.util.List;

import irar.neorescards.card.Card;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class AllCardData implements INBTSerializable<ListNBT>{

	List<PlayerCardData> playerCardData = new ArrayList<>();
	private SaveDataHandler saveData;
	
	public AllCardData(SaveDataHandler saveData) {
		this.saveData = saveData;
	}
	
	@Override
	public ListNBT serializeNBT() {
		ListNBT tagList = new ListNBT();
		for(PlayerCardData data : playerCardData) {
			tagList.add(data.serializeNBT());
		}
		return tagList;
	}

	@Override
	public void deserializeNBT(ListNBT nbt) {
		for(INBT data : nbt) {
			PlayerCardData playerData = new PlayerCardData(this, (CompoundNBT) data);
			playerCardData.add(playerData);
		}
	}

	public PlayerCardData getPlayerData(PlayerEntity player) {
		for(PlayerCardData data : playerCardData) {
			if(data.matchesPlayer(player)) {
				return data;
			}
		}
		return null;
	}

	public void addPlayer(PlayerEntity player) {
		PlayerCardData playerData = new PlayerCardData(this, player);
		playerCardData.add(playerData);
	}

	public void setCardTier(PlayerEntity player, Card card, int tier) {
		if(this.getPlayerData(player) == null) {
			addPlayer(player);
		}
		getPlayerData(player).setCardInTier(card, tier);
	}

	public void markDirty() {
		saveData.markDirty();
	}

	public void addOrReplacePlayerData(CompoundNBT data) {
		PlayerCardData newPlayerData = new PlayerCardData(this, data);
		PlayerCardData toRemove = null;
		for(PlayerCardData playerData : playerCardData) {
			if(playerData.getUUID().equals(newPlayerData.getUUID())) {
				toRemove = playerData;
			}
		}
		if(toRemove != null) {
			playerCardData.remove(toRemove);
		}
		playerCardData.add(newPlayerData);
	}

}
