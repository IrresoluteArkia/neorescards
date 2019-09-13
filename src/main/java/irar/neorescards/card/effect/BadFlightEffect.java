package irar.neorescards.card.effect;

import java.util.List;

import irar.neorescards.util.PH;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class BadFlightEffect implements ICardEffect{
	
	int imperfectUntil;
	
	public BadFlightEffect(int imperfectUntil) {
		this.imperfectUntil = imperfectUntil;
	}

	@Override
	public void applyEffect(PlayerEntity player, int cardTier) {
		if(cardTier < imperfectUntil && player.abilities.isFlying && !player.abilities.isCreativeMode) {
			player.addPotionEffect(new EffectInstance(PH.WITHER, 41, imperfectUntil - cardTier - 1, false, false));
		}
	}

	@Override
	public int getReapplyDelay(int cardTier) {
		return 40 * cardTier/* + (120 * (cardTier - 1)) */;
	}

	@Override
	public void addEffectInfo(ItemStack stack, World world, List<ITextComponent> info, ITooltipFlag flagIn, int cardTier) {
		if(cardTier < imperfectUntil) {
			info.add(new StringTextComponent("Withering Flight " + (imperfectUntil - cardTier)));
		}else {
			info.add(new StringTextComponent("Flight"));
		}
	}

	@Override
	public void removeEffect(PlayerEntity player, int tier) {
		EffectInstance effect = player.getActivePotionEffect(PH.WITHER);
		if(effect != null) {
			player.removePotionEffect(PH.WITHER);
		}
	}

	
	
}
