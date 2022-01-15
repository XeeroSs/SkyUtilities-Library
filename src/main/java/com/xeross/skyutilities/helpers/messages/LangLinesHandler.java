package com.xeross.skyutilities.helpers.messages;

import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.messages.api.Lang;
import com.xeross.skyutilities.helpers.messages.api.MessageType;
import com.xeross.skyutilities.helpers.utils.files.utils.UTFConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LangLinesHandler<T extends Enum<T>, O extends Enum<O> & MessageType> implements MainAPI {
    
    private final HashMap<T, HashMap<O, ArrayList<String>>> lines;
    
    public HashMap<T, HashMap<O, ArrayList<String>>> getLines() {
        return lines;
    }
    
    public LangLinesHandler() {
        lines = new HashMap<>();
        onReload();
    }
    
    public ArrayList<String> getMessage(O path, T lang) {
        return lines.get(lang).get(path);
    }
    
    @SuppressWarnings({"ResultOfMethodCallIgnored", "unused"})
    public void registerMessages(Lang<T, O, ArrayList<String>> lang, File file, String prefixPath) {
        boolean fileCreated = false;
        if (!file.exists()) {
            try {
                file.createNewFile();
                fileCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
                fileCreated = true;
            }
        }
        final YamlConfiguration configuration = UTFConfig.loadConfiguration(file);
        if (fileCreated) {
            final HashMap<O, ArrayList<String>> fileMessages = new LinkedHashMap<>();
            
            for (Map.Entry<O, ArrayList<String>> value : lang.getMessages().entrySet()) {
                configuration.set(prefixPath + value.getKey().getPath(), value.getValue());
                final ArrayList<String> lines = new ArrayList<>();
                for (String line : value.getValue()) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                fileMessages.put(value.getKey(), lines);
            }
            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            lines.put(lang.getType(), fileMessages);
        }
        
        final HashMap<O, ArrayList<String>> fileMessages = new LinkedHashMap<>();
        
        for (Map.Entry<O, ArrayList<String>> value : lang.getMessages().entrySet()) {
            final List<String> message = configuration.getStringList(prefixPath + value.getKey().getPath());
            if (message == null) {
                throw new IllegalArgumentException(value.getKey().getPath() + " must be a strings list.");
            }
            final ArrayList<String> lines = new ArrayList<>();
            for (String line : message) {
                lines.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            fileMessages.put(value.getKey(), lines);
        }
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.put(lang.getType(), fileMessages);
    }
    
    @Override
    public void onDisable() {
    
    }
    
    @Override
    public void onReload() {
    
    }
}
