package irar.neorescards.util;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class DelayedText extends Screen{

	private List<String> text;
	private int x;
	private int y;
	private Minecraft mc;

	public DelayedText(List<String> text, int x, int y, Minecraft mc) {
		super(new StringTextComponent(""));
		this.text = text;
		this.x = x;
		this.y = y;
		this.mc = mc;
	}
	
	public void draw() {
		RenderHelper.INSTANCE.drawText(text, x, y, mc);
	}
	
}
