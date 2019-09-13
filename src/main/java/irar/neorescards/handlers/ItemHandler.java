package irar.neorescards.handlers;


import java.util.ArrayList;
import java.util.Random;

import irar.neorescards.Ref;
import irar.neorescards.card.Card;
import irar.neorescards.card.CardType;
import irar.neorescards.card.Cards;
import irar.neorescards.card.Suit;
import irar.neorescards.item.ItemCard;
import irar.neorescards.item.ItemCardSelector;
import irar.neorescards.item.ItemCardSelectorTiered;
import irar.neorescards.item.ItemIrresoluteCard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemHandler {
	public static ArrayList<Item> allItems = new ArrayList<Item>();
	public static Item CardSelector;
	public static Item CardIrresolute;
	public static Item CardSelectorTiered;
	
	public static void init(){
		CardSelector = new ItemCardSelector("card_selector");
		CardIrresolute = new ItemIrresoluteCard("card_irresolute");
		CardSelectorTiered = new ItemCardSelectorTiered("card_selector_tiered");
		for(Card card : Cards.allCards) {
			Item CurrentCard = new ItemCard("card_" + card.suit.id + "_" + card.type.id, card);
			Cards.allCardItems.add(CurrentCard);
		}
		
		allItems.add(CardSelector);
		allItems.add(CardIrresolute);
		allItems.add(CardSelectorTiered);
		
		for(int i = 0; i < Cards.allCardItems.size(); i++){
			allItems.add(Cards.allCardItems.get(i));
		}
		
	}
	
	public static void register(){
		 for(int i = 0; i < allItems.size(); i++){
			 ForgeRegistries.ITEMS.register(allItems.get(i));
		 }
	}
	
	public static void registerRenders(){
		 for(int i = 0; i < allItems.size(); i++){
			 registerRender(allItems.get(i));
		 }
	}
	
	public static void registerRender(Item item){
//		if(item instanceof ItemCard) {
//			ItemCard card = (ItemCard) item;
//			ModelBakery.registerItemVariants(item, card.getCard().type.texture);
//			ModelBakery.registerItemVariants(item, card.getCard().suit.texture);
//			ModelLoader.setCustomMeshDefinition(item, card);
//		}else {
//			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
//		}
	}
	
	public static void registerColors() {
		for(Item item : allItems) {
			if(item instanceof ItemCard) {
				ItemCard card = (ItemCard) item;
				Minecraft.getInstance().getItemColors().register(card, card);
			}
		}
	}

	public static void registerCustomModels() {
		for(CardType cType : CardType.values()) {
			ModelLoader.addSpecialModel(new ModelResourceLocation(cType.texture, "inventory"));
		}
		for(Suit suit : Suit.values()) {
			ModelLoader.addSpecialModel(new ModelResourceLocation(suit.texture, "inventory"));
		}
	}
}
