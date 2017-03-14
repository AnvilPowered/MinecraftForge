package com.clowcadia.test.npc;

import com.clowcadia.test.GuiHandler;
import com.clowcadia.test.TestModHandler;
import com.clowcadia.test.utils.Utils;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class Basic extends EntityTameable implements  ICapabilityProvider{	
	
	private final ItemStackHandler handler;
	private String tagStomach = "Stomach";
	private int stomach;	
	private int stomachCap = 800;
	
	public Basic(World world) 
	{			
		super(world);		
		this.handler = new ItemStackHandler(9); 
		this.stomach = 0;
	}
	
	public int getStomach(){
		return this.stomach;
	}
	
	public void setStomach(int value){
		this.stomach = value;
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
		
		if (!this.world.isRemote)
		{			
			int basicID = this.getEntityId(); //for inputing into x/y/z in the opne gui to pass the entety id
			System.out.println("Player has interacted with the mob");	
			player.openGui(TestModHandler.instance, GuiHandler.BASIC, this.world, basicID,0, 0);	
		}
		return true;
    }
	
	@Override
	public void onEntityUpdate() {
		ItemStack foodStack = handler.getStackInSlot(0);
		if (this.world != null){
			if(!this.world.isRemote){
				Utils.getLogger().info("Stomach: "+stomach);
				if(foodStack.getUnlocalizedName().equals(Items.APPLE.getUnlocalizedName()) && stomach != stomachCap){					
					foodStack.splitStack(1);
					stomach += 40;
				}				
			}				
		}			
		super.onEntityUpdate();
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;		
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		this.handler.deserializeNBT(compound.getCompoundTag("ItemStackHandler"));		
		this.stomach = compound.getInteger("Stomach");
		if(compound.hasKey(tagStomach)) Utils.getLogger().info("Reading Stomach");
		super.readEntityFromNBT(compound);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);		
		compound.setTag("ItemStackHandler", this.handler.serializeNBT());
		compound.setInteger("Stomach", this.stomach);
		if(compound.hasKey(tagStomach)) Utils.getLogger().info("Writing Stomach");
		
	}	

}
