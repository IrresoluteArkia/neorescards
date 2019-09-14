package irar.neorescards.handlers;

import irar.neorescards.Ref;
import irar.neorescards.potion.PotionCursedBody;
import irar.neorescards.potion.PotionDetermination;
import irar.neorescards.potion.PotionFated;
import irar.neorescards.potion.PotionFlameBody;
import irar.neorescards.potion.PotionFlight;
import irar.neorescards.potion.PotionFortune;
import irar.neorescards.potion.PotionHealingTouch;
import irar.neorescards.potion.PotionPeacemaker;
import irar.neorescards.potion.PotionPoisonBody;
import irar.neorescards.potion.PotionSoulless;
import irar.neorescards.potion.PotionThornedBody;
import irar.neorescards.potion.PotionWarDestiny;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionHandler {

	public static Effect FLIGHT;
	public static Effect FORTUNE;
	public static Effect POISON_BODY;
	public static Effect FLAME_BODY;
	public static Effect CURSED_BODY;
	public static Effect THORNED_BODY;
	public static Effect HEALING_TOUCH;
	public static Effect WAR_DESTINY;
	public static Effect THE_FATED;
	public static Effect DETERMINATION;
	public static Effect SOULLESS;
	public static Effect PEACEMAKER;
	
	public static void init() {
		FLIGHT = new PotionFlight().setRegistryName("cardflight");
		FORTUNE = new PotionFortune().setRegistryName("cardfortune");
		POISON_BODY = new PotionPoisonBody().setRegistryName("poisonbody");
		FLAME_BODY = new PotionFlameBody().setRegistryName("flamebody");
		CURSED_BODY = new PotionCursedBody().setRegistryName("cursedbody");
		THORNED_BODY = new PotionThornedBody().setRegistryName("thornedbody");
		HEALING_TOUCH = new PotionHealingTouch().setRegistryName("healingtouch");
		THE_FATED = new PotionFated().setRegistryName("thefated");
		WAR_DESTINY = new PotionWarDestiny().setRegistryName("wardestiny");
		DETERMINATION = new PotionDetermination().setRegistryName("determination");
		SOULLESS = new PotionSoulless().setRegistryName("soulless");
		PEACEMAKER = new PotionPeacemaker().setRegistryName("peacemaker");
	}
	
	public static void register() {
		ForgeRegistries.POTIONS.register(FLIGHT);
		ForgeRegistries.POTIONS.register(FORTUNE);
		ForgeRegistries.POTIONS.register(POISON_BODY);
		ForgeRegistries.POTIONS.register(FLAME_BODY);
		ForgeRegistries.POTIONS.register(CURSED_BODY);
		ForgeRegistries.POTIONS.register(THORNED_BODY);
		ForgeRegistries.POTIONS.register(HEALING_TOUCH);
		ForgeRegistries.POTIONS.register(THE_FATED);
		ForgeRegistries.POTIONS.register(WAR_DESTINY);
		ForgeRegistries.POTIONS.register(DETERMINATION);
		ForgeRegistries.POTIONS.register(SOULLESS);
		ForgeRegistries.POTIONS.register(PEACEMAKER);
	}
	
}
