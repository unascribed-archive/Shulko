package com.unascribed.shulko;

import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemSubbedShulkerBox extends ItemShulkerBox {

	public ItemSubbedShulkerBox(Block blockInstance) {
		super(blockInstance);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab)) {
			for (EnumDyeColor color : EnumDyeColor.values()) {
				if (color == EnumDyeColor.PURPLE) {
					items.add(new ItemStack(Shulko.ACTUALLY_PURPLE_SHULKER_BOX));
				} else {
					items.add(new ItemStack(BlockShulkerBox.getBlockByColor(color)));
				}
			}
			items.add(new ItemStack(Blocks.PURPLE_SHULKER_BOX));
		}
	}
	

}
