package com.builtbroken.gascan.content;

import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import com.builtbroken.gascan.ClientProxy;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class GasCanModel implements IBakedModel {
	
	private IBakedModel baseModel;
	
	public GasCanModel(IBakedModel baseModel) {
		this.baseModel = baseModel;
	}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return baseModel.getQuads(state, side, rand);
	}
	
	
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType transform) {
		ModelManager manager = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager();
		ModelResourceLocation loc = transform == TransformType.GUI ? ClientProxy.mrlInventory : ClientProxy.mrl3D;
		Matrix4f mat = baseModel.handlePerspective(transform).getRight();
		mat.setScale(0.0625F);
		return Pair.of(manager.getModel(loc), mat);
	}



	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return baseModel.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}

}
