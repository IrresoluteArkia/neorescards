package irar.neorescards.card;

import java.util.ArrayList;
import java.util.Random;

import irar.neorescards.card.effect.BadFlightEffect;
import irar.neorescards.card.effect.ICardEffect;
import irar.neorescards.item.ItemCard;
import irar.neorescards.util.PH;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class Cards {
	
	private static Random rand = new Random();
	public static ArrayList<Card> allCards = new ArrayList<Card>();
	public static ArrayList<Item> allCardItems = new ArrayList<Item>();
	@Deprecated
	public static ArrayList<ArrayList<Card>> cardsBySuit = new ArrayList<>();
	
	public static void init() {
		for(Suit suit : Suit.values()) {
			for(CardType type : CardType.values()) {
				makeCard(suit, type);
			}
		}
	}
	
	public static void addEffects() {
		fromSuitAndType(Suit.CLUB, CardType.ACE)
		.addEffect(ICardEffect.fromPotion(PH.STRENGTH))
		.setMaxLvl(10);
		
		fromSuitAndType(Suit.CLUB, CardType.TWO)
		.addEffect(ICardEffect.fromPotion(PH.GLOWING))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.CLUB, CardType.THREE)
		.addEffect(ICardEffect.fromPotion(PH.NIGHT_VISION))
		.addEffect(ICardEffect.periodicPotion(PH.NAUSEA)
				.setDuration(7)
				.setFrequency(37))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.CLUB, CardType.FOUR)
		.addEffect(ICardEffect.fromPotion(PH.JUMP_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.SLOWNESS)
				.setDuration(4)
				.setFrequency(24))
		.setMaxLvl(10);
		
		fromSuitAndType(Suit.CLUB, CardType.FIVE)
		.addEffect(ICardEffect.fromPotion(PH.WATER_BREATHING))
		.addEffect(ICardEffect.fromPotion(PH.UNLUCK))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.CLUB, CardType.SIX)
		.addEffect(ICardEffect.fromPotion(PH.INVISIBILITY))
		.addEffect(ICardEffect.periodicPotion(PH.LEVITATION)
				.setDuration(2)
				.setFrequency(42))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.CLUB, CardType.SEVEN)
		.addEffect(ICardEffect.fromPotion(PH.FIRE_RESISTANCE))
		.addEffect(ICardEffect.periodicPotion(PH.BLINDNESS)
				.setDuration(3)
				.setFrequency(120))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.CLUB, CardType.EIGHT)
		.addEffect(ICardEffect.fromPotion(PH.HEALTH_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.HUNGER)
				.setDuration(8)
				.setFrequency(143))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.CLUB, CardType.NINE)
		.addEffect(ICardEffect.periodicPotion(PH.ABSORPTION)
				.setDuration(30)
				.setFrequency(153))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.CLUB, CardType.TEN)
		.addEffect(ICardEffect.fromPotion(PH.FLIGHT).setInfoHidden(true))
		.addEffect(new BadFlightEffect(5))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.CLUB, CardType.JACK)
		.addEffect(ICardEffect.fromPotion(PH.STRENGTH))
		.addEffect(ICardEffect.periodicPotion(PH.WEAKNESS)
				.setDuration(10)
				.setFrequency(100))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.CLUB, CardType.QUEEN)
		.addEffect(ICardEffect.fromPotion(PH.HEALING_TOUCH))
		.addEffect(ICardEffect.periodicPotion(PH.STRENGTH)
				.setDuration(11)
				.setFrequency(67))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.CLUB, CardType.KING)
		.addEffect(ICardEffect.fromPotion(PH.FORTUNE))
		.addEffect(ICardEffect.periodicPotion(PH.WEAKNESS)
				.setDuration(7)
				.setFrequency(94))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.CLUB, CardType.FE)
		.addEffect(ICardEffect.fromPotion(PH.POISON_BODY))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.CLUB, CardType.UKD)
		.addEffect(ICardEffect.fromPotion(PH.WAR_DESTINY))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.DIAMOND, CardType.ACE)
		.addEffect(ICardEffect.fromPotion(PH.RESISTANCE))
		.setMaxLvl(2);
		
		fromSuitAndType(Suit.DIAMOND, CardType.TWO)
		.addEffect(ICardEffect.fromPotion(PH.RESISTANCE))
		.addEffect(ICardEffect.periodicPotion(PH.BLINDNESS)
				.setDuration(3)
				.setFrequency(69))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.DIAMOND, CardType.THREE)
		.addEffect(ICardEffect.periodicPotion(PH.ABSORPTION)
				.setDuration(30)
				.setFrequency(30))
		.addEffect(ICardEffect.periodicPotion(PH.WEAKNESS)
				.setDuration(5)
				.setFrequency(22))
		.setMaxLvl(2);
		
		fromSuitAndType(Suit.DIAMOND, CardType.FOUR)
		.addEffect(ICardEffect.fromPotion(PH.HEALTH_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.ABSORPTION)
				.setDuration(30)
				.setFrequency(60))
		.addEffect(ICardEffect.periodicPotion(PH.HUNGER)
				.setDuration(5)
				.setFrequency(142))
		.addEffect(ICardEffect.periodicPotion(PH.POISON)
				.setDuration(7)
				.setFrequency(143))
		.setMaxLvl(4);
		
		fromSuitAndType(Suit.DIAMOND, CardType.FIVE)
		.addEffect(ICardEffect.fromPotion(PH.SPEED))
		.addEffect(ICardEffect.periodicPotion(PH.SLOWNESS)
				.setDuration(4)
				.setFrequency(70))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.DIAMOND, CardType.SIX)
		.addEffect(ICardEffect.fromPotion(PH.HASTE))
		.addEffect(ICardEffect.periodicPotion(PH.MINING_FATIGUE)
				.setDuration(10)
				.setFrequency(81))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.DIAMOND, CardType.SEVEN)
		.addEffect(ICardEffect.fromPotion(PH.REGENERATION))
		.addEffect(ICardEffect.periodicPotion(PH.WITHER)
				.setDuration(5)
				.setFrequency(197))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.DIAMOND, CardType.EIGHT)
		.addEffect(ICardEffect.fromPotion(PH.THE_FATED))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.DIAMOND, CardType.NINE)
		.addEffect(ICardEffect.fromPotion(PH.NIGHT_VISION))
		.addEffect(ICardEffect.periodicPotion(PH.NAUSEA)
				.setDuration(7)
				.setFrequency(37))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.DIAMOND, CardType.TEN)
		.addEffect(ICardEffect.fromPotion(PH.FLIGHT).setInfoHidden(true))
		.addEffect(new BadFlightEffect(5))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.DIAMOND, CardType.JACK)
		.addEffect(ICardEffect.fromPotion(PH.RESISTANCE))
		.addEffect(ICardEffect.periodicPotion(PH.SLOWNESS)
				.setDuration(7)
				.setFrequency(73))
		.setMaxLvl(2);
		
		fromSuitAndType(Suit.DIAMOND, CardType.QUEEN)
		.addEffect(ICardEffect.fromPotion(PH.HEALING_TOUCH))
		.addEffect(ICardEffect.periodicPotion(PH.RESISTANCE)
				.setDuration(10)
				.setFrequency(200))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.DIAMOND, CardType.KING)
		.addEffect(ICardEffect.fromPotion(PH.FORTUNE))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.DIAMOND, CardType.FE)
		.addEffect(ICardEffect.fromPotion(PH.FLAME_BODY))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.DIAMOND, CardType.UKD)
		.addEffect(ICardEffect.fromPotion(PH.PEACEMAKER))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.SPADE, CardType.ACE)
		.addEffect(ICardEffect.fromPotion(PH.HASTE))
		.setMaxLvl(10);
		
		fromSuitAndType(Suit.SPADE, CardType.TWO)
		.addEffect(ICardEffect.fromPotion(PH.SPEED))
		.setMaxLvl(9);
		
		fromSuitAndType(Suit.SPADE, CardType.THREE)
		.addEffect(ICardEffect.fromPotion(PH.SPEED))
		.addEffect(ICardEffect.periodicPotion(PH.NAUSEA)
				.setDuration(7)
				.setFrequency(58))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.SPADE, CardType.FOUR)
		.addEffect(ICardEffect.fromPotion(PH.HASTE))
		.addEffect(ICardEffect.fromPotion(PH.SPEED))
		.addEffect(ICardEffect.periodicPotion(PH.POISON)
				.setDuration(5)
				.setFrequency(91))
		.addEffect(ICardEffect.periodicPotion(PH.WITHER)
				.setDuration(5)
				.setFrequency(172))
		.setMaxLvl(4);
		
		fromSuitAndType(Suit.SPADE, CardType.FIVE)
		.addEffect(ICardEffect.fromPotion(PH.INVISIBILITY))
		.addEffect(ICardEffect.periodicPotion(PH.LEVITATION)
				.setDuration(2)
				.setFrequency(45))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.SPADE, CardType.SIX)
		.addEffect(ICardEffect.fromPotion(PH.HEALTH_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.HUNGER)
				.setDuration(8)
				.setFrequency(132))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.SPADE, CardType.SEVEN)
		.addEffect(ICardEffect.fromPotion(PH.FORTUNE))
		.addEffect(ICardEffect.periodicPotion(PH.MINING_FATIGUE)
				.setDuration(10)
				.setFrequency(94))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.SPADE, CardType.EIGHT)
		.addEffect(ICardEffect.fromPotion(PH.THE_FATED))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.SPADE, CardType.NINE)
		.addEffect(ICardEffect.fromPotion(PH.JUMP_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.SLOWNESS)
				.setDuration(4)
				.setFrequency(24))
		.setMaxLvl(10);
		
		fromSuitAndType(Suit.SPADE, CardType.TEN)
		.addEffect(ICardEffect.fromPotion(PH.FLIGHT).setInfoHidden(true))
		.addEffect(new BadFlightEffect(5))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.SPADE, CardType.JACK)
		.addEffect(ICardEffect.fromPotion(PH.HASTE))
		.addEffect(ICardEffect.periodicPotion(PH.MINING_FATIGUE)
				.setDuration(10)
				.setFrequency(103))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.SPADE, CardType.QUEEN)
		.addEffect(ICardEffect.fromPotion(PH.HEALING_TOUCH))
		.addEffect(ICardEffect.periodicPotion(PH.HASTE)
				.setDuration(10)
				.setFrequency(221))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.SPADE, CardType.KING)
		.addEffect(ICardEffect.fromPotion(PH.FORTUNE))
		.addEffect(ICardEffect.periodicPotion(PH.MINING_FATIGUE)
				.setDuration(10)
				.setFrequency(137))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.SPADE, CardType.FE)
		.addEffect(ICardEffect.fromPotion(PH.CURSED_BODY))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.SPADE, CardType.UKD)
		.addEffect(ICardEffect.fromPotion(PH.SOULLESS))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.HEART, CardType.ACE)
		.addEffect(ICardEffect.fromPotion(PH.REGENERATION))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.HEART, CardType.TWO)
		.addEffect(ICardEffect.fromPotion(PH.HEALTH_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.BLINDNESS)
				.setDuration(3)
				.setFrequency(95))
		.setMaxLvl(10);
		
		fromSuitAndType(Suit.HEART, CardType.THREE)
		.addEffect(ICardEffect.periodicPotion(PH.ABSORPTION)
				.setDuration(30)
				.setFrequency(30))
		.addEffect(ICardEffect.periodicPotion(PH.NAUSEA)
				.setDuration(8)
				.setFrequency(73))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.HEART, CardType.FOUR)
		.addEffect(ICardEffect.fromPotion(PH.HEALTH_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.ABSORPTION)
				.setDuration(30)
				.setFrequency(60))
		.addEffect(ICardEffect.periodicPotion(PH.NAUSEA)
				.setDuration(5)
				.setFrequency(148))
		.addEffect(ICardEffect.periodicPotion(PH.POISON)
				.setDuration(7)
				.setFrequency(135))
		.setMaxLvl(4);
		
		fromSuitAndType(Suit.HEART, CardType.FIVE)
		.addEffect(ICardEffect.fromPotion(PH.HEALTH_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.HUNGER)
				.setDuration(7)
				.setFrequency(106))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.HEART, CardType.SIX)
		.addEffect(ICardEffect.fromPotion(PH.STRENGTH))
		.addEffect(ICardEffect.periodicPotion(PH.HUNGER)
				.setDuration(8)
				.setFrequency(129))
		.setMaxLvl(6);
		
		fromSuitAndType(Suit.HEART, CardType.SEVEN)
		.addEffect(ICardEffect.fromPotion(PH.HEALTH_BOOST))
		.addEffect(ICardEffect.periodicPotion(PH.WITHER)
				.setDuration(3)
				.setFrequency(175))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.HEART, CardType.EIGHT)
		.addEffect(ICardEffect.fromPotion(PH.NIGHT_VISION))
		.addEffect(ICardEffect.periodicPotion(PH.NAUSEA)
				.setDuration(7)
				.setFrequency(47))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.HEART, CardType.NINE)
		.addEffect(ICardEffect.fromPotion(PH.WATER_BREATHING))
		.addEffect(ICardEffect.fromPotion(PH.UNLUCK))
		.setMaxLvl(1);
		
		fromSuitAndType(Suit.HEART, CardType.TEN)
		.addEffect(ICardEffect.fromPotion(PH.FLIGHT).setInfoHidden(true))
		.addEffect(new BadFlightEffect(5))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.HEART, CardType.JACK)
		.addEffect(ICardEffect.fromPotion(PH.REGENERATION))
		.addEffect(ICardEffect.periodicPotion(PH.WITHER)
				.setDuration(5)
				.setFrequency(198))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.HEART, CardType.QUEEN)
		.addEffect(ICardEffect.fromPotion(PH.HEALING_TOUCH))
		.addEffect(ICardEffect.periodicPotion(PH.REGENERATION)
				.setDuration(3)
				.setFrequency(199))
		.setMaxLvl(7);
		
		fromSuitAndType(Suit.HEART, CardType.KING)
		.addEffect(ICardEffect.fromPotion(PH.FORTUNE))
		.addEffect(ICardEffect.periodicPotion(PH.WITHER)
				.setDuration(4)
				.setFrequency(176))
		.setMaxLvl(3);
		
		fromSuitAndType(Suit.HEART, CardType.FE)
		.addEffect(ICardEffect.fromPotion(PH.THORNED_BODY))
		.setMaxLvl(5);
		
		fromSuitAndType(Suit.HEART, CardType.UKD)
		.addEffect(ICardEffect.fromPotion(PH.DETERMINATION))
		.setMaxLvl(3);
		
	}
	
	private static Card makeCard(Suit suit, CardType type) {
		Card card = new Card(suit, type);
		allCards.add(card);
		return card;
	}

	public static void addCard(Card card){
		allCards.add(card);
	}
	
	public static Card getCardFromId(String id){
		Card card = null;
		for(int i = 0; i < allCards.size(); i++){
			if(allCards.get(i).id.equals(id)){
				card = allCards.get(i);
			}
		}
//		System.out.println("returning a null card; THIS WILL PROBABLY CAUSE A PROBLEM!!!!");
		return card;
	}
	
	public static Card getRandomCard(){
		return allCards.get(rand.nextInt(allCards.size()));
	}

	public static Card fromSuitAndType(Suit suit, CardType type) {
		for(Card card : allCards) {
			if(card.suit.equals(suit) && card.type.equals(type)) {
				return card;
			}
		}
		return null;
	}

	
}
