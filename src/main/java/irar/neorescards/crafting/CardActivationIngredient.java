package irar.neorescards.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class CardActivationIngredient {

	private final Ingredient ingredient;
	private final int count;
	private ITextComponent displayName;
	private boolean nameSet = false;
	private static final ITextComponent ERROR = new StringTextComponent("ERROR: Empty Ingredient");

	public CardActivationIngredient(Ingredient ingredient, int count) {
		this.ingredient = ingredient;
		this.count = count;
		if(ingredient.getMatchingStacks().length > 0) {
			this.displayName = ingredient.getMatchingStacks()[0].getDisplayName();
		}else {
			this.displayName = ERROR;
		}
	}

	public CardActivationIngredient(ItemGroup group, int count) {
		this(group.asIngredient(), count);
		this.nameSet  = true;
		this.displayName = group.getDisplayName();
	}

	public int getAmountRequired() {
		return count;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public ITextComponent getDisplayName() {
		if(nameSet) {
			return displayName;
		}
		ItemStack[] stacks = ingredient.getMatchingStacks();
		if(stacks.length > 0) {
			return ingredient.getMatchingStacks()[0].getDisplayName();
		}
		return ERROR;
	}

}
