package irar.neorescards.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import irar.neorescards.Ref;
import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.card.effect.ICardEffect;
import irar.neorescards.crafting.CardActivationIngredient;
import irar.neorescards.crafting.CardActivationRecipe;
import irar.neorescards.crafting.CardActivationRecipes;
import irar.neorescards.handlers.CreativeTabsHandler;
import irar.neorescards.handlers.ItemHandler;
import irar.neorescards.proxy.CommonProxy;
import irar.neorescards.util.ChatHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemCard extends ItemGeneric implements IItemColor{
	
	private Card card;
	
	public ItemCard(String name, Card card){
		super(name);
		this.card = card;
	}
	
	@Override
	public void getSubItems(ItemGroup tabs, NonNullList<ItemStack> items) {
		items.add(getItemStackWithTierCardAndMetadata(card, 1, 1, true));
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		Card card = ((ItemCard) stack.getItem()).getCard();
		if(card != null) {
			return new TranslationTextComponent("item." + Ref.MODID + ".card_base", 
					new TranslationTextComponent(Ref.MODID + "." + card.type.id),
					new TranslationTextComponent(Ref.MODID + "." + card.suit.id));
//			return new StringTextComponent("The " + type + " of " + suit);
		}
		return new StringTextComponent("");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote){
			Card selectedCard = ((ItemCard) stack.getItem()).card;
			int tier = ItemCard.getTierFromItemStack(stack);
			if(getActivated(stack)) {
				if(!CommonProxy.saveData.isPlayerInList(playerIn)){
					CommonProxy.saveData.addPlayer(playerIn, selectedCard);
					for(int i = 0; i < tier - 1; i++) {
						CommonProxy.saveData.upgradeCard(selectedCard, playerIn);
					}
					stack.shrink(1);
				}else{
					boolean consumed = false;
					if(!CommonProxy.saveData.hasCard(card, playerIn)) {
						CommonProxy.saveData.setCardInTier(card, tier, playerIn);
						consumed = true;
					}
					if(consumed) {
						stack.shrink(1);
					}
				}
			}else {
				CardActivationRecipe recipe = CardActivationRecipes.getRecipe(selectedCard, tier);
				if(recipe != null) {
					if(!recipe.canActivate(playerIn.inventory)) {
						playerIn.sendMessage(ChatHelper.formatResoluteArkia("This card is not yet activated"));
						List<CardActivationIngredient> ingredients = recipe.getIngredients();
						playerIn.sendMessage(ChatHelper.formatResoluteArkia("To activate it, you need:"));
						for(CardActivationIngredient ingredient : ingredients) {
							playerIn.sendMessage(ChatHelper.formatResoluteArkia("" + TextFormatting.ITALIC + ingredient.getAmountRequired() + " " + ingredient.getDisplayName().getFormattedText()));
						}
					}else {
						recipe.consumeActivationItems(playerIn.inventory);
						if(stack.getCount() > 1) {
							stack.shrink(1);
							ItemStack newStack = stack.copy();
							newStack.setCount(1);
							setActivated(newStack, true);
							worldIn.addEntity(new ItemEntity(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, newStack));
						}else {
							setActivated(stack, true);
						}
						playerIn.sendMessage(ChatHelper.formatResoluteArkia("You have activated the " + stack.getDisplayName().getFormattedText() + ", now you can gain its power"));
					}
				}else {
					playerIn.sendMessage(ChatHelper.formatResoluteArkia("This card is not yet activated"));
					playerIn.sendMessage(ChatHelper.formatResoluteArkia("There is no known way to activate it"));
				}
			}
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
	private static void setActivated(ItemStack stack, boolean b) {
		CompoundNBT tag = stack.hasTag() ? stack.getTag() : stack.serializeNBT();
		tag.putBoolean("ACTIVATED", b);
	}
	

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
		
		if(stack.hasTag() && stack.getTag().contains("TIER")){
			int tier = stack.getTag().getInt("TIER");
			if(getMetadata(stack) == 0) {
				tooltip.add(new StringTextComponent(tier == 1 ? "Gain This Card" : "Upgrade Card To Level " + tier));
				if(!Screen.hasShiftDown() || Screen.hasControlDown()) {
					tooltip.add(new StringTextComponent(""));
					if(tier != 1) {
						tooltip.add(new StringTextComponent("" + TextFormatting.DARK_RED + TextFormatting.ITALIC + "You will temporarily lose the powers of"));
						tooltip.add(new StringTextComponent("" + TextFormatting.DARK_RED + TextFormatting.ITALIC + "this card and must reactivate it"));
					}else {
						tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GREEN + TextFormatting.ITALIC + "You must activate the card"));
						tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GREEN + TextFormatting.ITALIC + "before gaining its powers"));
					}
				}
				tooltip.add(new StringTextComponent(""));
			}else if(getMetadata(stack) == 1) {
				tooltip.add(new StringTextComponent("Level " + tier));
			}
			for(int i = 0; i < card.cardEffects.size(); i++) {
				ICardEffect effect = card.cardEffects.get(i);
				effect.addEffectInfo(stack, worldIn, tooltip, flagIn, tier);
			}
			tooltip.add(new StringTextComponent(""));
			if(getActivated(stack)) {
				tooltip.add(new StringTextComponent("" + TextFormatting.BLUE + TextFormatting.BOLD + "Activated"));
				tooltip.add(new StringTextComponent("" + TextFormatting.BLUE + TextFormatting.BOLD + "Right Click to Gain Powers"));
			}else {
				Card card = ((ItemCard) stack.getItem()).card;
				tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GRAY + TextFormatting.BOLD + "Deactivated"));
				if(Screen.hasShiftDown()) {
					CardActivationRecipe recipe = CardActivationRecipes.getRecipe(card, tier);
					if(recipe != null) {
						List<CardActivationIngredient> ingredients = recipe.getIngredients();
						tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GRAY + TextFormatting.ITALIC + "To activate it, you need:"));
						for(CardActivationIngredient ingredient : ingredients) {
							tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GRAY + TextFormatting.ITALIC + ingredient.getAmountRequired() + " " + ingredient.getDisplayName().getFormattedText()));
						}
					}else {
						tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GRAY + TextFormatting.ITALIC + "There is no known way to activate it"));
					}
				}else {
					tooltip.add(new StringTextComponent("" + TextFormatting.DARK_GRAY + TextFormatting.ITALIC + "Hold Shift to See Activation Requirements"));
				}
			}
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	private int getMetadata(ItemStack stack) {
		if(stack.hasTag() && stack.getTag().contains("card_meta")) {
			return stack.getTag().getInt("card_meta");
		}
		return 0;
	}

	public Card getCard(){
		return this.card;
	}
	
	public static int getTierFromItemStack(ItemStack stack) {
		if(stack.hasTag()) {
			CompoundNBT tag = stack.getTag();
			if(tag.contains("TIER")) {
				return tag.getInt("TIER");
			}
		}
		return 0;
	}

	public static boolean getActivated(ItemStack stack) {
		if(stack.hasTag()) {
			CompoundNBT tag = stack.getTag();
			if(tag.contains("ACTIVATED")) {
				return tag.getBoolean("ACTIVATED");
			}
		}
		return false;
	}

	public ItemStack getItemStackWithTier(int tier, boolean activated) {
		ItemStack itemstack = new ItemStack(this, 1);
		itemstack.setTag(new CompoundNBT());
		itemstack.getTag().putInt("TIER", tier);
		itemstack.getTag().putBoolean("ACTIVATED", activated);
		return itemstack;
	}
	
	public static ItemStack getItemStackWithTierCardAndMetadata(Card card, int tier, int meta, boolean activated) {
		ItemCard target = null;
		for(Item item : ItemHandler.allItems) {
			if(item instanceof ItemCard) {
				ItemCard carditem = (ItemCard) item;
				if(carditem.getCard().equals(card)) {
					target = carditem;
				}
			}
		}
		
		if(target != null) {
			ItemStack stack = target.getItemStackWithTier(tier, activated);
			setMetadata(stack, meta);
			return stack;
		}
		return ItemStack.EMPTY;
	}

	private static void setMetadata(ItemStack stack, int meta) {
		CompoundNBT nbt = stack.hasTag() ? stack.getTag() : new CompoundNBT();
		nbt.putInt("card_meta", meta);
		stack.setTag(nbt);
	}

//	@Override
//	public ModelResourceLocation getModelLocation(ItemStack stack) {
//		ModelResourceLocation loc;
//		if(GuiScreen.isShiftKeyDown()) {
//			loc = new ModelResourceLocation(((ItemCard) stack.getItem()).card.suit.texture, "inventory");
//		}else {
//			loc = new ModelResourceLocation(((ItemCard) stack.getItem()).card.type.texture, "inventory");
//		}
//		return loc;
//	}

	@Override
	public int getColor(ItemStack stack, int tintIndex) {
        return tintIndex > 0 ? -1 : getColor(stack);
	}

	public static int getColor(ItemStack stack) {
		return ((ItemCard) stack.getItem()).card.suit.color;
	}}
