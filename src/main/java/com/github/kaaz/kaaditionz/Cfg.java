package com.github.kaaz.kaaditionz;

import com.github.kaaz.kaaditionz.Config.ConfigManager;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Cfg {
    private static Configuration config;

    public static void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        for (Class<?> sub : Cfg.class.getClasses()) {
            String category = sub.getSimpleName();
            for (Field field : sub.getFields()) {
                if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isPublic(field.getModifiers())) {
                    continue;
                }
                ConfigManager.createConfig(Constants.MODID, category, config, field.getType(), field, null);
            }
        }
        config.save();
    }

    public static class General {
        @Config.Comment({"Debug mode activated?"})
        public static boolean debug = false;
        @Config.Comment({"this is some multiplier"})
        public static int some_multiplier = 3;
    }

    public static class Extra {
        @Config.Comment({"extra variable unused, mostly for testing the cfg file"})
        public static String some_var = "123";
        @Config.Comment({"some arbitrary multiplier"})
        public static double extra_multi = 0.1d;
    }

}
