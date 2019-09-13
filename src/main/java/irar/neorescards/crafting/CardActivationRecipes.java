package irar.neorescards.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import irar.neorescards.card.Card;
import irar.neorescards.card.CardType;
import irar.neorescards.card.Cards;
import irar.neorescards.card.Suit;
import irar.neorescards.item.ItemCard;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class CardActivationRecipes {

	private static List<CardActivationRecipe> recipes = new ArrayList<>();
	
	public static void init() {
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 1, 
				Items.STONE_SWORD, 3,
				"ingots/iron", 3, 
				Items.BONE, 10);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 2, 
				Items.IRON_SWORD, 3, 
				"ingots/gold", 3, 
				Items.BONE, 20);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 3, 
				Items.DIAMOND_SWORD, 2, 
				"ingots/gold", 10, 
				Items.BONE, 20, 
				Items.BLAZE_POWDER, 30);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 4, 
				Items.DIAMOND_SWORD, 4, 
				"gems/diamond", 7, 
				Items.BLAZE_POWDER, 65, 
				Blocks.OBSIDIAN, 10);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 5,
				Items.NETHER_STAR, 1, 
				"gems/diamond", 16, 
				Items.BLAZE_POWDER, 128, 
				Blocks.OBSIDIAN, 32);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 6, 
				Items.NETHER_STAR, 3, 
				"gems/diamond", 32, 
				Items.BREWING_STAND, 96, 
				Blocks.OBSIDIAN, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 7, 
				Items.NETHER_STAR, 8, 
				"gems/diamond", 48, 
				Items.BREWING_STAND, 128, 
				Blocks.OBSIDIAN, 64, 
				Blocks.GLOWSTONE, 132);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 8, 
				Items.NETHER_STAR, 8, 
				"gems/diamond", 48, 
				Items.BREWING_STAND, 128, 
				Blocks.OBSIDIAN, 64, 
				Blocks.GLOWSTONE, 132);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 9, 
				Items.NETHER_STAR, 8, 
				"gems/diamond", 48, 
				Items.BREWING_STAND, 128, 
				Blocks.OBSIDIAN, 64, 
				Blocks.GLOWSTONE, 132);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 10, 
				Items.NETHER_STAR, 8, 
				"gems/diamond", 48, 
				Items.BREWING_STAND, 128, 
				Blocks.OBSIDIAN, 64, 
				Blocks.GLOWSTONE, 132);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.TWO), 1, 
				Items.GLOWSTONE_DUST, 10,
				Blocks.TORCH, 10,
				Items.REDSTONE, 10,
				Blocks.REDSTONE_TORCH, 10);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.THREE), 1,
				Items.GOLDEN_CARROT, 16,
				Items.DIAMOND, 4,
				new NBTIngredient(
						ItemCard.getItemStackWithTierCardAndMetadata(
								Cards.fromSuitAndType(Suit.CLUB, CardType.TWO), 
								1, 1, true),
						"TIER", 1,
						"ACTIVATED", true), 
				1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 1,
				Items.RABBIT_HIDE, 1,
				Items.GOLD_INGOT, 1,
				Items.IRON_INGOT, 4);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 2,
				Items.RABBIT_HIDE, 2,
				Items.GOLD_INGOT, 2,
				Items.IRON_INGOT, 6);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 3,
				Items.RABBIT_HIDE, 8,
				Items.GOLD_INGOT, 8,
				Items.IRON_INGOT, 9);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 4,
				Items.RABBIT_HIDE, 16,
				Items.GOLD_INGOT, 17,
				Blocks.GOLD_BLOCK, 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 5,
				Items.RABBIT_HIDE, 32,
				Items.GOLD_INGOT, 32,
				Blocks.GOLD_BLOCK, 4,
				Items.NETHER_STAR, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 6,
				Items.RABBIT_HIDE, 64,
				Items.GOLD_INGOT, 64,
				Blocks.GOLD_BLOCK, 8,
				Items.NETHER_STAR, 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 7,
				Items.RABBIT_HIDE, 128,
				Items.GOLD_INGOT, 68,
				Blocks.GOLD_BLOCK, 12,
				Items.NETHER_STAR, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 8,
				Items.RABBIT_HIDE, 128,
				Items.GOLD_INGOT, 68,
				Blocks.GOLD_BLOCK, 12,
				Items.NETHER_STAR, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 9,
				Items.RABBIT_HIDE, 128,
				Items.GOLD_INGOT, 68,
				Blocks.GOLD_BLOCK, 12,
				Items.NETHER_STAR, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FOUR), 10,
				Items.RABBIT_HIDE, 128,
				Items.GOLD_INGOT, 68,
				Blocks.GOLD_BLOCK, 12,
				Items.NETHER_STAR, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FIVE), 1,
				ItemGroups.FISH, 10,
				Items.FISHING_ROD, 10,
				ItemGroups.COOKED_FISH, 10,
				ItemGroups.BOAT, 11,
				Blocks.TNT, 1,
				Blocks.REDSTONE_BLOCK, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.SIX), 1,
				Blocks.GLASS, 32,
				Items.ENDER_PEARL, 4,
				Items.FEATHER, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.SEVEN), 1,
				Blocks.OBSIDIAN, 32,
				Items.SPIDER_EYE, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.EIGHT), 1,
				Items.GOLDEN_APPLE, 3,
				Items.ROTTEN_FLESH, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.EIGHT), 2,
				Items.GOLDEN_APPLE, 6,
				Items.ROTTEN_FLESH, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.EIGHT), 3,
				Items.GOLDEN_APPLE, 9,
				Items.ROTTEN_FLESH, 32,
				Items.FERMENTED_SPIDER_EYE, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.EIGHT), 4,
				Items.GOLDEN_APPLE, 16,
				Items.GHAST_TEAR, 9,
				Items.ROTTEN_FLESH, 64,
				Items.FERMENTED_SPIDER_EYE, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.EIGHT), 5,
				Items.GOLDEN_APPLE, 18,
				Items.GHAST_TEAR, 16,
				Items.GLISTERING_MELON_SLICE, 16,
				Items.ROTTEN_FLESH, 64,
				Items.FERMENTED_SPIDER_EYE, 20);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.EIGHT), 6,
				Items.GOLDEN_APPLE, 32,
				Items.GHAST_TEAR, 32,
				Items.GLISTERING_MELON_SLICE, 32,
				Items.ROTTEN_FLESH, 64,
				Items.FERMENTED_SPIDER_EYE, 32,
				Items.NETHER_STAR, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.EIGHT), 7,
				Items.GOLDEN_APPLE, 64,
				Items.GHAST_TEAR, 64,
				Items.GLISTERING_MELON_SLICE, 64,
				Items.ROTTEN_FLESH, 64,
				Items.FERMENTED_SPIDER_EYE, 64,
				Items.NETHER_STAR, 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.NINE), 1,
				Items.GOLDEN_APPLE, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.NINE), 2,
				Items.GOLDEN_APPLE, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.NINE), 3,
				Items.GOLDEN_APPLE, 32);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.NINE), 4,
				Items.GOLDEN_APPLE, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.NINE), 5,
				Items.GOLDEN_APPLE, 128);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.TEN), 1,
				ItemGroups.SKULL, 1,
				Items.FEATHER, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.TEN), 2,
				ItemGroups.SKULL, 2,
				Items.FEATHER, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.TEN), 3,
				ItemGroups.SKULL, 3,
				Items.FEATHER, 24);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.TEN), 4,
				ItemGroups.SKULL, 6,
				Items.FEATHER, 32);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.TEN), 5,
				ItemGroups.SKULL, 9,
				Items.FEATHER, 64,
				Items.NETHER_STAR, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.JACK), 1,
				Items.STONE_SWORD, 3,
				"ingots/iron", 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.JACK), 2,
				Items.IRON_SWORD, 3, 
				"ingots/gold", 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.JACK), 3,
				Items.DIAMOND_SWORD, 1, 
				"ingots/gold", 10,
				Items.BLAZE_POWDER, 20);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.QUEEN), 1,
				Items.GOLDEN_APPLE, 1,
				Items.STONE_SWORD, 2);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.QUEEN), 2,
				Items.GOLDEN_APPLE, 2,
				Items.GHAST_TEAR, 2,
				Items.IRON_SWORD, 2);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.QUEEN), 3,
				Items.GOLDEN_APPLE, 4,
				Items.GHAST_TEAR, 3,
				Items.DIAMOND_SWORD, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.QUEEN), 4,
				Items.GOLDEN_APPLE, 8,
				Items.GHAST_TEAR, 8,
				Items.DIAMOND_SWORD, 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.QUEEN), 5,
				Items.GOLDEN_APPLE, 16,
				Items.GHAST_TEAR, 12,
				Items.GLISTERING_MELON_SLICE, 8,
				Items.DIAMOND_SWORD, 6);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.QUEEN), 6,
				Items.GOLDEN_APPLE, 24,
				Items.GHAST_TEAR, 16,
				Items.GLISTERING_MELON_SLICE, 16,
				Items.DIAMOND_SWORD, 9);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.QUEEN), 7,
				Items.GOLDEN_APPLE, 32,
				Items.GHAST_TEAR, 32,
				Items.GLISTERING_MELON_SLICE, 32,
				Items.NETHER_STAR, 1,
				Items.DIAMOND_SWORD, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.KING), 1,
				"gems/diamond", 10,
				Items.REDSTONE, 24,
				Items.COAL, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.KING), 2,
				"gems/diamond", 20,
				Items.REDSTONE, 64,
				Items.COAL, 128);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.KING), 3,
				"gems/diamond", 30,
				Items.REDSTONE, 128,
				Items.COAL, 256);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FE), 1,
				Items.SPIDER_EYE, 4,
				Items.GLOWSTONE_DUST, 2);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FE), 2,
				Items.SPIDER_EYE, 8,
				Items.GLOWSTONE_DUST, 16,
				Items.MILK_BUCKET, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FE), 3,
				Items.SPIDER_EYE, 16,
				Items.GLOWSTONE_DUST, 32,
				Items.MILK_BUCKET, 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FE), 4,
				Items.SPIDER_EYE, 32,
				Items.GLOWSTONE_DUST, 64,
				Items.MILK_BUCKET, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.FE), 5,
				Items.SPIDER_EYE, 64,
				Items.GLOWSTONE_DUST, 128,
				Items.NETHER_STAR, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.UKD), 1,
				Items.GLOWSTONE_DUST, 16,
				new NBTIngredient(
						ItemCard.getItemStackWithTierCardAndMetadata(
								Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 
								1, 1, true),
						"TIER", 1,
						"ACTIVATED", true), 
				1,
				new NBTIngredient(
						ItemCard.getItemStackWithTierCardAndMetadata(
								Cards.fromSuitAndType(Suit.SPADE, CardType.EIGHT), 
								1, 1, true),
						"TIER", 1,
						"ACTIVATED", true), 
				1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.UKD), 2,
				Items.GLOWSTONE_DUST, 32,
				new NBTIngredient(
						ItemCard.getItemStackWithTierCardAndMetadata(
								Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 
								2, 1, true),
						"TIER", 2,
						"ACTIVATED", true), 
				1,
				new NBTIngredient(
						ItemCard.getItemStackWithTierCardAndMetadata(
								Cards.fromSuitAndType(Suit.SPADE, CardType.EIGHT), 
								1, 1, true),
						"TIER", 1,
						"ACTIVATED", true), 
				1);
		
		addRecipe(Cards.fromSuitAndType(Suit.CLUB, CardType.UKD), 3,
				Items.GLOWSTONE_DUST, 64,
				new NBTIngredient(
						ItemCard.getItemStackWithTierCardAndMetadata(
								Cards.fromSuitAndType(Suit.CLUB, CardType.ACE), 
								3, 1, true),
						"TIER", 3,
						"ACTIVATED", true), 
				1,
				new NBTIngredient(
						ItemCard.getItemStackWithTierCardAndMetadata(
								Cards.fromSuitAndType(Suit.SPADE, CardType.EIGHT), 
								1, 1, true),
						"TIER", 1,
						"ACTIVATED", true), 
				1);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.ACE), 1,
				Blocks.OBSIDIAN, 64,
				Blocks.STONE, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.ACE), 2,
				Blocks.OBSIDIAN, 256,
				Blocks.STONE, 256,
				Blocks.END_STONE, 128,
				Items.NETHER_STAR, 2);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.TWO), 1,
				Blocks.IRON_BLOCK, 3,
				Blocks.STONE, 32,
				"gems/diamond", 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.THREE), 1,
				Items.GOLDEN_APPLE, 8,
				Items.GLOWSTONE_DUST, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.THREE), 2,
				Items.GOLDEN_APPLE, 16,
				Items.GLOWSTONE_DUST, 32);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FOUR), 1,
				Items.GOLDEN_APPLE, 8,
				Items.GHAST_TEAR, 2,
				Items.GLISTERING_MELON_SLICE, 4,
				Items.SPIDER_EYE, 4,
				Items.FERMENTED_SPIDER_EYE, 2);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FOUR), 2,
				Items.GOLDEN_APPLE, 16,
				Items.GHAST_TEAR, 8,
				Items.GLISTERING_MELON_SLICE, 8,
				Items.SPIDER_EYE, 12,
				Items.FERMENTED_SPIDER_EYE, 6);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FOUR), 3,
				Items.GOLDEN_APPLE, 28,
				Items.GHAST_TEAR, 16,
				Items.GLISTERING_MELON_SLICE, 16,
				Items.SPIDER_EYE, 24,
				Items.FERMENTED_SPIDER_EYE, 17);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FOUR), 4,
				Items.GOLDEN_APPLE, 34,
				Items.GHAST_TEAR, 34,
				Items.GLISTERING_MELON_SLICE, 34,
				Items.SPIDER_EYE, 34,
				Items.FERMENTED_SPIDER_EYE, 24);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FIVE), 1,
				Items.SUGAR, 16,
				Items.REDSTONE, 16,
				Items.GLOWSTONE_DUST, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FIVE), 2,
				Items.SUGAR, 32,
				Items.REDSTONE, 32,
				Items.GLOWSTONE_DUST, 32);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FIVE), 3,
				Items.SUGAR, 64,
				Items.REDSTONE, 64,
				Items.GLOWSTONE_DUST, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FIVE), 4,
				Items.SUGAR, 96,
				Items.REDSTONE, 96,
				Items.GLOWSTONE_DUST, 96,
				Items.ENDER_PEARL, 16);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FIVE), 5,
				Items.SUGAR, 96,
				Items.REDSTONE, 96,
				Items.GLOWSTONE_DUST, 96,
				Items.ENDER_PEARL, 24,
				Items.NETHER_STAR, 1);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FIVE), 6,
				Items.SUGAR, 128,
				Items.REDSTONE, 128,
				Items.GLOWSTONE_DUST, 128,
				Items.ENDER_PEARL, 32,
				Items.NETHER_STAR, 3);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.FIVE), 7,
				Items.SUGAR, 128,
				Items.REDSTONE, 128,
				Items.GLOWSTONE_DUST, 128,
				Items.ENDER_PEARL, 48,
				Items.NETHER_STAR, 8);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.KING), 1,
				"gems/diamond", 10,
				Items.REDSTONE, 24,
				Items.COAL, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.KING), 2,
				"gems/diamond", 20,
				Items.REDSTONE, 64,
				Items.COAL, 128);
		
		addRecipe(Cards.fromSuitAndType(Suit.DIAMOND, CardType.KING), 3,
				"gems/diamond", 30,
				Items.REDSTONE, 128,
				Items.COAL, 256);
		
		addRecipe(Cards.fromSuitAndType(Suit.SPADE, CardType.KING), 1,
				"gems/diamond", 10,
				Items.REDSTONE, 24,
				Items.COAL, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.SPADE, CardType.KING), 2,
				"gems/diamond", 20,
				Items.REDSTONE, 64,
				Items.COAL, 128);
		
		addRecipe(Cards.fromSuitAndType(Suit.SPADE, CardType.KING), 3,
				"gems/diamond", 30,
				Items.REDSTONE, 128,
				Items.COAL, 256);
		
		addRecipe(Cards.fromSuitAndType(Suit.HEART, CardType.KING), 1,
				"gems/diamond", 10,
				Items.REDSTONE, 24,
				Items.COAL, 64);
		
		addRecipe(Cards.fromSuitAndType(Suit.HEART, CardType.KING), 2,
				"gems/diamond", 20,
				Items.REDSTONE, 64,
				Items.COAL, 128);
		
		addRecipe(Cards.fromSuitAndType(Suit.HEART, CardType.KING), 3,
				"gems/diamond", 30,
				Items.REDSTONE, 128,
				Items.COAL, 256);
		
	}
	
	public static void addRecipe(Card card, int tier, Object... ingredients) {
		CardActivationRecipe recipe = new CardActivationRecipe(card, tier, ingredients);
		recipes.add(recipe);
	}
	
	@Nullable
	public static CardActivationRecipe getRecipe(Card card, int tier) {
		for(CardActivationRecipe recipe : recipes) {
			if(recipe.matches(card, tier)) {
				return recipe;
			}
		}
		return null;
	}
	
}
