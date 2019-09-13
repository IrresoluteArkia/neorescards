package irar.neorescards.util;

import irar.neorescards.handlers.PotionHandler;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;

/**
 * PH (PotionHelper)
 *
 */
public class PH {

	public static Effect SPEED;
	public static Effect SLOWNESS;
	public static Effect HASTE;
	public static Effect MINING_FATIGUE;
	public static Effect STRENGTH;
	public static Effect INSTANT_HEALTH;
	public static Effect INSTANT_DAMAGE;
	public static Effect JUMP_BOOST;
	public static Effect NAUSEA;
	public static Effect REGENERATION;
	public static Effect RESISTANCE;
	public static Effect FIRE_RESISTANCE;
	public static Effect WATER_BREATHING;
	public static Effect INVISIBILITY;
	public static Effect BLINDNESS;
	public static Effect NIGHT_VISION;
	public static Effect HUNGER;
	public static Effect WEAKNESS;
	public static Effect POISON;
	public static Effect WITHER;
	public static Effect HEALTH_BOOST;
	public static Effect ABSORPTION;
	public static Effect SATURATION;
	public static Effect GLOWING;
	public static Effect LEVITATION;
	public static Effect LUCK;
	public static Effect UNLUCK;
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
		SPEED = Effect.get(1);
		SLOWNESS = Effect.get(2);
		HASTE = Effect.get(3);
		MINING_FATIGUE = Effect.get(4);
		STRENGTH = Effect.get(5);
		INSTANT_HEALTH = Effect.get(6);
		INSTANT_DAMAGE = Effect.get(7);
		JUMP_BOOST = Effect.get(8);
		NAUSEA = Effect.get(9);
		REGENERATION = Effect.get(10);
		RESISTANCE = Effect.get(11);
		FIRE_RESISTANCE = Effect.get(12);
		WATER_BREATHING = Effect.get(13);
		INVISIBILITY = Effect.get(14);
		BLINDNESS = Effect.get(15);
		NIGHT_VISION = Effect.get(16);
		HUNGER = Effect.get(17);
		WEAKNESS = Effect.get(18);
		POISON = Effect.get(19);
		WITHER = Effect.get(20);
		HEALTH_BOOST = Effect.get(21);
		ABSORPTION = Effect.get(22);
		SATURATION = Effect.get(23);
		GLOWING = Effect.get(24);
		LEVITATION = Effect.get(25);
		LUCK = Effect.get(26);
		UNLUCK = Effect.get(27);
		FLIGHT = PotionHandler.FLIGHT;
		FORTUNE = PotionHandler.FORTUNE;
		POISON_BODY = PotionHandler.POISON_BODY;
		FLAME_BODY = PotionHandler.FLAME_BODY;
		CURSED_BODY = PotionHandler.CURSED_BODY;
		THORNED_BODY = PotionHandler.THORNED_BODY;
		HEALING_TOUCH = PotionHandler.HEALING_TOUCH;
		THE_FATED = PotionHandler.THE_FATED;
		WAR_DESTINY = PotionHandler.WAR_DESTINY;
		DETERMINATION = PotionHandler.DETERMINATION;
		SOULLESS = PotionHandler.SOULLESS;
		PEACEMAKER = PotionHandler.PEACEMAKER;
	}
	
}
