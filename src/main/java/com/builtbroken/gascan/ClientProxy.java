package com.builtbroken.gascan;

import com.builtbroken.gascan.content.GasCanModel;
import com.builtbroken.mc.fluids.FluidModule;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy
{	
	
	public static final ModelResourceLocation mrlInventory = new ModelResourceLocation("sbmgascan:gascan2d", "inventory");
	public static final ModelResourceLocation mrl3D = new ModelResourceLocation("sbmgascan:gascan.obj", "inventory");
	
	@Override
	public void preInit() {
		super.preInit();
	}

	@SubscribeEvent
	public static void registerItemBlockModels(final ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain("sbmgascan");
		ModelLoader.setCustomModelResourceLocation(FluidModule.bucket, GasCanMod.gasCanBucketMaterial.metaValue, mrlInventory);
		ModelLoader.setCustomModelResourceLocation(FluidModule.bucket, GasCanMod.gasCanBucketMaterial.metaValue, mrl3D);
	}

	@SubscribeEvent
	public void modelBake(ModelBakeEvent e) {
		IBakedModel model = e.getModelRegistry().getObject(mrl3D);
		GasCanModel newModel = new GasCanModel(model);
		e.getModelRegistry().putObject(mrl3D, newModel);
	}
}
