package irar.neorescards.card.effect;

import java.util.List;

import irar.neorescards.util.PH;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class PeriodicPotionCardEffect implements ICardEffect {

	private Effect potion;
	private int duration = 5;
	private int frequency = 30;

	public PeriodicPotionCardEffect(Effect potion) {
		this.potion = potion;
	}

	@Override
	public void applyEffect(PlayerEntity player, int cardTier) {
		float health = player.getHealth();
		player.addPotionEffect(new EffectInstance(potion, duration * 20, cardTier - 1, true, false));
		float healAmount = health - player.getHealth();
		player.heal(healAmount);
	}

	@Override
	public int getReapplyDelay(int cardTier) {
		return frequency * 20;
	}

	@Override
	public void addEffectInfo(ItemStack stack, World world, List<ITextComponent> info, ITooltipFlag flagIn, int cardTier) {
		info.add(new StringTextComponent("Occasional " + I18n.format(potion.getName()) + " " + cardTier));
	}

	public PeriodicPotionCardEffect setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	public PeriodicPotionCardEffect setFrequency(int frequency) {
		this.frequency  = frequency;
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
