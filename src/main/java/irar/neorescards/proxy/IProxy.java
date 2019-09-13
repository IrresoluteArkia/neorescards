package irar.neorescards.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IProxy {
	public void preInit(FMLCommonSetupEvent event);
	public void init(FMLCommonSetupEvent event);
	public void postInit(FMLCommonSetupEvent event);
	public boolean isClient();
}
