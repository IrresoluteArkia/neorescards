package irar.neorescards.potion;

import javax.annotation.Nullable;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public abstract class PotionBase extends Effect{

	protected PotionBase(EffectType type, int liquidColorIn) {
		super(type, liquidColorIn);
	}
	
	@Override
	public abstract void performEffect(LivingEntity LivingEntityIn, int amplifier);
	
	@Override
	public abstract void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity LivingEntityIn, int amplifier, double health);

	@Override
	public abstract boolean isReady(int duration, int amplifier);
	
	@Override
	public abstract boolean isInstant();
	
	@Override
	public abstract void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, int x, int y, float z);

	@Override
	public abstract void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha);
	
	
}
