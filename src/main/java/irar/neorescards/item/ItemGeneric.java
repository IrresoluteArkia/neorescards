package irar.neorescards.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import irar.neorescards.handlers.CreativeTabsHandler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemGeneric extends Item{
	public ItemGeneric(String name){
		super(new Properties());
		setRegistryName(name);
		NonNullList<ItemStack> creativeItems = NonNullList.<ItemStack>create();
		this.getSubItems(CreativeTabsHandler.CARDS, creativeItems);
		this.fillItemGroup(CreativeTabsHandler.CARDS, creativeItems);
	}

	@Override
	public Collection<ItemGroup> getCreativeTabs() {
		Collection<ItemGroup> group = super.getCreativeTabs();
		List<ItemGroup> groupnew = new ArrayList<>();
		groupnew.addAll(group);
		groupnew.add(CreativeTabsHandler.CARDS);
		return groupnew;
	}

	public void getSubItems(ItemGroup tabs, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

}
