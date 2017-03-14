package com.clowcadia.test.containers;

import com.clowcadia.test.entities.Test;
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
		Utils.getLogger().info("ContainerBasic: Constructor");
		
		IItemHandler handler = entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null); //Gets the inventory from our tile entity

		this.addSlotToContainer(new SlotItemHandler(handler, 0, 8,8));
		
		int xPos = 8;
		int yPos = 84; 

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
		Utils.getLogger().info("ContainerBasic: canInteractWith");
		
		return !player.isSpectator();
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		Utils.getLogger().info("ContainerBasic: detectAndSendChanges");
		
		IContainerListener icontainerlistener = this.listeners.get(0);
		if(this.stomach!=((Test) this.entity).getStomach()){
			icontainerlistener.sendProgressBarUpdate(this, 0, ((Test) this.entity).getStomach());
		}
		this.stomach = ((Test) this.entity).getStomach();
	}
	
	@Override
	public void updateProgressBar(int id, int data) {
		Utils.getLogger().info("ContainerBasic: updateProgressBar");
		
		if(id==0)
		((Test) this.entity).setStomach(data);
	}
}

