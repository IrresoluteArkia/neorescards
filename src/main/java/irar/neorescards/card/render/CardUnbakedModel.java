package irar.neorescards.card.render;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import irar.neorescards.card.Card;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.ISprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

public class CardUnbakedModel implements IUnbakedModel {
	
	private Card card;

	public CardUnbakedModel(Card card) {
		this.card = card;
	}

	@Override
	public IBakedModel bake(ModelBakery bakery, Function<ResourceLocation, TextureAtlasSprite> spriteGetter,
			ISprite sprite, VertexFormat format) {
		IUnbakedModel suitModel = bakery.getUnbakedModel(card.suit.texture);
		IUnbakedModel typeModel = bakery.getUnbakedModel(card.type.texture);
		return new CardBakedModel(suitModel.bake(bakery, spriteGetter, sprite, format), typeModel.bake(bakery, spriteGetter, sprite, format));
	}

	@Override
	public Collection<ResourceLocation> getDependencies() {
		List<ResourceLocation> dependencies = new ArrayList<>();
		dependencies.add(card.suit.texture);
		dependencies.add(card.type.texture);
		return dependencies;
	}

	@Override
	public Collection<ResourceLocation> getTextures(Function<ResourceLocation, IUnbakedModel> modelGetter,
			Set<String> missingTextureErrors) {
		return getDependencies();
	}

}
