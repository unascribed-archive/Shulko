package com.unascribed.shulko;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemSublessShulkerBox extends ItemShulkerBox {

	public static boolean lieAboutCreator = true;
	
	public ItemSublessShulkerBox(Block blockInstance) {
		super(blockInstance);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (worldIn != null) lieAboutCreator = false;
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	@Nullable
	public String getCreatorModId(ItemStack itemStack) {
		// forces JEI to order our purple box in with the vanilla ones
		if (lieAboutCreator) return "minecraft";
		return super.getCreatorModId(itemStack);
	}
	
}
