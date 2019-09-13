package irar.neorescards.potion;

import irar.neorescards.util.PH;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class PotionCursedBody extends PotionBase{

	public PotionCursedBody() {
		super(EffectType.BENEFICIAL, 0);
	}

	@Override
	public void performEffect(LivingEntity LivingEntityIn, int amplifier) {
	}

	@Override
	public void affectEntity(Entity source, Entity indirectSource, LivingEntity LivingEntityIn, int amplifier, double health) {
		LivingEntityIn.addPotionEffect(new EffectInstance(PH.SLOWNESS, 60 + (15 * amplifier), amplifier));
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return false;
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, int x, int y, float z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
	}

}
