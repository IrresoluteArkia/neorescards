package irar.neorescards.network;

import irar.neorescards.gui.client.GuiCardInventory;
import irar.neorescards.gui.container.ContainerCardInventory;
import irar.neorescards.gui.inventory.CardInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandler implements IGuiHandler{
	
	public static ContainerType<ContainerCardInventory> CARDINV = new ContainerType<ContainerCardInventory>(GuiHandler::getServerCardInv);

	public static final int CARD_SELECTOR = 0;
	public static final int CARD_VIEWER = 1;
	
	public static ContainerCardInventory getServerCardInv(int ID, PlayerInventory inv) {
		return new ContainerCardInventory(inv, new CardInventory(1, inv.player, false));
	}

	@Override
	public Object getClientGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z) {
//		System.out.println("Made it to client gui");
		if(ID == GuiHandler.CARD_SELECTOR)
			return new GuiCardInventory(new ContainerCardInventory(player.inventory, new CardInventory(1, player, false)), player.inventory, new StringTextComponent("test 3"));
		return null;
	}
	
	public static Screen openGui(FMLPlayMessages.OpenContainer openContainer)
    {
        BlockPos pos = openContainer.getAdditionalData().readBlockPos();
        PlayerEntity player = Minecraft.getInstance().player;
		return new GuiCardInventory(new ContainerCardInventory(player.inventory, new CardInventory(1, player, true)), player.inventory, new StringTextComponent("test 3"));
//        return new GUIChest(type, (IInventory) Minecraft.getInstance().player.inventory, (IInventory) Minecraft.getInstance().world.getTileEntity(pos));
    }

	@Override
	public Object getServerGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z) {
//		System.out.println("Made it to server gui");
		if(ID == GuiHandler.CARD_SELECTOR)
			return new ContainerCardInventory(player.inventory, new CardInventory(x, player, true));
		
		return null;
	}

	public static void registerContainers(Register<ContainerType<?>> event) {
		event.getRegistry().register(CARDINV.setRegistryName(new ResourceLocation("neorescards:card_inv")));
		ScreenManager.registerFactory(CARDINV, GuiCardInventory::new);
	}


}
