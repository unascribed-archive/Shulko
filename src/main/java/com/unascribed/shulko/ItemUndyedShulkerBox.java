package com.unascribed.shulko;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemUndyedShulkerBox extends ItemSublessShulkerBox {

	public ItemUndyedShulkerBox(Block blockInstance) {
		super(blockInstance);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal("container.shulkerBox");
	}
	
}
