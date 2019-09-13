package irar.neorescards.crafting;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class CardActivationIngredient {

	private final Ingredient ingredient;
	private final int count;
	private ITextComponent displayName;

	public CardActivationIngredient(Ingredient ingredient, int count) {
		this.ingredient = ingredient;
		this.count = count;
		if(ingredient.getMatchingStacks().length > 0) {
			this.displayName = ingredient.getMatchingStacks()[0].getDisplayName();
		}else {
			this.displayName = new StringTextComponent("ERROR: Empty Ingredient");
		}
	}

	public CardActivationIngredient(ItemGroup group, int count) {
		this(group.asIngredient(), count);
		this.displayName = group.getDisplayName();
	}

	public int getAmountRequired() {
		return count;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public ITextComponent getDisplayName() {
		return displayName;
	}

}
