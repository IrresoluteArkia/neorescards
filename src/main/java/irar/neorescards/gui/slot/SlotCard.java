package irar.neorescards.gui.slot;

import irar.neorescards.item.ItemCard;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotCard extends Slot{

	public SlotCard(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if(stack.getItem() instanceof ItemCard) {
			return true;
		}
		return false;
	}
}
