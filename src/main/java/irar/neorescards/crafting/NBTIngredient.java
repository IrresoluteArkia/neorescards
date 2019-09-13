package irar.neorescards.crafting;

import java.util.HashMap;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;

public class NBTIngredient extends Ingredient{
	
	private HashMap<String, Object> nbtMap = new HashMap<>();
	ItemStack item;
	
	public NBTIngredient(ItemStack stack, Object... nbtData) {
		super(Stream.of(new Ingredient.SingleItemList(ItemStack.EMPTY)));
		for(int i = 0; i < nbtData.length; i+=2) {
			String key = (String) nbtData[i];
			// currently supports ints and booleans
			Object value = nbtData[i+1];
			nbtMap.put(key, value);
		}
		item = stack;
	}
	
	@Override
	public ItemStack[] getMatchingStacks() {
		return new ItemStack[] {item};
	}

	@Override
	public boolean test(ItemStack stack) {
		boolean matches = super.test(stack);
		if(matches) {
			CompoundNBT nbt = stack.hasTag() ? stack.getTag() : stack.serializeNBT();
			for(String key : nbtMap.keySet()) {
				Object value = nbtMap.get(key);
				if(value instanceof Integer) {
					int i = (int) value;
					if(!(nbt.contains(key) && nbt.getInt(key) == i)) {
						matches = false;
					}
				}else if(value instanceof Boolean) {
					boolean b = (boolean) value;
					if(!(nbt.contains(key) && nbt.getBoolean(key) == b)) {
						matches = false;
					}
				}else {
					matches = false;
				}
			}
		}
		return matches;
	}

}
