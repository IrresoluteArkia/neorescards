package irar.neorescards.world;

import irar.neorescards.card.effect.ICardEffect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class EffectData implements INBTSerializable<CompoundNBT> {

	private int tick;

	public EffectData(ICardEffect effect) {
		tick = 0;
	}

	public EffectData(CompoundNBT data) {
		deserializeNBT(data);
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("TICK", tick);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if(nbt.contains("TICK")) {
			tick = nbt.getInt("TICK");
		}else {
			tick = 0;
		}
	}

	public void applyEffect(CardData cardData, ICardEffect effect, PlayerEntity player) {
		tick++;
		if(tick >= effect.getReapplyDelay(cardData.getTier())) {
			tick = 0;
			effect.applyEffect(player, cardData.getTier());
		}
		cardData.markDirty();
	}

	public void removeEffect(CardData cardData, ICardEffect effect, PlayerEntity player) {
		effect.removeEffect(player, cardData.getTier());
	}

}
