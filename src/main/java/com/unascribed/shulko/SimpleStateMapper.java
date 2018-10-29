package com.unascribed.shulko;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;

public class SimpleStateMapper extends StateMapperBase implements ItemMeshDefinition {
	public final ModelResourceLocation loc;

	public SimpleStateMapper(ModelResourceLocation mrl) {
		this.loc = mrl;
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		return loc;
	}

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		return loc;
	}
}