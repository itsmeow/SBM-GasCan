package com.builtbroken.gascan.content;

import com.builtbroken.mc.fluids.bucket.BucketMaterial;

import net.minecraft.util.ResourceLocation;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 3/7/2017.
 */
public class BucketMaterialCan extends BucketMaterial
{
    public BucketMaterialCan(String localization, ResourceLocation textureName)
    {
        super(localization, textureName);
        preventHotFluidUsage = false;
        damageBucketWithHotFluid = false;
        burnEntityWithHotFluid = false;
    }
}
