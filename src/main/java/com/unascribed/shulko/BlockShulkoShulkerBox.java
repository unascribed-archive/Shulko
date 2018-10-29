package com.unascribed.shulko;

import javax.annotation.Nullable;

import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockShulkoShulkerBox extends BlockShulkerBox {

	public enum Variant {
		CLAY(EnumDyeColor.SILVER),
		ACTUALLY_PURPLE(EnumDyeColor.PURPLE),
		;
		public final EnumDyeColor color;
		private Variant(EnumDyeColor color) {
			this.color = color;
		}
	}
	
	private final Variant variant;
	
	public BlockShulkoShulkerBox(Variant variant) {
		super(variant.color);
		this.variant = variant;
		setHardness(2);
		setSoundType(SoundType.STONE);
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityShulkoShulkerBox(variant);
	}
	
	@Override
	@Nullable
	public TileEntity createTileEntity(World world, IBlockState state) {
		return createNewTileEntity(world, 0);
	}

}
