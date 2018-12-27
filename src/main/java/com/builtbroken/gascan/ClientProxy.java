package com.builtbroken.gascan;

import com.builtbroken.gascan.content.GasCanModel;
import com.builtbroken.gascan.content.GasCanRenderer;
import com.builtbroken.mc.fluids.FluidModule;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy
{	

	//public static final ModelResourceLocation mrlInventory = new ModelResourceLocation("sbmgascan:gascan2d", "inventory");
	public static final ModelResourceLocation mrl3D = new ModelResourceLocation("sbmgascan:models/item/gascan.obj");

	private static IModel unbakedModel;
	private static GasCanModel MODEL;

	@Override
	public void preInit() {
		super.preInit();
	}
	
	@Override
	public void postInit() {
		super.postInit();
		System.out.println("adding TEISR " + FluidModule.bucket);
		FluidModule.bucket.setTileEntityItemStackRenderer(new GasCanRenderer());
	}

	@SubscribeEvent
	public static void registerItemBlockModels(final ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain("sbmgascan");
	}

	public static GasCanModel getBakedModel() {
		if(MODEL != null) {
			return MODEL;
		}
		try {
			unbakedModel = ModelLoaderRegistry.getModel(mrl3D);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		IBakedModel bakedOld = unbakedModel.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM, location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
		MODEL = new GasCanModel(bakedOld);
		return MODEL;
	}
}
