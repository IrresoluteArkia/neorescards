package irar.neorescards.crafting;

import irar.neorescards.handlers.ItemHandler;
import irar.neorescards.item.ItemCardSelectorTiered;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CardCraftingRecipe implements IRecipe<CraftingInventory>{

	private ResourceLocation name = new ResourceLocation("card_crafting_recipe");

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
		boolean matches = true;
		int numSelectors = 0;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(inv.getStackInSlot(i).getItem() instanceof ItemCardSelectorTiered){
				numSelectors++;
			}
		}
		if(numSelectors != 2) {
			return false;
		}
		int targetTier = -50;
		for(int i = 1; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack.getItem() instanceof ItemCardSelectorTiered) {
				if(targetTier == -50) {
					targetTier = ItemCardSelectorTiered.getTier(stack);
				}else {
					if(!(ItemCardSelectorTiered.getTier(inv.getStackInSlot(i)) == targetTier)){
						matches = false;
					}
				}
			}
		}
		return matches;
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		int tier = -1;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(inv.getStackInSlot(i).getItem() instanceof ItemCardSelectorTiered){
				tier = ItemCardSelectorTiered.getTier(inv.getStackInSlot(i));
			}
		}
		return ItemCardSelectorTiered.getCardSelectorWithTier(tier + 1);
	}

	@Override
	public boolean canFit(int width, int height) {
		if(width == 2 && height == 2){
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ItemHandler.CardSelectorTiered);
	}

	@Override
	public ResourceLocation getId() {
		return this.name;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return IRecipeSerializer.CRAFTING_SHAPED;
	}

	@Override
	public IRecipeType<?> getType() {
		return IRecipeType.CRAFTING;
	}

}
