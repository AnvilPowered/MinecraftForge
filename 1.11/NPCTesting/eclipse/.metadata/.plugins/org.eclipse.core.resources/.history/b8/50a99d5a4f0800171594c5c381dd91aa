package com.clowcadia.test.containers;

import com.clowcadia.test.npc.Basic;
import com.clowcadia.test.utils.Utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBasic extends Container{
	
	public Entity entity;
	private int stomach;
	
	public ContainerBasic(IInventory playerInv, Entity entity) {
		this.entity = entity;
		
		
		IItemHandler handler = entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null); //Gets the inventory from our tile entity

		//Our tile entity slots
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 8,8));
		
	//The player's inventory slots
		int xPos = 8; //The x position of the top left player inventory slot on our texture
		int yPos = 84; //The y position of the top left player inventory slot on our texture

		//Player slots
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
		Utils.getLogger().info("Finish ContainerBasic");
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !player.isSpectator();
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		Utils.getLogger().info("detectAndSecChanges");
		IContainerListener icontainerlistener = this.listeners.get(0);
		if(this.stomach!=((Basic) this.entity).getStomach()){
			icontainerlistener.sendProgressBarUpdate(this, 0, ((Basic) this.entity).getStomach());
		}
		this.stomach = ((Basic) this.entity).getStomach();
		Utils.getLogger().info("detectAndSecChanges "+this.stomach);
	}
	
	@Override
	public void updateProgressBar(int id, int data) {
		if(id==0)
		((Basic) this.entity).setStomach(data);
	}
}

