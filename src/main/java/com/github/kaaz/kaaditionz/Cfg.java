package com.github.kaaz.kaaditionz;

import com.github.kaaz.kaaditionz.config.Comment;
import com.github.kaaz.kaaditionz.config.ConfigManager;
import com.github.kaaz.kaaditionz.config.Ignore;
import com.google.common.base.Joiner;
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
            if (sub.getAnnotation(Ignore.class) != null) {
                continue;
            }
            String category = sub.getSimpleName();
            Comment catComment = sub.getAnnotation(Comment.class);
            if (catComment != null) {
                config.getCategory(category).setComment(Joiner.on("\n").join(catComment.value()));
            }
            for (Field field : sub.getFields()) {
                if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isPublic(field.getModifiers())) {
                    continue;
                }
                if (field.getAnnotation(Ignore.class) != null) {
                    continue;
                }
                ConfigManager.createConfig(Constants.MODID, category, config, field.getType(), field, null);
            }
        }
        config.save();
    }

    @Comment({"General properties"})
    public static class General {
        @Comment({"Debug mode activated?"})
        public static boolean debug = false;
        @Comment({"this is some multiplier"})
        public static int some_multiplier = 3;
    }

    @Comment({"only applicable in special cases"})
    public static class Extra {
        @Comment({"extra variable unused, mostly for testing the cfg file"})
        public static String some_var = "123";
        @Comment({"some arbitrary multiplier"})
        public static double extra_multi = 0.1d;
    }

}
