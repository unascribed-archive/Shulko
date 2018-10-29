package com.unascribed.shulko;

import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShulkerBoxRecipes.ShulkerBoxColoring;
import net.minecraft.world.World;
import net.minecraftforge.oredict.DyeUtils;

public class RecipeShulkoShulkerBoxColoring extends ShulkerBoxColoring {

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i).getItem() == Item.getItemFromBlock(Shulko.CLAY_SHULKER_BOX)) return false;
		}
		return super.matches(inv, worldIn);
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack shulkerBox = ItemStack.EMPTY;
		ItemStack dye = ItemStack.EMPTY;

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack is = inv.getStackInSlot(i);
			if (!is.isEmpty()) {
				Block b = Block.getBlockFromItem(is.getItem());
				if (b instanceof BlockShulkerBox) { 
					shulkerBox = is;
				} else if (DyeUtils.isDye(is)) {
					dye = is;
				}
			}
		}

		ItemStack output = BlockShulkerBox.getColoredItemStack(DyeUtils.colorFromStack(dye).get());
		if (output.getItem() == Item.getItemFromBlock(Blocks.PURPLE_SHULKER_BOX)) {
			output = new ItemStack(Shulko.ACTUALLY_PURPLE_SHULKER_BOX);
		}

		if (shulkerBox.hasTagCompound()) {
			output.setTagCompound(shulkerBox.getTagCompound().copy());
		}

		return output;
	}

}
