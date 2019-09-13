package irar.neorescards.potion;

import java.util.ArrayList;
import java.util.List;

import irar.neorescards.util.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class PotionPeacemaker extends PotionBase{

	public PotionPeacemaker() {
		super(EffectType.BENEFICIAL, 0);
	}

	@Override
	public void performEffect(LivingEntity LivingEntityIn, int amplifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void affectEntity(Entity source, Entity indirectSource, LivingEntity LivingEntityIn, int amplifier,
			double health) {
		// TODO Auto-generated method stub
		
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
		List<String> text = new ArrayList<>();
		text.add("The role of the peacemaker is very important!");
		text.add("You help convince living creatures that there are");
		text.add("Better ways to solve problems than fighting.");
		text.add("It's often not very effective, but at least they");
		text.add("Don't hit as hard while listening to you.");
		RenderHelper.INSTANCE.drawTextIfInDelayed(text, x + 6, y + 6, x + 24, y + 24, Minecraft.getInstance());
	}

	@Override
	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
		// TODO Auto-generated method stub
		
	}

}
