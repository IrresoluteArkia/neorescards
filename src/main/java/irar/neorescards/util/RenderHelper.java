package irar.neorescards.util;

import java.util.List;

import irar.neorescards.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class RenderHelper extends Screen{
	
	private RenderHelper() {
		super(new StringTextComponent(""));
		this.width = (int) (Minecraft.getInstance().mainWindow.getWidth() / Minecraft.getInstance().mainWindow.getGuiScaleFactor());
		this.height = (int) (Minecraft.getInstance().mainWindow.getHeight() / Minecraft.getInstance().mainWindow.getGuiScaleFactor());
	}
	
	public static RenderHelper INSTANCE;
	
	public static void initRH() {
		INSTANCE = new RenderHelper();
	}

	public void drawTexture(ResourceLocation texture, int x, int y, int width, int height, Minecraft mc) {
		mc.getTextureManager().bindTexture(texture);
		this.drawTexture(x, y, 0, 0, width, height);
	}
	
	private void drawTexture(int x, int y, int tx, int ty, int width, int height) {
		this.blit(x, y, tx, ty, width, height);
	}

	public void drawTextIfInDelayed(List<String> text, int startX, int startY, int endX, int endY, Minecraft mc) {
		int[] mouseCoords = getMouseCoords(mc);
		int mX = mouseCoords[0];
		int mY = mouseCoords[1];
		if(mX >= startX && mX <= endX && mY >= startY && mY <= endY) {
			drawTextDelayed(text, (int) mX, (int) mY,  mc);
		}
	}

	private void drawTextDelayed(List<String> text, int x, int y, Minecraft mc) {
		DelayedText dt = new DelayedText(text, x, y, mc);
		ClientProxy.ceh.submitDelayedText(dt);
	}

	void drawText(List<String> text, int x, int y, Minecraft mc) {
		FontRenderer font = mc.fontRenderer;
		this.renderTooltip(text, x, y, font);
	}

	private int[] getMouseCoords(Minecraft mc) {
		double scale = mc.mainWindow.getGuiScaleFactor();
        int mouseX = (int) (mc.mouseHelper.getMouseX() / scale);
        int mouseY = (int) (mc.mouseHelper.getMouseY() / scale);
		return new int[] {mouseX, mouseY};
	}

}
