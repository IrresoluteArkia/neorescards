package irar.neorescards.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import irar.neorescards.NeoresCards;
import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.card.effect.ICardEffect;
import irar.neorescards.crafting.CardActivationRecipes;
import irar.neorescards.crafting.ItemGroups;
import irar.neorescards.handlers.CraftingHandler;
import irar.neorescards.handlers.ItemHandler;
import irar.neorescards.handlers.MainEventHandler;
import irar.neorescards.handlers.PotionHandler;
import irar.neorescards.network.CardMessage;
import irar.neorescards.network.CardMessageHandler;
import irar.neorescards.network.GuiHandler;
import irar.neorescards.network.PCDMessage;
import irar.neorescards.network.PCDMessageHandler;
import irar.neorescards.network.PacketHandler;
import irar.neorescards.util.PH;
import irar.neorescards.world.PlayerCardData;
import irar.neorescards.world.SaveDataHandler;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.main.Main;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class CommonProxy implements IProxy{
	
	public static SaveDataHandler saveData = new SaveDataHandler();
//	public static IForgeRegistry<IRecipe> recipeRegistry;
	public static World world;

	@Override
	public void preInit(FMLCommonSetupEvent event) {
        final MainEventHandler handler = new MainEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)handler);
//        FMLCommonHandler.instance().bus().register((Object)handler);
		

//		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, GuiHandler::openGui);
//		NetworkRegistry.registerGuiHandler(NeoresCards.instance, new GuiHandler());
        
		
		PacketHandler.HANDLER.registerMessage(0, CardMessage.class, CardMessage::encode, CardMessage::decode, CardMessageHandler::handleMessage);
		PacketHandler.HANDLER.registerMessage(1, PCDMessage.class, PCDMessage::encode, PCDMessage::decode, PCDMessageHandler::handleMessage);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@Override
	public void postInit(FMLCommonSetupEvent event) {
		CraftingHandler.init();
		ItemGroups.init();
		CardActivationRecipes.init();
	}

	@Override
	public boolean isClient() {
		return false;
	}

}
