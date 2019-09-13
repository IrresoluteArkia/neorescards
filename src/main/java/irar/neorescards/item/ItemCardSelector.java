package irar.neorescards.item;

import java.util.ArrayList;

import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.handlers.CreativeTabsHandler;
import irar.neorescards.proxy.CommonProxy;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCardSelector extends ItemGeneric{
	public ItemCardSelector(String name){
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		if(!worldIn.isRemote){
			if(CommonProxy.saveData.isPlayerInList(playerIn)){
				StringTextComponent messagep1 = new StringTextComponent("[ResoluteArkia]");
				StringTextComponent messagep2 = new StringTextComponent(" ...");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				playerIn.sendMessage(messagep1);
			}else{
					Card selectedCard = Cards.getRandomCard();
					StringTextComponent messagep1 = new StringTextComponent("[ResoluteArkia]");
					StringTextComponent messagep2 = new StringTextComponent(" Be glad, " + playerIn.getName().getFormattedText() + "! The " + selectedCard.type.name + " of " + selectedCard.suit.name + " has seen fit to grant you its great power!");
					messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
					messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
					messagep1.appendSibling(messagep2);
					playerIn.sendMessage(messagep1);
					ItemStack stack = ItemCard.getItemStackWithTierCardAndMetadata(selectedCard, 1, 1, true);
					worldIn.addEntity(new ItemEntity(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, stack));
					CommonProxy.saveData.addPlayer(playerIn, null);
			}
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, ItemStack.EMPTY);
	}
	
}
