package irar.neorescards.handlers;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabsHandler {
	public static ItemGroup CARDS = new ItemGroup("cards") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemHandler.CardSelector);
		}
		
	};
}
