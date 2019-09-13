package irar.neorescards.crafting;

import irar.neorescards.Ref;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.TranslationTextComponent;

public class ItemGroups {
	
	public static ItemGroup FISH;
	public static ItemGroup COOKED_FISH;
	public static ItemGroup BOAT;
	public static ItemGroup SKULL;
	
	public static void init() {
		FISH = new ItemGroup(new TranslationTextComponent(Ref.MODID + ".item_group.fish"),
				new ItemStack(Items.COD),
				new ItemStack(Items.SALMON),
				new ItemStack(Items.TROPICAL_FISH),
				new ItemStack(Items.PUFFERFISH));
		COOKED_FISH = new ItemGroup(new TranslationTextComponent(Ref.MODID + ".item_group.cooked_fish"),
				new ItemStack(Items.COOKED_COD),
				new ItemStack(Items.COOKED_SALMON));
		BOAT = new ItemGroup(new TranslationTextComponent(Ref.MODID + ".item_group.boat"),
				new ItemStack(Items.ACACIA_BOAT),
				new ItemStack(Items.BIRCH_BOAT),
				new ItemStack(Items.DARK_OAK_BOAT),
				new ItemStack(Items.JUNGLE_BOAT),
				new ItemStack(Items.OAK_BOAT),
				new ItemStack(Items.SPRUCE_BOAT));
		SKULL = new ItemGroup(new TranslationTextComponent(Ref.MODID + ".item_group.skull"),
				new ItemStack(Items.SKELETON_SKULL),
				new ItemStack(Items.WITHER_SKELETON_SKULL),
				new ItemStack(Items.CREEPER_HEAD),
				new ItemStack(Items.DRAGON_HEAD),
				new ItemStack(Items.PLAYER_HEAD),
				new ItemStack(Items.ZOMBIE_HEAD));
	}

}
