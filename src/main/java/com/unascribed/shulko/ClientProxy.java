package com.unascribed.shulko;

import com.unascribed.shulko.BlockShulkoShulkerBox.Variant;

import net.minecraft.client.model.ModelShulker;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends Proxy {

	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(this);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShulkoShulkerBox.class, new TileEntityShulkoShulkerBoxRenderer(new ModelShulker()));
	}
	
	@SubscribeEvent
	public void onModelBake(ModelBakeEvent e) {
		e.getModelRegistry().putObject(new ModelResourceLocation("shulko:actually_purple_shulker_box#inventory"), new ShulkerBoxBakedModel("actually_purple"));
		e.getModelRegistry().putObject(new ModelResourceLocation("shulko:clay_shulker_box#inventory"), new ShulkerBoxBakedModel("clay"));
	}
	
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre e) {
		e.getMap().registerSprite(new ResourceLocation("shulko:blocks/shulker_top_actually_purple"));
		e.getMap().registerSprite(new ResourceLocation("shulko:blocks/shulker_top_clay"));
	}
	
	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent e) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Shulko.ACTUALLY_PURPLE_SHULKER_BOX), 0, new ModelResourceLocation("shulko:actually_purple_shulker_box#inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Shulko.CLAY_SHULKER_BOX), 0, new ModelResourceLocation("shulko:clay_shulker_box#inventory"));
		
		ModelLoader.setCustomStateMapper(Shulko.ACTUALLY_PURPLE_SHULKER_BOX, new SimpleStateMapper(new ModelResourceLocation("shulko:actually_purple_shulker_box#inventory")));
		ModelLoader.setCustomStateMapper(Shulko.CLAY_SHULKER_BOX, new SimpleStateMapper(new ModelResourceLocation("shulko:clay_shulker_box#inventory")));
		
		Item.getItemFromBlock(Shulko.ACTUALLY_PURPLE_SHULKER_BOX).setTileEntityItemStackRenderer(new ShulkoItemRenderer(Variant.ACTUALLY_PURPLE));
		Item.getItemFromBlock(Shulko.CLAY_SHULKER_BOX).setTileEntityItemStackRenderer(new ShulkoItemRenderer(Variant.CLAY));
	}
	
}
