package irar.neorescards.potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import irar.neorescards.util.RenderHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class PotionFlight extends PotionBase{
	
	private static final ResourceLocation FLIGHT_INV = new ResourceLocation("neorescards:textures/potion/inventory/flight.png");
	private static final ResourceLocation FLIGHT_HUD = new ResourceLocation("neorescards:textures/potion/hud/flight.png");

	public PotionFlight() {
		super(EffectType.BENEFICIAL, 0);
	}

	@Override
	public void performEffect(LivingEntity LivingEntityIn, int amplifier) {
        if(LivingEntityIn instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) LivingEntityIn;
        	player.abilities.allowFlying = true;
        	player.sendPlayerAbilities();
        }

	}

	@Override
	public void affectEntity(Entity source, Entity indirectSource, LivingEntity LivingEntityIn, int amplifier, double health) {}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return duration % 20 == 0;
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
    public void applyAttributesModifiersToEntity(LivingEntity LivingEntityIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        super.applyAttributesModifiersToEntity(LivingEntityIn, attributeMapIn, amplifier);
        if(LivingEntityIn instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) LivingEntityIn;
        	player.abilities.allowFlying = true;
        	player.sendPlayerAbilities();
        }
    }
	
	@Override
	public void removeAttributesModifiersFromEntity(LivingEntity LivingEntityIn, AbstractAttributeMap attributeMapIn, int amplifier)
    {
        super.removeAttributesModifiersFromEntity(LivingEntityIn, attributeMapIn, amplifier);
        if(LivingEntityIn instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) LivingEntityIn;
        	if(!player.abilities.isCreativeMode) {
        		player.abilities.allowFlying = false;
        		player.sendPlayerAbilities();
        	}
        }
    }

	@Override
	public void renderInventoryEffect(EffectInstance effect, DisplayEffectsScreen<?> gui, int x, int y, float z) {
		RenderHelper.INSTANCE.drawTexture(FLIGHT_INV, x + 6, y + 6, 18, 18, Minecraft.getInstance());
		List<String> text = new ArrayList<>();
		text.add(TextFormatting.ITALIC + "You can fly!");
		text.add("" + TextFormatting.DARK_RED + TextFormatting.BOLD + "WARNING: " + TextFormatting.RESET + TextFormatting.GRAY + "You may also experience " + TextFormatting.DARK_GRAY + TextFormatting.BOLD + "WITHERING" + TextFormatting.RESET + TextFormatting.GRAY + " during flight");
		text.add(TextFormatting.GRAY + "if you recieved this effect from a " + TextFormatting.DARK_PURPLE + TextFormatting.OBFUSCATED + "|||" + TextFormatting.RESET + TextFormatting.BOLD + "CARD" + TextFormatting.RESET + TextFormatting.DARK_PURPLE + TextFormatting.OBFUSCATED + "|||");
		RenderHelper.INSTANCE.drawTextIfInDelayed(text, x + 6, y + 6, x + 24, y + 24, Minecraft.getInstance());
	}

	@Override
	public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
		RenderHelper.INSTANCE.drawTexture(FLIGHT_HUD, x + 3, y + 3, 18, 18, Minecraft.getInstance());
	}

}
