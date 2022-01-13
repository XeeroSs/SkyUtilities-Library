package com.xeross.skyutilities.helpers.messages;

import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.messages.api.Lang;
import com.xeross.skyutilities.helpers.messages.api.MessageType;
import com.xeross.skyutilities.helpers.messages.models.MessagesHolder;
import com.xeross.skyutilities.helpers.utils.files.utils.UTFConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LangLinesWithTitleHandler<T extends Enum<T>, O extends Enum<O> & MessageType> implements MainAPI {
    
    private final HashMap<Lang<T, O, MessagesHolder>, HashMap<O, MessagesHolder>> lines;
    
    public HashMap<Lang<T, O, MessagesHolder>, HashMap<O, MessagesHolder>> getLines() {
        return lines;
    }
    
    public LangLinesWithTitleHandler() {
        lines = new HashMap<>();
        onReload();
    }
    
    public MessagesHolder getMessage(O path, Lang<T, O, MessagesHolder> lang) {
        return lines.get(lang).get(path);
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void registerLinesWithTitle(Lang<T, O, MessagesHolder> lang, File file, String prefixPath) throws Exception {
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
            final HashMap<O, MessagesHolder> fileMessages = new LinkedHashMap<>();
            
            for (Map.Entry<O, MessagesHolder> value : lang.getMessages().entrySet()) {
                configuration.set(prefixPath + value.getKey().getPath() + ".title", value.getValue().getTitle());
                configuration.set(prefixPath + value.getKey().getPath() + ".lines", value.getValue().getLines());
                final ArrayList<String> lines = new ArrayList<>();
                for (String line : value.getValue().getLines()) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                fileMessages.put(value.getKey(),
                        new MessagesHolder(ChatColor.translateAlternateColorCodes('&', value.getValue().getTitle()),
                                lines));
            }
            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            lines.put(lang, fileMessages);
        }
        
        final HashMap<O, MessagesHolder> fileMessages = new LinkedHashMap<>();
        
        for (Map.Entry<O, MessagesHolder> value : lang.getMessages().entrySet()) {
            final String title = configuration.getString(prefixPath + value.getKey().getPath() + ".title");
            if (title == null) {
                throw new IllegalArgumentException(value.getKey().getPath() + ".title must be a string.");
            }
            final List<String> message = configuration.getStringList(prefixPath + value.getKey().getPath() + ".lines");
            if (message == null) {
                throw new IllegalArgumentException(value.getKey().getPath() + " must be a strings list.");
            }
            final ArrayList<String> lines = new ArrayList<>();
            for (String line : message) {
                lines.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            fileMessages.put(value.getKey(),
                    new MessagesHolder(ChatColor.translateAlternateColorCodes('&', title), lines));
        }
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.put(lang, fileMessages);
    }
    
    @Override
    public void onDisable() {
    
    }
    
    @Override
    public void onReload() {
    
    }
}
