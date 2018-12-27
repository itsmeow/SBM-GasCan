package com.builtbroken.gascan.content;

import com.builtbroken.gascan.ClientProxy;
import com.builtbroken.gascan.GasCanMod;
import com.builtbroken.mc.fluids.FluidModule;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.FMLClientHandler;

public class GasCanRenderer extends TileEntityItemStackRenderer {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation("sbmgascan", "models/gascan");
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		if(itemStackIn.getItem() == FluidModule.bucket && itemStackIn.getMetadata() == GasCanMod.gasCanBucketMaterial.metaValue) { // Is the gas can
			GlStateManager.pushMatrix();
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURE);

			if (type == ItemRenderType.EQUIPPED)
			{
				GlStateManager.rotate(-40, 0, 1, 0);
				//GlStateManager.glRotatef(13, 1, 0, 0);
				GlStateManager.translate(1f, -1.1f, 0.1f);

				final float scale = 0.0625f / 4;
				GlStateManager.scale(scale, scale, scale);
				Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, ClientProxy.MODEL);
			}
			else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
			{
				GlStateManager.rotate(-90, 0, 1, 0);
				//GlStateManager.glRotatef(-13, 1, 0, 0);
				GlStateManager.translate(1f, 0.6f, 0.5f);

				final float scale = 0.0625f / 5;
				GlStateManager.scale(scale, scale, scale);
				Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, ClientProxy.MODEL);
			}
			else if (type == ItemRenderType.ENTITY)
			{
				GlStateManager.translate(0f, 0, 0);

				final float scale = 0.0625f / 4;
				GlStateManager.scale(scale, scale, scale);
				Minecraft.getMinecraft().getRenderItem().renderItem(itemStackIn, ClientProxy.MODEL);
			}

			GlStateManager.popMatrix();
		}
	}

}
