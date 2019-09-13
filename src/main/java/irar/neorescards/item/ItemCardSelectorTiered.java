package irar.neorescards.item;

import java.util.List;

import javax.annotation.Nullable;

import irar.neorescards.NeoresCards;
import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.gui.container.ContainerCardInventory;
import irar.neorescards.gui.inventory.CardInventory;
import irar.neorescards.handlers.ItemHandler;
import irar.neorescards.network.GuiHandler;
import irar.neorescards.proxy.CommonProxy;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCardSelectorTiered extends ItemGeneric{

	public ItemCardSelectorTiered(String name) {
		super(name);
	}
	
	@Override
	public void getSubItems(ItemGroup tabs, NonNullList<ItemStack> items) {
		for(int i = 0; i < 10; i++) {
			items.add(getCardSelectorWithTier(i + 1));
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote){
			playerIn.openContainer(new INamedContainerProvider() {
				@Override
				public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
						PlayerEntity p_createMenu_3_) {
					return new ContainerCardInventory(p_createMenu_2_, new CardInventory(getTier(stack), p_createMenu_3_, true));
				}

				@Override
				public ITextComponent getDisplayName() {
					return new StringTextComponent("test inv 2");
				}
			});
			stack.shrink(1);
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	public static ItemStack getCardSelectorWithTier(int tier){
		ItemStack itemstack = new ItemStack(ItemHandler.CardSelectorTiered, 1);
		itemstack.setTag(new CompoundNBT());
		itemstack.getTag().putInt("TIER", tier);
//		System.out.println("Adding tier to itemstack");
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
		
		if(stack.hasTag() && stack.getTag().contains("TIER")){
			int tier = stack.getTag().getInt("TIER");
			tooltip.add(new StringTextComponent("Tier " + tier));
		}else{
			tooltip.add(new StringTextComponent("Invalid Tier: please get this item through proper methods e.g. the crafting recipe"));
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public static int getTier(ItemStack stack) {
		int tier = -1;
		if(stack.hasTag() && stack.getTag().contains("TIER")){
			tier = stack.getTag().getInt("TIER");
		}
		return tier;
	}
	
}
