package irar.neorescards.gui.container;

import javax.annotation.Nullable;

import irar.neorescards.gui.inventory.CardInventory;
import irar.neorescards.gui.slot.SlotUnusable;
import irar.neorescards.network.GuiHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCardInventory extends Container{

	private CardInventory ci;
	
	public ContainerCardInventory(PlayerInventory playerInv, CardInventory tileEntityOreGen){
		super(GuiHandler.CARDINV, 0);
		this.ci = tileEntityOreGen;
		 // Tile Entity, Slot 0-8, Slot IDs 0-8
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 3; ++x) {
	            this.addSlot(new SlotUnusable(tileEntityOreGen, x + y * 3, 98 + x * 18, 26 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlot(new Slot(playerInv, x + y * 9 + 9, 40 + x * 18, 102 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int x = 0; x < 9; ++x) {
	        this.addSlot(new Slot(playerInv, x, 40 + x * 18, 160));
	    }
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.ci.isUsableByPlayer(playerIn);
	}

	@Nullable
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int fromSlot) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        return itemstack;
	}
	
}
