package com.xeross.skyutilities.helpers.utils.files;

import com.xeross.skyutilities.helpers.utils.files.api.YamlFileAPI;
import com.xeross.skyutilities.helpers.utils.files.api.YamlFileDataAPI;
import com.xeross.skyutilities.helpers.utils.files.utils.UTFConfig;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author XeroSs
 * @version 1.0.0
 */
public abstract class YamlFileUtils<E extends Enum<E> & YamlFileDataAPI> implements YamlFileAPI<E> {
    
    private File file;
    private String parentFile;
    private final String prefixPath;
    private Map<E, Object> objects;
    private Class<E> clazz;
    private final Class<?> typeDataForAll;
    
    @Override
    public Map<E, Object> get() {
        return objects;
    }
    
    public YamlFileUtils(final Class<E> clazz, final String parentFile, final String file, Plugin main) {
        this.typeDataForAll = null;
        this.prefixPath = "";
        build(main, clazz, parentFile, file);
    }
    
    public YamlFileUtils(final Class<E> clazz, final String parentFile, final String file, Plugin main, final String prefixPath, Class<?> typeDataForAll) {
        this.typeDataForAll = typeDataForAll;
        this.prefixPath = prefixPath;
        build(main, clazz, parentFile, file);
    }
    
    private void build(Plugin main, Class<E> clazz, final String parentFile, final String file) {
        this.clazz = clazz;
        this.file = new File(parentFile, file);
        this.parentFile = parentFile;
        this.objects = new HashMap<>();
        register();
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private Boolean createFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }
    
    private void saveConfig(final YamlConfiguration configuration) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createParentFile() {
        final File file = new File(parentFile);
        if (!file.exists()) file.mkdirs();
    }
    
    protected void register() {
        
        createParentFile();
        
        boolean fileCreated = createFile();
        
        // Consider the UTF8
        final YamlConfiguration configuration = UTFConfig.loadConfiguration(file);
        
        final E[] enumObjects = clazz.getEnumConstants();
        
        if (fileCreated) {
            for (E enumObject : enumObjects) {
                final String path = enumObject.getPath();
                if (path == null) continue;
                final Object defaultValue = enumObject.getDefaultValue();
                configuration.set(this.prefixPath + path, defaultValue);
                objects.put(enumObject, defaultValue);
            }
            saveConfig(configuration);
            return;
        }
        
        for (E enumObject : enumObjects) {
            if (enumObject.getPath() == null) continue;
            final Object yamlObject = configuration.get(this.prefixPath + enumObject.getPath());
            final Class<?> dataType = typeDataForAll == null ? enumObject.getDataType() : typeDataForAll;
            if (yamlObject == null || !(yamlObject.getClass().isAssignableFrom(dataType))) {
                configuration.set(this.prefixPath + enumObject.getPath(), enumObject.getDefaultValue());
                objects.put(enumObject, enumObject.getDefaultValue());
                continue;
            }
            objects.put(enumObject, yamlObject);
        }
        saveConfig(configuration);
    }
    
    protected abstract void reload();
    
}
