package com.unascribed.shulko;

import com.unascribed.shulko.BlockShulkoShulkerBox.Variant;

import net.minecraft.tileentity.TileEntityShulkerBox;

public class TileEntityShulkoShulkerBox extends TileEntityShulkerBox {

	private Variant variant;
	
	public TileEntityShulkoShulkerBox() {}
	
	public TileEntityShulkoShulkerBox(Variant variant) {
		super(variant.color);
		this.variant = variant;
	}
	
	public Variant getVariant() {
		if (variant == null) {
			if (Shulko.enableClayShulkerBox && getBlockType() == Shulko.CLAY_SHULKER_BOX) return Variant.CLAY;
			return Variant.ACTUALLY_PURPLE;
		}
		return variant;
	}
	
}
