package com.unascribed.shulko;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unascribed.shulko.BlockShulkoShulkerBox.Variant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid=Shulko.MODID, name=Shulko.NAME, version=Shulko.VERSION)
public class Shulko {
	public static final String MODID = "shulko";
	public static final String NAME = "Shulko";
	public static final String VERSION = "@VERSION@";
	
	public static final Logger log = LogManager.getLogger(NAME);
	
	@SidedProxy(clientSide="com.unascribed.shulko.ClientProxy", serverSide="com.unascribed.shulko.Proxy")
	public static Proxy proxy;
	
	public static boolean enableClayShulkerBox = false;
	
	public static BlockShulkoShulkerBox CLAY_SHULKER_BOX;
	public static BlockShulkoShulkerBox ACTUALLY_PURPLE_SHULKER_BOX;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent e) {
		Configuration cfg = new Configuration(e.getSuggestedConfigurationFile());
		cfg.load();
		enableClayShulkerBox = cfg.getBoolean("balance", "General", false, "it's balanced because it takes... oh forget it");
		cfg.save();
		proxy.preInit();
		
		GameRegistry.registerTileEntity(TileEntityShulkoShulkerBox.class, new ResourceLocation("shulko", "shulker_box"));
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent e) {
		if (enableClayShulkerBox) {
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Item.getItemFromBlock(CLAY_SHULKER_BOX),
					BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(Item.getItemFromBlock(Blocks.PURPLE_SHULKER_BOX)));
		}
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Item.getItemFromBlock(ACTUALLY_PURPLE_SHULKER_BOX),
				BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(Item.getItemFromBlock(Blocks.PURPLE_SHULKER_BOX)));
	}
	
	@SubscribeEvent
	public void onInteract(PlayerInteractEvent.RightClickBlock e) {
		IBlockState ibs = e.getWorld().getBlockState(e.getPos());
		if (ibs.getBlock() == Blocks.CAULDRON && ibs.getValue(BlockCauldron.LEVEL) > 0) {
			ItemStack is = e.getItemStack();
			if (is.getItem() instanceof ItemShulkerBox) {
				if (enableClayShulkerBox && is.getItem() == Item.getItemFromBlock(CLAY_SHULKER_BOX)) return;
				if (is.getItem() == Item.getItemFromBlock(Blocks.PURPLE_SHULKER_BOX)) return;
				e.setUseBlock(Result.ALLOW);
				e.setUseItem(Result.DENY);
				e.getWorld().setBlockState(e.getPos(), ibs.withProperty(BlockCauldron.LEVEL, ibs.getValue(BlockCauldron.LEVEL)-1));
				NBTTagCompound nbt = is.serializeNBT();
				nbt.setString("id", "minecraft:purple_shulker_box");
				ItemStack nw = new ItemStack(nbt);
				e.setCanceled(true);
				e.setCancellationResult(EnumActionResult.SUCCESS);
				e.getEntityPlayer().setHeldItem(e.getHand(), nw);
			}
		}
	}
	
	@SubscribeEvent
	public void onRegisterRecipes(RegistryEvent.Register<IRecipe> e) {
		log.warn("This is an intended override. Shush, Forge.");
		e.getRegistry().register(new RecipeShulkoShulkerBoxColoring().setRegistryName("minecraft", "shulkerboxcoloring"));
		if (enableClayShulkerBox) {
			try {
				ItemStack is = new ItemStack(CLAY_SHULKER_BOX);
				is.setTagCompound(JsonToNBT.getTagFromJson("{BlockEntityTag:{Items:[{Slot: 0b,id:\"minecraft:clay\",Count:1b,Damage:0s}]}}"));
				e.getRegistry().register(new ShapedOreRecipe(null, is,
						"s",
						"c",
						"s",
						
						'c', Blocks.CLAY,
						's', Items.SHULKER_SHELL)
					.setRegistryName("clay_shulker_box"));
			} catch (NBTException ex) {
				throw new AssertionError(ex);
			}
		}
	}
	
	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> e) {
		Blocks.PURPLE_SHULKER_BOX.setTranslationKey("shulko.undyed_shulker_box");
		if (enableClayShulkerBox) {
			e.getRegistry().register(CLAY_SHULKER_BOX = (BlockShulkoShulkerBox)new BlockShulkoShulkerBox(Variant.CLAY)
					.setTranslationKey("shulko.clay_shulker_box").setRegistryName("clay_shulker_box"));
		}
		e.getRegistry().register(ACTUALLY_PURPLE_SHULKER_BOX = (BlockShulkoShulkerBox)new BlockShulkoShulkerBox(Variant.ACTUALLY_PURPLE)
				.setTranslationKey("shulkerBoxPurple").setRegistryName("actually_purple_shulker_box"));
	}
	
	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> e) {
		log.warn("These are intended overrides. Shush, Forge.");
		e.getRegistry().register(new ItemSubbedShulkerBox(Blocks.WHITE_SHULKER_BOX)
				.setRegistryName("minecraft", "white_shulker_box"));
		e.getRegistry().register(new ItemUndyedShulkerBox(Blocks.PURPLE_SHULKER_BOX)
				.setRegistryName("minecraft", "purple_shulker_box"));
		for (EnumDyeColor color : EnumDyeColor.values()) {
			if (color == EnumDyeColor.WHITE) continue;
			if (color == EnumDyeColor.PURPLE) continue;
			e.getRegistry().register(new ItemSublessShulkerBox(BlockShulkerBox.getBlockByColor(color))
					.setRegistryName("minecraft", color.getName()+"_shulker_box"));
		}
		log.warn("End intended overrides.");
		
		if (enableClayShulkerBox) {
			e.getRegistry().register(new ItemClayShulkerBox(CLAY_SHULKER_BOX)
					.setRegistryName("clay_shulker_box"));
		}
		e.getRegistry().register(new ItemSublessShulkerBox(ACTUALLY_PURPLE_SHULKER_BOX)
				.setRegistryName("actually_purple_shulker_box"));
	}
	
}
