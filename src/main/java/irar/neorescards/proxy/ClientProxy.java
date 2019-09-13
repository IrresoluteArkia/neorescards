package irar.neorescards.proxy;

import irar.neorescards.handlers.ClientEventHandler;
import irar.neorescards.handlers.ItemHandler;
import irar.neorescards.util.RenderHelper;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy extends CommonProxy{
	
	public static ClientEventHandler ceh;
	
	@Override
	public void preInit(FMLCommonSetupEvent event){
		super.preInit(event);
		ItemHandler.registerRenders();
		RenderHelper.initRH();
	}
	
	@Override
	public void init(FMLCommonSetupEvent event){
		super.init(event);
		ItemHandler.registerColors();
	}
	
	@Override
	public boolean isClient() {
		return true;
	}
}
