package com.github.kaaz.kaaditionz;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MODID, version = Constants.VERSION)
public class KaaditionzMod {
    @Mod.Instance(Constants.MODID)
    public static KaaditionzMod instance = new KaaditionzMod();


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Cfg.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());
    }
}
