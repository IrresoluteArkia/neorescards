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

public class PotionDetermination extends PotionBase{

	public PotionDetermination() {
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
		text.add("You are filled with determination!");
		text.add("You gain the power to stay determined all the time,");
		text.add("Every day. Even if the monsters hurt you one day,");
		text.add("You will get up the next, ready to deal with them.");
		RenderHelper.INSTANCE.drawTextIfInDelayed(text, x + 6, y + 6, x + 24, y + 24, Minecraft.getInstance());
	}

	@Override
	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
		// TODO Auto-generated method stub
		
	}

}
