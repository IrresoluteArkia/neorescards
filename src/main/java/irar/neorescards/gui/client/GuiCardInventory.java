package irar.neorescards.gui.client;

import com.mojang.blaze3d.platform.GlStateManager;

import irar.neorescards.gui.container.ContainerCardInventory;
import irar.neorescards.gui.inventory.CardInventory;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GuiCardInventory extends ContainerScreen<ContainerCardInventory> implements IHasContainer<ContainerCardInventory> {

	private IInventory playerInv;
	private ContainerCardInventory cci;

	public GuiCardInventory(ContainerCardInventory cci, PlayerInventory playerInv, ITextComponent name) {
	    super(cci, playerInv, name);

	    this.playerInv = playerInv;
	    this.cci = cci;

	    this.xSize = 248;
	    this.ySize = 184;
	}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(new ResourceLocation("neorescards:textures/gui/container/card_selector.png"));
        drawTexture(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
    
    private void drawTexture(int x, int y, int tx, int ty, int xSize, int ySize) {
    	this.blit(x, y, tx, ty, xSize, ySize);
//		innerBlit(x, y, x+width, y+height, 100, tx, ty, tx+width, ty+height);
	}

	/**
     * Draws the screen and all the components in it.
     */
    @Override
    public void render(int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.font.drawString(/*this.cci.getUnformattedComponentText()*/"temp", 8, 4, 4210752);
        this.font.drawString(/*this.playerInv.getDisplayName().getUnformattedText()*/"Inventory", 8, this.ySize - 96 + 2, 4210752);
    }
    
}