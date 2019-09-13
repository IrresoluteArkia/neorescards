package irar.neorescards.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;

public class TagIngredient extends Ingredient {

	private Supplier<Tag<?>> tag;

	protected TagIngredient(Supplier<Tag<?>> tag) {
		super(Stream.of(new Ingredient.SingleItemList(ItemStack.EMPTY)));
		this.tag = tag;
	}

	@Override
	public ItemStack[] getMatchingStacks() {
		Tag<?> tag = this.tag.get();
		if(tag.getAllElements().size() < 1) {
			return new ItemStack[0];
		}
		Object testObj = tag.getRandomElement(new Random());
		List<ItemStack> stacks = new ArrayList<>();
		if(testObj instanceof Item) {
			for(Object obj : tag.getAllElements()) {
				stacks.add(new ItemStack((Item) obj));
			}
		}else if(testObj instanceof Block) {
			for(Object obj : tag.getAllElements()) {
				stacks.add(new ItemStack((Block) obj));
			}
		}
		return stacks.toArray(new ItemStack[0]);
	}

	@Override
	public boolean test(ItemStack p_test_1_) {
		ItemStack[] stacks = this.getMatchingStacks();
		if (p_test_1_ == null) {
	         return false;
	      } else if (stacks.length == 0) {
	         return p_test_1_.isEmpty();
	      } else {

	         for(ItemStack itemstack : stacks) {
	            if (itemstack.getItem() == p_test_1_.getItem()) {
	               return true;
	            }
	         }

	         return false;
	      }
	}
	
	

}
