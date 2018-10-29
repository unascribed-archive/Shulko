package com.unascribed.shulko;

import com.unascribed.shulko.BlockShulkoShulkerBox.Variant;

import net.minecraft.client.model.ModelShulker;
import net.minecraft.client.renderer.tileentity.TileEntityShulkerBoxRenderer;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.ResourceLocation;

public class TileEntityShulkoShulkerBoxRenderer extends TileEntityShulkerBoxRenderer {

	private static final ResourceLocation CLAY = new ResourceLocation("shulko", "textures/entity/shulker_clay.png");
	private static final ResourceLocation ACTUALLY_PURPLE = new ResourceLocation("shulko", "textures/entity/shulker_actually_purple.png");
	
	private ResourceLocation overrideTex;
	
	public TileEntityShulkoShulkerBoxRenderer(ModelShulker modelIn) {
		super(modelIn);
	}
	
	@Override
	public void render(TileEntityShulkerBox te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if (((TileEntityShulkoShulkerBox)te).getVariant() == Variant.CLAY) {
			overrideTex = CLAY;
		} else {
			overrideTex = ACTUALLY_PURPLE;
		}
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
	}
	
	@Override
	protected void bindTexture(ResourceLocation location) {
		if (!location.getPath().contains("destroy")) {
			super.bindTexture(overrideTex);
		} else {
			super.bindTexture(location);
		}
	}

}
