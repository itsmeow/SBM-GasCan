package com.builtbroken.gascan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.builtbroken.mc.fluids.FluidModule;
import com.builtbroken.mc.fluids.bucket.BucketMaterial;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 3/7/2017.
 */
@Mod(modid = "sbmgascan", name = "Gas Can", version = GasCanMod.VERSION, dependencies = "after:vefluids")
public class GasCanMod
{
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    /** Information output thing */
    public static final Logger logger = LogManager.getLogger("SBM-GasCan");

    @SidedProxy(clientSide = "com.builtbroken.gascan.ClientProxy", serverSide = "com.builtbroken.gascan.CommonProxy")
    public static CommonProxy proxy;

    public static BucketMaterial gasCanBucketMaterial;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        GameRegistry.addShapedRecipe(new ResourceLocation("sbmgascan:gascan"), 
        		new ResourceLocation("sbmgascan"),
        		new ItemStack(FluidModule.bucket, 1, gasCanBucketMaterial.metaValue), new Object[] {
                "WBW",
                "I I",
                "WIW",
                'W', Blocks.WOODEN_SLAB,
                'B', Blocks.WOODEN_BUTTON,
                'I', Items.IRON_INGOT});
        proxy.postInit();
    }
    
}
