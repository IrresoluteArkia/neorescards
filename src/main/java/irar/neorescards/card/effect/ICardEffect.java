package irar.neorescards.card.effect;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public interface ICardEffect {

	public static PotionCardEffect fromPotion(Effect potion) {
		return new PotionCardEffect(potion);
	}
	
	public void applyEffect(PlayerEntity player, int cardTier);
	
	public int getReapplyDelay(int cardTier);
	
	public void addEffectInfo(ItemStack stack, @Nullable World world, List<ITextComponent> info, ITooltipFlag flagIn, int cardTier);

	public static PeriodicPotionCardEffect periodicPotion(Effect potion) {
		return new PeriodicPotionCardEffect(potion);
	}

	public void removeEffect(PlayerEntity player, int tier);
	
}
