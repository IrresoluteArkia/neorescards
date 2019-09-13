package irar.neorescards.card;

import java.util.ArrayList;
import java.util.List;

import irar.neorescards.card.effect.ICardEffect;
import net.minecraft.potion.EffectInstance;

public class Card {
	
	public String id;
	public Suit suit;
	public CardType type;
	@Deprecated
	public ArrayList<EffectInstance> EffectInstances = new ArrayList<EffectInstance>();
	@Deprecated
	public int[] maxAmps;
	public List<ICardEffect> cardEffects = new ArrayList<>();
	public int maxLvl;
	
//	@Deprecated
//	public Card(Suit suit, CardType type, int[] potionIds, int[] potionAmps, int[] maxAmps, int[] maxLvl){
//		this.suit = suit;
//		this.type = type;
//		this.id = suit + "_" + type;
//		this.maxAmps = maxAmps;
//		this.maxLvl = maxLvl == null ? 255 : maxLvl[0];
//		if(potionIds != null && potionAmps != null) {
//			for(int i = 0; i < potionIds.length; i++){
//				EffectInstances.add(new EffectInstance(Potion.getPotionById(potionIds[i]), 10000, potionAmps[i]));
//			}
//		}
//	}
//	
//	@Deprecated
//	public Card(Suit suit, CardType type, int maxLvl, ICardEffect... effects) {
//		this.suit = suit;
//		this.type = type;
//		this.id = suit + "_" + type;
//		this.maxLvl = maxLvl;
//		this.cardEffects = Arrays.asList(effects);
//	}
	
	public Card(Suit suit, CardType type) {
		this.suit = suit;
		this.type = type;
		this.id = suit + "_" + type;
		this.cardEffects = new ArrayList<>();
		this.maxLvl = 1;
	}

	public static String[] getSuitAndTypeFromId(String id){
		return id.split("_");
	}
	
	public static String getIdFromSuitAndType(String[] sAndT){
		return sAndT[0] + "_" + sAndT[1];
	}
	
	Card addEffect(ICardEffect effect) {
		cardEffects.add(effect);
		return this;
	}
	
	Card setMaxLvl(int i) {
		this.maxLvl = i;
		return this;
	}
	
}
