package irar.neorescards.card.effect;

import java.util.List;

import irar.neorescards.util.PH;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class PotionCardEffect implements ICardEffect {

	private Effect potion;
	private boolean infoHidden = false;

	PotionCardEffect(Effect potion) {
		this.potion = potion;
	}

	@Override
	public void applyEffect(PlayerEntity player, int cardTier) {
		float health = player.getHealth();
		player.addPotionEffect(new EffectInstance(potion, (100000 * 20) - 1, cardTier - 1, false, false));
		float healAmount = health - player.getHealth();
		player.heal(healAmount);
	}

	@Override
	public int getReapplyDelay(int cardTier) {
		return 80;
	}

	@Override
	public void addEffectInfo(ItemStack stack, World world, List<ITextComponent> info, ITooltipFlag flagIn, int cardTier) {
		if(!infoHidden) {
			info.add(new StringTextComponent(I18n.format(potion.getName()) + " " + cardTier));
		}
	}
	
	public PotionCardEffect setInfoHidden(boolean hidden) {
		this.infoHidden = hidden;
		return this;
	}

	@Override
	public void removeEffect(PlayerEntity player, int tier) {
		EffectInstance effect = player.getActivePotionEffect(potion);
		if(effect != null) {
			if(!(effect.getAmplifier() > tier - 1)) {
				player.removePotionEffect(potion);
			}
		}
	}

}
