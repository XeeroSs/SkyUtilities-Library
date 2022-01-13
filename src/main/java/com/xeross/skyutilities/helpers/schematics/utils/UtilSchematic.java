package com.xeross.skyutilities.helpers.schematics.utils;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.schematics.api.NMSAbstraction;

import java.lang.reflect.InvocationTargetException;

public class UtilSchematic {
    
    public static NMSAbstraction checkVersion(SkyUtilities main) throws ClassNotFoundException, IllegalArgumentException,
            SecurityException, InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        String pluginPackageName = main.getClass().getPackage().getName();
        Class<?> clazz;
        try {
            clazz = Class.forName(pluginPackageName + ".helper.schematic.nms.NMSHandler");
        } catch (Exception e) {
            clazz = Class.forName(pluginPackageName + ".helper.schematic.nms.NMSHandler");
        }
        // Check if we have a NMSAbstraction implementing class at that location.
        if (NMSAbstraction.class.isAssignableFrom(clazz)) {
            return (NMSAbstraction) clazz.getConstructor().newInstance();
        } else {
            throw new IllegalStateException("Class " + clazz.getName() + " does not implement NMSAbstraction");
        }
    }
    
}
