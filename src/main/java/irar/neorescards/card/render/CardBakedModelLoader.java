package irar.neorescards.card.render;

import irar.neorescards.Ref;
import irar.neorescards.card.Card;
import irar.neorescards.card.Suit;
import irar.neorescards.item.ItemCard;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.registries.ForgeRegistries;

public class CardBakedModelLoader implements ICustomModelLoader{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		boolean accept = ForgeRegistries.ITEMS.getValue(modelLocation) instanceof ItemCard;
		if(modelLocation.getNamespace().contentEquals(Ref.MODID)) {
			for(Suit suit : Suit.values()) {
				if(modelLocation.getPath().contains(suit.id)) {
					accept = true;
				}
			}
		}
		if(accept == true) {
			accept = true;
		}
		return accept;
	}

	@Override
	public IUnbakedModel loadModel(ResourceLocation modelLocation) throws Exception {
		Card card = ((ItemCard) ForgeRegistries.ITEMS.getValue(modelLocation)).getCard();
		return new CardUnbakedModel(card);
	}

}
