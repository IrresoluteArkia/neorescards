package irar.neorescards.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import irar.neorescards.card.Card;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class CardActivationRecipe {

	public final Card card;
	public final int tier;
	protected List<CardActivationIngredient> ingredients;
//	protected HashMap<Ingredient, Integer> ingredients;
	
	CardActivationRecipe(Card card, int tier, Object... ingredients) {
		this.card = card;
		this.tier = tier;
		this.ingredients = new ArrayList<>();
		convertIngredients(this.ingredients, ingredients);
	}

	private void convertIngredients(List<CardActivationIngredient> ingredients, Object[] toConvert) {
		for(int i = 0; i < toConvert.length; i += 2) {
			Object o = toConvert[i];
			int count = (int) toConvert[i+1];
			if(o instanceof Ingredient) {
				ingredients.add(new CardActivationIngredient((Ingredient) o, count));
			}else if(o instanceof Block) {
				o = new ItemStack((Block) o);
			}else if(o instanceof Item) {
				o = new ItemStack((Item) o);
			}else if(o instanceof ItemGroup) {
				ingredients.add(new CardActivationIngredient((ItemGroup) o, count));
			}else if(o instanceof String) {
				String tag = (String) o;
				ResourceLocation tagLoc;
				if(tag.contains(":")) {
					String[] split = tag.split(":");
					tagLoc = new ResourceLocation(split[0], split[1]);
				}else {
					tagLoc = new ResourceLocation("forge", tag);
				}
//				if(ItemTags.getCollection().getOrCreate(tagLoc).getAllElements().size() < 1) {
//					ingredients.add(new CardActivationIngredient(new TagIngredient(() -> BlockTags.getCollection().getOrCreate(tagLoc)), count));
//				}else {
					ingredients.add(new CardActivationIngredient(new TagIngredient(() -> ItemTags.getCollection().getOrCreate(tagLoc)), count));
//				}
			}
			if(o instanceof ItemStack) {
				ingredients.add(new CardActivationIngredient(Ingredient.fromStacks((ItemStack) o), count));
			}
		}
	}
	
	public boolean matches(Card card, int tier) {
		return this.card.equals(card) && this.tier == tier;
	}
	
	public List<CardActivationIngredient> getIngredients(){
		List<CardActivationIngredient> ingredients = new ArrayList<>();
		ingredients.addAll(this.ingredients);
		return ingredients;
	}

	public boolean canActivate(IInventory inventory) {
		List<CardActivationIngredient> ingredients = getIngredients();
		List<ItemStack> inventoryCopy = getInventoryCopy(inventory);
		for(CardActivationIngredient ingredient : ingredients) {
			int amountRequired = ingredient.getAmountRequired();
			for(int i = 0; i < inventoryCopy.size(); i++) {
				ItemStack stack = inventoryCopy.get(i);
				if(ingredient.getIngredient().test(stack)) {
					int amountAvailable = stack.getCount();
					if(amountAvailable <= amountRequired) {
						amountRequired -= amountAvailable;
						inventoryCopy.set(i, ItemStack.EMPTY);
					}else {
						amountAvailable -= amountRequired;
						stack.setCount(amountAvailable);
						amountRequired = 0;
					}
					if(amountRequired == 0) {
						break;
					}
				}
			}
			if(amountRequired != 0) {
				return false;
			}
		}
		return true;
	}

	private List<ItemStack> getInventoryCopy(IInventory inventory) {
		List<ItemStack> inventoryCopy = new ArrayList<>();
		for(int i = 0; i < inventory.getSizeInventory(); i++) {
			inventoryCopy.add(inventory.getStackInSlot(i).copy());
		}
		return inventoryCopy;
	}

	public void consumeActivationItems(IInventory inventory) {
		List<CardActivationIngredient> ingredients = getIngredients();
		for(CardActivationIngredient ingredient : ingredients) {
			int amountRequired = ingredient.getAmountRequired();
			for(int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack stack = inventory.getStackInSlot(i);
				if(ingredient.getIngredient().test(stack)) {
					int amountAvailable = stack.getCount();
					if(amountAvailable <= amountRequired) {
						amountRequired -= amountAvailable;
						inventory.setInventorySlotContents(i, ItemStack.EMPTY);
					}else {
						amountAvailable -= amountRequired;
						stack.setCount(amountAvailable);
						inventory.setInventorySlotContents(i, stack);
						amountRequired = 0;
					}
					if(amountRequired == 0) {
						break;
					}
				}
			}
		}
	}

}
