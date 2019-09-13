package irar.neorescards.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.ITextComponent;

public class ItemGroup {

	List<ItemStack> stacks = new ArrayList<>();
	private ITextComponent displayName;
	
	private ItemGroup(ItemStack... stacks) {
		for(ItemStack stack : stacks) {
			this.stacks.add(stack);
		}
	}
	
	public ItemGroup(ITextComponent name, ItemStack... stacks) {
		this(stacks);
		this.displayName = name;
	}
	
	public Ingredient asIngredient() {
		return Ingredient.fromStacks(stacks.toArray(new ItemStack[0]));
	}

	public ITextComponent getDisplayName() {
		return displayName;
	}

}
