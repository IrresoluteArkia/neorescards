package irar.neorescards.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import irar.neorescards.proxy.CommonProxy;
import irar.neorescards.util.PH;
import irar.neorescards.world.PlayerCardData;
import irar.neorescards.world.SaveDataHandler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MainEventHandler{
	
	private HashMap<ItemStack, Integer> removeFortuneFrom = new HashMap<>();
	public static HashMap<ServerPlayerEntity, Integer> tickMap = new HashMap<ServerPlayerEntity, Integer>();

//	@SubscribeEvent
//	public void Register(RegistryEvent.Register<IRecipe> event){
//		CommonProxy.recipeRegistry = event.getRegistry();
//	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
		if(!event.getPlayer().world.isRemote){
			CommonProxy.saveData = SaveDataHandler.get(event.getPlayer().world);
			if(CommonProxy.saveData.isPlayerInList(event.getPlayer())){
				if(CommonProxy.saveData.getCardDataForPlayer(event.getPlayer()).hasCards()) {
					StringTextComponent messagep1 = new StringTextComponent("[ResoluteArkia]");
					StringTextComponent messagep2 = new StringTextComponent(" Welcome back " + event.getPlayer().getName().getFormattedText() + ", the power of your cards has been waiting for you.");
					messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
					messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
					messagep1.appendSibling(messagep2);
					event.getPlayer().sendMessage(messagep1);
				}
			}else{
				StringTextComponent messagep1 = new StringTextComponent("[ResoluteArkia]");
				StringTextComponent messagep2 = new StringTextComponent(" Hello " + event.getPlayer().getName().getFormattedText() + ", welcome to my world; here is a card to get you started.");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				event.getPlayer().sendMessage(messagep1);
				event.getPlayer().world.addEntity(new ItemEntity(event.getPlayer().world, event.getPlayer().posX, event.getPlayer().posY, event.getPlayer().posZ, new ItemStack(ItemHandler.CardSelector)));
			}
		}else {
			CommonProxy.saveData = new SaveDataHandler();
		}
		CommonProxy.world = event.getPlayer().world;
	}
    @SubscribeEvent
    public void entTick(final LivingEvent.LivingUpdateEvent event) {
        if(event.getEntity() instanceof PlayerEntity){
        	PlayerEntity player = (PlayerEntity)event.getEntity();
        	if(player.abilities.isFlying && !player.abilities.allowFlying) {
        		player.abilities.isFlying = false;
        		player.sendPlayerAbilities();
        	}
        }
        if (event.getEntity().world.isRemote) {
            return;
        }
        if(event.getEntity() instanceof ServerPlayerEntity){
        	ServerPlayerEntity PlayerEntity = (ServerPlayerEntity)event.getEntity();
        	int tick = 0;
        	if(tickMap.containsKey(PlayerEntity)) {
        		tick = tickMap.get(PlayerEntity);
        		tick++;
        	}
        	tickMap.put(PlayerEntity, tick);
//        	System.out.println(PlayerEntity.getUniqueID().toString());
        	if(CommonProxy.saveData.isPlayerInList(PlayerEntity)){
        		PlayerCardData playerData = CommonProxy.saveData.getCardDataForPlayer(PlayerEntity);
        		this.applyPlayerEffects(playerData, PlayerEntity, tick);
        	}
        }
        for(ItemStack stack : removeFortuneFrom.keySet()) {
        	int i = removeFortuneFrom.get(stack);
        	removeFortune(stack, i);
        }
        removeFortuneFrom.clear();
        if(event.getEntity() instanceof LivingEntity) {
        	LivingEntity living = (LivingEntity) event.getEntity();
        	if(living.isPotionActive(PH.THE_FATED)) {
        		Random r = new Random();
        		if(r.nextInt(6000) == 1) {
        			BlockPos pos = getPosition(r, new BlockPos(living), 5, 5, 10, 10);
        			LightningBoltEntity lb = new LightningBoltEntity(living.world, pos.getX(), pos.getY(), pos.getZ(), false);
        			living.world.addEntity(lb);
        		}
        	}
        }
    }
    private BlockPos getPosition(Random r, BlockPos startPos, int minX, int minZ, int maxX, int maxZ) {
		BlockPos pos = startPos.add(0, 0, 0);
		int xDist = getNumberPosNeg(r, minX, maxX);
		int zDist = getNumberPosNeg(r, minZ, maxZ);
		pos = pos.add(xDist, 0, zDist);
		return pos;
	}

	private int getNumberPosNeg(Random r, int min, int max) {
		boolean pos = r.nextBoolean();
		int num = r.nextInt(max - min) + min;
		return pos ? num : -num;
	}

	@SubscribeEvent
    public void playerSpawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getPlayer().world.isRemote) {
            return;
        }
    	ServerPlayerEntity PlayerEntity = (ServerPlayerEntity)event.getPlayer();
    	int tick = 0;
    	if(tickMap.containsKey(PlayerEntity)) {
    		tick = tickMap.get(PlayerEntity);
    	}
    	if(CommonProxy.saveData.isPlayerInList(PlayerEntity)){
    		PlayerCardData playerData = CommonProxy.saveData.getCardDataForPlayer(PlayerEntity);
    		this.applyPlayerEffects(playerData, PlayerEntity, tick);
    	}
    }
    
    public void applyPlayerEffects(PlayerCardData playerData, ServerPlayerEntity PlayerEntity, int tick) {
		float health = PlayerEntity.getHealth();
    	playerData.applyEffects(PlayerEntity);
		float healAmount = health - PlayerEntity.getHealth();
		PlayerEntity.heal(healAmount);
    }
    
    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent event) {
    	PlayerEntity player = event.getPlayer();
    	if(player.world.isRemote) {
    		return;
    	}
    	ItemStack stack = player.getHeldItemMainhand();
    	if(stack.isEmpty()) {
    		return;
    	}
    	if(EnchantmentHelper.getEnchantments(stack).keySet().contains(Enchantments.SILK_TOUCH)) {
    		return;
    	}
    	if(player.getActivePotionEffect(PH.FORTUNE) != null) {
    		EffectInstance fortune = player.getActivePotionEffect(PH.FORTUNE);
    		int fortuneModifier = fortune.getAmplifier() + 1;
    		addFortune(stack, fortuneModifier);
    		removeFortuneFrom.put(stack, fortuneModifier);
    	}
    }
    
    private void addFortune(ItemStack stack, int fortuneModifier) {
		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
    	if(EnchantmentHelper.getEnchantments(stack).keySet().contains(Enchantments.FORTUNE)) {
    		map.put(Enchantments.FORTUNE, map.get(Enchantments.FORTUNE) + fortuneModifier);
    	}else {
    		map.put(Enchantments.FORTUNE, fortuneModifier);
    	}
    	EnchantmentHelper.setEnchantments(map, stack);
	}

	private void removeFortune(ItemStack stack, int fortuneModifier) {
		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
    	int i = map.get(Enchantments.FORTUNE);
    	if(i <= fortuneModifier) {
    		map.remove(Enchantments.FORTUNE);
    	}else {
    		map.put(Enchantments.FORTUNE, map.get(Enchantments.FORTUNE) - fortuneModifier);
    	}
    	EnchantmentHelper.setEnchantments(map, stack);
	}
	
	private static final float cr3 = (float) Math.pow(2, 1.0F/3);
	
	@SubscribeEvent
	public void playerHurt(LivingHurtEvent event) {
    	if(event.getEntityLiving().world.isRemote) {
    		return;
    	}
    	LivingEntity living = event.getEntityLiving();
    	Entity source = event.getSource().getImmediateSource();
    	if(source != null && source instanceof LivingEntity) {
    		LivingEntity livingSource = (LivingEntity) source;
    		if(living.equals(livingSource)) {
    			return;
    		}
    		Effect[] bodies = new Effect[] {PH.POISON_BODY, PH.FLAME_BODY, PH.CURSED_BODY, PH.THORNED_BODY};
    		for(Effect potion : bodies) {
    			if(living.isPotionActive(potion) && livingSource.getHeldItemMainhand().isEmpty()) {
    				potion.affectEntity(null, null, livingSource, living.getActivePotionEffect(potion).getAmplifier(), 0.0F);
    			}
    		}
    		for(Effect potion : bodies) {
    			if(livingSource.isPotionActive(potion) && livingSource.getHeldItemMainhand().isEmpty()) {
    				potion.affectEntity(null, null, living, livingSource.getActivePotionEffect(potion).getAmplifier(), 0.0F);
    			}
    		}
    	}
    	handlePeacemaker(event);
	}
	
	private void handlePeacemaker(LivingHurtEvent event) {
		if(event.getSource().getImmediateSource() instanceof LivingEntity && !event.getSource().getImmediateSource().equals(event.getEntityLiving())) {
			List<LivingEntity> toCheck = event.getEntityLiving().world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(new BlockPos(event.getEntityLiving()).subtract(new BlockPos(50, 15, 50)), new BlockPos(event.getEntityLiving()).add(new BlockPos(50, 15, 50))));
			int pmCount = 0;
			for(LivingEntity check : toCheck) {
				if(check.isPotionActive(PH.PEACEMAKER)) {
					pmCount += check.getActivePotionEffect(PH.PEACEMAKER).getAmplifier() + 1;
				}
			}
			if(pmCount > 0) {
				float damageModifier = (float) (1.0F / (Math.pow(cr3, pmCount)));
//				System.out.println("Changing " + event.getAmount() + " to " + (event.getAmount() * damageModifier));
				event.setAmount(event.getAmount() * damageModifier);
			}
		}
	}

	@SubscribeEvent
	public void playerUseItem(EntityInteractSpecific event) {
		PlayerEntity player = event.getPlayer();
		if(player.isPotionActive(PH.HEALING_TOUCH) && event.getHand() == Hand.MAIN_HAND && player.getHeldItemMainhand().isEmpty()) {
			int level = player.getActivePotionEffect(PH.HEALING_TOUCH).getAmplifier() + 1;
			Entity entity  = event.getTarget();
			if(entity instanceof LivingEntity) {
				LivingEntity target = (LivingEntity) entity;
				if(target.getHealth() < target.getMaxHealth() && player.getFoodStats().getFoodLevel() > 0) {
					if(player.world.isRemote) {
						Random rand = new Random();
			            for (int i = 0; i < level; ++i)
			            {
			                double d0 = rand.nextGaussian() * 0.02D;
			                double d1 = rand.nextGaussian() * 0.02D;
			                double d2 = rand.nextGaussian() * 0.02D;
			                target.world.addParticle(ParticleTypes.HEART, target.posX + (double)(rand.nextFloat() * target.getWidth() * 2.0F) - (double)target.getWidth(), target.posY + 0.5D + (double)(rand.nextFloat() * target.getHeight()), target.posZ + (double)(rand.nextFloat() * target.getWidth() * 2.0F) - (double)target.getWidth(), d0, d1, d2);
			            }
					}else {
						float oldHealth = target.getHealth();
						target.heal(1.0F * level);
						float newHealth = target.getHealth();
						player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - (int) (newHealth - oldHealth));
					}
					event.setCancellationResult(ActionResultType.SUCCESS);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void playerWakeUp(PlayerWakeUpEvent event) {
		PlayerEntity player = event.getPlayer();
		if(player.world.isRemote) {
			return;
		}
		if(event.shouldSetSpawn()) {
			if(player.isPotionActive(PH.DETERMINATION)) {
				int level = player.getActivePotionEffect(PH.DETERMINATION).getAmplifier() + 1;
				float healAmount = (player.getMaxHealth() / 3) * level;
				player.heal(healAmount);
			}
		}
	}
	
	@SubscribeEvent
	public void mobSpawn(LivingSpawnEvent.CheckSpawn event) {
		if(event.getEntityLiving().world.isRemote || !event.getEntityLiving().isNonBoss()) {
			return;
		}
		List<LivingEntity> toCheck = event.getEntityLiving().world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(new BlockPos(event.getEntityLiving()).subtract(new BlockPos(100, 30, 100)), new BlockPos(event.getEntityLiving()).add(new BlockPos(100, 30, 100))));
		int wdCount = 0;
		for(LivingEntity living : toCheck) {
			if(living.isPotionActive(PH.WAR_DESTINY)) {
				wdCount += living.getActivePotionEffect(PH.WAR_DESTINY).getAmplifier() + 1;
			}
		}
		if(wdCount > 0) {
			Random rand = new Random();
			float limiter = 0.999F;
			float amplifier = 0.5F;
			float chance = (float) (1 - (Math.pow(amplifier, wdCount)));
			chance *= limiter;
			if(chance >= rand.nextFloat()) {
				LivingEntity clone = cloneMonster(event.getEntityLiving());
				if(clone != null) {
					event.getEntityLiving().world.addEntity(clone);
//					System.out.println("Spawned extra entity: " + clone.getName().getFormattedText());
				}else {
					System.out.println("Failed to spawn entity: " + event.getEntityLiving().getName().getFormattedText() + ", this is a bug, and if this is a modded entity, likely that mod's fault.");
				}
			}
		}
	}

	private LivingEntity cloneMonster(LivingEntity living) {
		CompoundNBT livingData = new CompoundNBT();
		living.writeUnlessRemoved(livingData);
//		livingData.putString("id", living.);
		UUID uuid = UUID.randomUUID();
		livingData.putLong("UUIDLeast", uuid.getLeastSignificantBits());
		livingData.putLong("UUIDMost", uuid.getMostSignificantBits());
		LivingEntity entity = (LivingEntity) EntityType.loadEntityUnchecked(livingData, living.world).get();
		return entity;
	}
	
	@SubscribeEvent
	public void setAttackTarget(LivingSetAttackTargetEvent event) {
		if(!(event.getEntityLiving() instanceof LivingEntity)) {
			return;
		}
		LivingEntity living = (LivingEntity) event.getEntityLiving();
		if(living.world.isRemote) {
			return;
		}
		if(event.getTarget() == null) {
			return;
		}
		if(living.getRevengeTarget() == null || (living.getRevengeTarget() != null && !living.getRevengeTarget().equals(event.getTarget()))) {
			LivingEntity target = event.getTarget();
			if(target.isPotionActive(PH.SOULLESS)) {
				int[] distancesSqu = new int[] {36, 4, -1};
				int level = target.getActivePotionEffect(PH.SOULLESS).getAmplifier();
				if(level >= distancesSqu.length) {
					level = distancesSqu.length - 1;
				}
				int distanceSqu = distancesSqu[level];
				double actualDistance = living.getDistanceSq(target);
				if(actualDistance >= distanceSqu) {
					living.setRevengeTarget(null);
				}
			}
		}
	}

}
