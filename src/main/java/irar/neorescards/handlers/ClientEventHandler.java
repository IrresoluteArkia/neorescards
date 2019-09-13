package irar.neorescards.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import irar.neorescards.NeoresCards;
import irar.neorescards.card.Card;
import irar.neorescards.card.Cards;
import irar.neorescards.card.render.CardBakedModel;
import irar.neorescards.card.render.CardBakedModelLoader;
import irar.neorescards.item.ItemCard;
import irar.neorescards.proxy.CommonProxy;
import irar.neorescards.util.DelayedText;
import irar.neorescards.world.CardData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEventHandler extends Screen{
	
	private List<DelayedText> dts = new ArrayList<>();

	public ClientEventHandler() {
		super(new StringTextComponent(""));
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onGameOverlay(RenderGameOverlayEvent.Post event){
		if(event.getType() == RenderGameOverlayEvent.ElementType.ALL){ //  && Minecraft.getMinecraft().currentScreen == null
			Minecraft mc = Minecraft.getInstance();
			PlayerEntity player = mc.player;
			ItemRenderer itemRender = mc.getItemRenderer();
			if(true) {
				renderPlayerCards(player, itemRender, mc);
			}
		}
	}
	
	@SubscribeEvent
	public void drawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
		for(DelayedText dt : dts) {
			dt.draw();
		}
		dts.clear();
	}

	private void renderPlayerCards(PlayerEntity player, ItemRenderer itemRender, Minecraft mc) {
		if(CommonProxy.saveData.isPlayerInList(player)) {
			List<CardData> cards = CommonProxy.saveData.getCardDataForPlayer(player).getCardData();
			for(int i = 0; i < cards.size(); i++) {
				int x = i % 13;
				int y = i / 13;
				ItemStack stack = ItemCard.getItemStackWithTierCardAndMetadata(cards.get(i).getCard(), 1, 1, true);
				itemRender.renderItemAndEffectIntoGUI(stack, 5 + 19 * x, 5 + 6 * y);
			}
		}
	}

	public void submitDelayedText(DelayedText dt) {
		dts.add(dt);
	}
	
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class ModelEventHandler{
    	@SubscribeEvent
    	public static void modelRegistryReady(ModelRegistryEvent event) {
    		ItemHandler.registerCustomModels();
    	}
    	
    	@SubscribeEvent
    	public static void modelBake(ModelBakeEvent event) {
    		Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
//    		for(Item item : Cards.allCardItems) {
//    			ItemCard card = (ItemCard) item;
//    			registry.put(card.getRegistryName(), new CardBakedModel(card.getCard(), registry));
//    		}
    		for(Item item : Cards.allCardItems) {
				ItemCard card = (ItemCard) item;
				registry.put(new ModelResourceLocation(card.getRegistryName(), "inventory"), new CardBakedModel(card.getCard(), registry));
			}
    	}
	}
	
}
