package irar.neorescards.card;

import irar.neorescards.Ref;

import net.minecraft.util.ResourceLocation;

public enum Suit {
	CLUB("club", "Clubs", 1973790),
	DIAMOND("diamond", "Diamonds", 16711680),
	SPADE("spade", "Spades", 0),
	HEART("heart", "Hearts", 16724530);
	
	
	public final ResourceLocation texture;
	public final int color;
	public final String name;
	public final String id;
	
	Suit(String id, String name, int color){
		this.id = id;
		this.name = name;
		this.color = color;
		this.texture = new ResourceLocation(Ref.MODID, id);
	}
	
}
