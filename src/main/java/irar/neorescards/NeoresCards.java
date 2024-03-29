package irar.neorescards;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import irar.neorescards.card.Cards;
import irar.neorescards.card.render.CardBakedModelLoader;
import irar.neorescards.handlers.ClientEventHandler;
import irar.neorescards.handlers.ItemHandler;
import irar.neorescards.handlers.PotionHandler;
import irar.neorescards.network.GuiHandler;
import irar.neorescards.proxy.ClientProxy;
import irar.neorescards.proxy.CommonProxy;
import irar.neorescards.proxy.IProxy;
import irar.neorescards.util.PH;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("neorescards")
public class NeoresCards
{
//	public static NeoresCards instance = new NeoresCards();
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static IProxy proxy = (IProxy) DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public NeoresCards() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> this::addClientEventHandler);
    }
    
    private void addClientEventHandler() {
		ClientProxy.ceh = new ClientEventHandler();
		ModelLoaderRegistry.registerLoader(new CardBakedModelLoader());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
    	proxy.preInit(event);
    	proxy.init(event);
    	proxy.postInit(event);
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
    		Cards.init();
    		ItemHandler.init();
    		ItemHandler.register();
       }
        @SubscribeEvent
        public static void onPotionsRegistry(final RegistryEvent.Register<Effect> event) {
    		PotionHandler.init();
    		PotionHandler.register();
    		PH.init();
    		Cards.addEffects();
       }
        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
    		GuiHandler.registerContainers(event);
       }
    }
}
