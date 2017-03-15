package com.clowcadia.test.gui;

import com.clowcadia.test.ModHandler;
import com.clowcadia.test.containers.ContainerBasic;
import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.utils.Utils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiBasic extends GuiContainer{
	
	private IInventory playerInv;
	private Entity entity;

	public GuiBasic(IInventory playerInv, Entity entity) {
		super(new ContainerBasic(playerInv, entity));
		Utils.getLogger().info("GuiBasic: Constructor");
		
		this.xSize=176;
		this.ySize=166;		
		this.playerInv = playerInv;
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		//Utils.getLogger().info("GuiBasic: drawGuiContainerBackgroundLayer");
		
		GlStateManager.color(1.0F, 1.0F, 1.0F,1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(ModHandler.modId, "textures/gui/container/basic.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize,this.ySize);
		
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//Utils.getLogger().info("GuiBasic: drawGuiContainerForegroundLayer");
		
		String s = I18n.format("container.basic"); //Gets the formatted name for the block breaker from the language file
		this.mc.fontRendererObj.drawString(s, this.xSize / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2, 6, 4210752); //Draws the block breaker name in the center on the top of the gui
		this.mc.fontRendererObj.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 72, 4210752); //The player's inventory name
		this.mc.fontRendererObj.drawString(((Test) entity).getStomach()+"", this.xSize / 2 - this.mc.fontRendererObj.getStringWidth("") / 2, 20, 4210752);
		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
		
	}
	

}
