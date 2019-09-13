package irar.neorescards.world;

import java.util.ArrayList;
import java.util.List;

import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.card.effect.ICardEffect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class CardData implements INBTSerializable<CompoundNBT> {

	private Card card;
	private int tier;
	private List<EffectData> effectData = new ArrayList<>();
	private PlayerCardData playerData;

	public CardData(PlayerCardData playerData, CompoundNBT data) {
		this.playerData = playerData;
		this.deserializeNBT(data);
	}

	public CardData(PlayerCardData playerData, Card card, int tier) {
		this.card = card;
		this.tier = tier;
		this.playerData = playerData;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putString("CARD", card.id);
		tag.putInt("TIER", tier);
		ListNBT eData = new ListNBT();
		for(EffectData data : effectData) {
			eData.add(data.serializeNBT());
		}
		tag.put("EFFECTDATA", eData);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		card = Cards.getCardFromId(nbt.getString("CARD"));
		tier = nbt.getInt("TIER");
		if(nbt.contains("EFFECTDATA")) {
			ListNBT eData = (ListNBT) nbt.get("EFFECTDATA");
			for(INBT data : eData) {
				effectData.add(new EffectData((CompoundNBT) data));
			}
		}
	}

	public Card getCard() {
		return card;
	}

	public int getTier() {
		return tier;
	}

	public static List<Card> cards(List<CardData> cardData) {
		List<Card> cards = new ArrayList<>();
		for(CardData data : cardData) {
			cards.add(data.getCard());
		}
		return cards;
	}

	public void apply(PlayerEntity player) {
		for(int i = 0; i < card.cardEffects.size(); i++) {
			ICardEffect effect = card.cardEffects.get(i);
			if(effectData.size() <= i) {
				EffectData data = new EffectData(effect);
				effectData.add(data);
			}
			EffectData data = effectData.get(i);
			data.applyEffect(this, effect, player);
		}
	}

	public void markDirty() {
		playerData.markDirty();
	}

	public void removeEffect(PlayerEntity player) {
		for(int i = 0; i < card.cardEffects.size(); i++) {
			ICardEffect effect = card.cardEffects.get(i);
			if(effectData.size() <= i) {
				EffectData data = new EffectData(effect);
				effectData.add(data);
			}
			EffectData data = effectData.get(i);
			data.removeEffect(this, effect, player);
		}
	}

}
