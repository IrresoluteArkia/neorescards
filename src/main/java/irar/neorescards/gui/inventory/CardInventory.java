package irar.neorescards.gui.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.crafting.CardActivationRecipes;
import irar.neorescards.handlers.ItemHandler;
import irar.neorescards.item.ItemCardSelectorTiered;
import irar.neorescards.network.CardMessage;
import irar.neorescards.network.PacketHandler;
import irar.neorescards.proxy.CommonProxy;
import irar.neorescards.world.CardData;
import irar.neorescards.item.ItemCard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CardInventory implements IInventory{
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3 * 3, ItemStack.EMPTY);
    private String customName;
    private int tier;
    private PlayerEntity player;
	private boolean server;
	public static boolean isClear = false;
    
    public CardInventory(int tier, PlayerEntity player, boolean server){
    	isClear = false;
    	this.player = player;
    	this.tier = tier;
    	this.server = server;
    	this.setCustomName(tier == 1 ? "Select A Card To Gain The Powers Of:" : "Select A Card To Upgrade:");
    	if(!player.world.isRemote){
    		int k = 0;
    		List<Card> cardPool = new ArrayList<>();
    		if(tier == 1) {
    			List<Card> notInPool = CardData.cards(CommonProxy.saveData.getCardDataForPlayer(player).getCardData());
    			cardPool.addAll(Cards.allCards);
    			cardPool.removeAll(notInPool);
    		}else {
    			cardPool = CardData.cards(CommonProxy.saveData.getCardDataForPlayer(player).inTier(tier - 1));
    		}
    		List<Card> cantActivate = new ArrayList<>();
    		for(Card card : cardPool) {
    			if(CardActivationRecipes.getRecipe(card, tier) == null) {
    				cantActivate.add(card);
    			}
    		}
    		cardPool.removeAll(cantActivate);
    		List<Card> chosenCards = new ArrayList<>();
    		if(cardPool.size() <= 9) {
    			Collections.shuffle(cardPool);
    			chosenCards = cardPool;
    		}else {
	    		for(int i = 0; i < 9; i++) {
	    			Card card = cardPool.get(new Random().nextInt(cardPool.size()));
	    			chosenCards.add(card);
	    			cardPool.remove(card);
	    		}
    		}
	    	for(int i = 0; i < 3; i++){
		    	for(int j = 0; j < 3; j++){
		    		if(i * 3 + j >= chosenCards.size()) {
		    			break;
		    		}
		    		Card currentCard = chosenCards.get(i * 3 + j);
		    		ItemStack currentCardItem = ItemCard.getItemStackWithTierCardAndMetadata(currentCard, tier, 0, false);
		    		if(CommonProxy.saveData.hasCard(currentCard, player)){
			    		if(CommonProxy.saveData.hasCardInTier(currentCard, tier - 1, player)){
			    			this.setInventorySlotContents(k, currentCardItem);
			    		}
		    		}else if(tier == 1){
		    			this.setInventorySlotContents(k, currentCardItem);
		    		}
		    		k++;
		    	}
	    	}
    	}
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
   

	public String getName() {
	    return this.hasCustomName() ? this.customName : "card_inventory";
	}
	
	public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
	}

	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	public static int getSizeInventory(int i) {
		return 3 * 3;
	}

	@Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

	@Override
	public ItemStack getStackInSlot(int index) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return null;
	    return this.inventory.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventory, index, count);
        if(!server) {
        	sendMessage(itemstack);
        }
        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return ItemStack.EMPTY;

	}
	
	private void sendMessage(ItemStack itemstack) {
		if(itemstack.getItem() instanceof ItemCard && !isClear){
			PacketHandler.HANDLER.sendToServer(new CardMessage(Cards.allCards.indexOf(((ItemCard) itemstack.getItem()).getCard())));
		}
		this.clear();
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
	    ItemStack stack = this.getStackInSlot(index);
    	System.out.println(index);
	    this.setInventorySlotContents(index, null);
	    return stack;
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
	}

	public void setInventorySlotContents(int index, ItemStack stack, boolean unimportant) {
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		return true;
	}
	
	@Override
	public void openInventory(PlayerEntity player) {
		
	}

	@Override
	public void closeInventory(PlayerEntity player) {
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

//	@Override
//	public int getField(int id) {
//		return 0;
//	}
//
//	@Override
//	public void setField(int id, int value) {
//		
//	}
//
//	@Override
//	public int getFieldCount() {
//		return 0;
//	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
	        this.setInventorySlotContents(i, ItemStack.EMPTY);
		this.isClear  = true;
	}

	public ITextComponent getDisplayName() {
        return (ITextComponent)(this.hasCustomName() ? new StringTextComponent(this.getName()) : new TranslationTextComponent(this.getName(), new Object[0]));
	}

	@Override
	public void markDirty() {
		
	}

/*	@Deprecated
	public static CardInventory getInventoryFor(ItemStack stack) {
		if(stack.getItem() instanceof ItemCardSelectorTiered){
			System.out.println("Tier of inventory: " + ItemCardSelectorTiered.getTier(stack));
			return new CardInventory(ItemCardSelectorTiered.getTier(stack));
		}
		return new CardInventory(-1);
	}*/

}
