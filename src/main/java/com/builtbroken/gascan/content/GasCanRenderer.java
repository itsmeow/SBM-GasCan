package com.builtbroken.gascan.content;

import com.builtbroken.gascan.ClientProxy;
import com.builtbroken.gascan.GasCanMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class GasCanRenderer extends TileEntityItemStackRenderer {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation("sbmgascan", "models/gascan");
	
	@Override
	public void renderByItem(ItemStack itemStackIn, float partialTicks) {
		System.out.println(itemStackIn.getMetadata());
		if(itemStackIn.getMetadata() == GasCanMod.gasCanBucketMaterial.metaValue) { // Is the gas can
			GlStateManager.pushMatrix();
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE);
			GasCanModel MODEL = ClientProxy.getBakedModel();
			TransformType type = MODEL.transform;
			System.out.println(type.name());
			if (type == TransformType.THIRD_PERSON_RIGHT_HAND)
			{
				GlStateManager.rotate(-40, 0, 1, 0);
				//GlStateManager.glRotatef(13, 1, 0, 0);
				GlStateManager.translate(1f, -1.1f, 0.1f);

				final float scale = 0.0625f / 4;
				GlStateManager.scale(scale, scale, scale);
				Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, MODEL);
			}
			else if (type == TransformType.FIRST_PERSON_RIGHT_HAND)
			{
				GlStateManager.rotate(-90, 0, 1, 0);
				//GlStateManager.glRotatef(-13, 1, 0, 0);
				GlStateManager.translate(1f, 0.6f, 0.5f);

				final float scale = 0.0625f / 5;
				GlStateManager.scale(scale, scale, scale);
				Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, MODEL);
			}
			else if (type == TransformType.GROUND)
			{
				GlStateManager.translate(0f, 0, 0);

				final float scale = 0.0625f / 4;
				GlStateManager.scale(scale, scale, scale);
				Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, MODEL);
			}

			GlStateManager.popMatrix();
		}
	}

}
