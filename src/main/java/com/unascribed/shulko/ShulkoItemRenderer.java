package com.unascribed.shulko;

import com.unascribed.shulko.BlockShulkoShulkerBox.Variant;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class ShulkoItemRenderer extends TileEntityItemStackRenderer {

	private final TileEntityShulkoShulkerBox te;
	
	public ShulkoItemRenderer(Variant variant) {
		te = new TileEntityShulkoShulkerBox(variant);
	}
	
	
	@Override
	public void renderByItem(ItemStack itemStackIn, float partialTicks) {
		TileEntityRendererDispatcher.instance.render(te, 0, 0, 0, 0, partialTicks);
	}
	
}
