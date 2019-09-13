package irar.neorescards.card.render;

import java.util.List;
import java.util.Map;
import java.util.Random;

import irar.neorescards.card.Card;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class CardBakedModel implements IBakedModel{
	
	private IBakedModel suitBakedModel;
	private IBakedModel typeBakedModel;

	public CardBakedModel(Card card, Map<ResourceLocation, IBakedModel> registry) {
		suitBakedModel = registry.get(new ModelResourceLocation(card.suit.texture, "inventory"));
		typeBakedModel = registry.get(new ModelResourceLocation(card.type.texture, "inventory"));
	}

	public CardBakedModel(IBakedModel suit, IBakedModel type) {
		suitBakedModel = suit;
		typeBakedModel = type;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
		if(Screen.hasShiftDown()) {
			return suitBakedModel.getQuads(state, side, rand);
		}
		return typeBakedModel.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		if(Screen.hasShiftDown()) {
			return suitBakedModel.isAmbientOcclusion();
		}
		return typeBakedModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		if(Screen.hasShiftDown()) {
			return suitBakedModel.isGui3d();
		}
		return typeBakedModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		if(Screen.hasShiftDown()) {
			return suitBakedModel.getParticleTexture();
		}
		return typeBakedModel.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		if(Screen.hasShiftDown()) {
			return suitBakedModel.getOverrides();
		}
		return typeBakedModel.getOverrides();
	}

}
