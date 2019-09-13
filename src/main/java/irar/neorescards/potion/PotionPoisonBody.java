package irar.neorescards.potion;

import irar.neorescards.util.PH;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class PotionPoisonBody extends PotionBase{

	public PotionPoisonBody() {
		super(EffectType.BENEFICIAL, 0);
	}

	@Override
	public void performEffect(LivingEntity LivingEntityIn, int amplifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void affectEntity(Entity source, Entity indirectSource, LivingEntity LivingEntityIn, int amplifier, double health) {
		LivingEntityIn.addPotionEffect(new EffectInstance(PH.POISON, 40 + (10 * amplifier), amplifier));
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInstant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, int x, int y, float z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
		// TODO Auto-generated method stub
		
	}

}
