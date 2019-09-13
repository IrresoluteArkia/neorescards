package irar.neorescards.util;

import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ChatHelper {

	public static StringTextComponent formatResoluteArkia(String string) {
		StringTextComponent messagep1 = new StringTextComponent("[ResoluteArkia]");
		StringTextComponent messagep2 = new StringTextComponent(" " + string);
		messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
		messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
		messagep1.appendSibling(messagep2);
		return messagep1;
	}

}
