package irar.neorescards.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.proxy.CommonProxy;
import irar.neorescards.world.CardData;
import irar.neorescards.world.PlayerCardData;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemIrresoluteCard extends ItemGeneric{

	public ItemIrresoluteCard(String name) {
		super(name);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn).copy();
		if(!worldIn.isRemote){
			if(!CommonProxy.saveData.isPlayerInList(playerIn) || !CommonProxy.saveData.getCardDataForPlayer(playerIn).hasCards()){
				StringTextComponent messagep1 = new StringTextComponent("[IrresoluteArkia]");
				StringTextComponent messagep2 = new StringTextComponent(" ...");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_PURPLE));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				playerIn.sendMessage(messagep1);
			}else{
				StringTextComponent messagep1 = new StringTextComponent("[IrresoluteArkia]");
				StringTextComponent messagep2 = new StringTextComponent(" There, I have freed you from the power of all those resolute card abominations!");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_PURPLE));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				playerIn.sendMessage(messagep1);
        		PlayerCardData playerData = CommonProxy.saveData.getCardDataForPlayer(playerIn);
        		List<CardData> cardData = playerData.getCardData();
        		for(int i = 0; i < cardData.size(); i++){
        			CardData data = cardData.get(i);
        			Card playerCard = data.getCard();
        			int tier = data.getTier();
        			ItemStack cardItem = ItemCard.getItemStackWithTierCardAndMetadata(playerCard, tier, 1, true);
        			worldIn.addEntity(new ItemEntity(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, cardItem));
        			data.removeEffect(playerIn);
        		}
				CommonProxy.saveData.removePlayer(playerIn);
//				playerIn.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
				if(stack.getCount() == 1){
					stack = ItemStack.EMPTY;
				}else{
					stack.setCount(stack.getCount() - 1);
				}
			}
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
		
		tooltip.add(new StringTextComponent("Warning: Will Convert Your Resolute Powers Back Into Card Form"));
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
