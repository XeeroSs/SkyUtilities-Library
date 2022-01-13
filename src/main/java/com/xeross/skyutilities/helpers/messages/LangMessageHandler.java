package com.xeross.skyutilities.helpers.messages;

import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.messages.api.Lang;
import com.xeross.skyutilities.helpers.messages.api.MessageType;
import com.xeross.skyutilities.helpers.utils.files.utils.UTFConfig;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LangMessageHandler<T extends Enum<T>, O extends Enum<O> & MessageType> implements MainAPI {
    
    private final HashMap<Lang<T, O, String>, HashMap<O, String>> message;
    
    public HashMap<Lang<T, O, String>, HashMap<O, String>> getMessage() {
        return message;
    }
    
    public LangMessageHandler() {
        message = new HashMap<>();
        onReload();
    }
    
    public String getMessage(O path, Lang<T, O, String> lang) {
        return message.get(lang).get(path);
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void register(Lang<T, O, String> lang, File file, String prefixPath) {
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
        final LinkedHashMap<O, String> fileMessages = new LinkedHashMap<>();
        if (fileCreated) {
            Map<O, String> defaultLocaleStrings = lang.getMessages();
            for (Map.Entry<O, String> messageTypeStringEntry : defaultLocaleStrings.entrySet()) {
                String translated = StringEscapeUtils.unescapeJava(messageTypeStringEntry.getValue());
                configuration.set(prefixPath + messageTypeStringEntry.getKey().getPath(), translated);
                fileMessages.put(messageTypeStringEntry.getKey(),
                        ChatColor.translateAlternateColorCodes('&', translated));
            }
            message.put(lang, fileMessages);
            try {
                configuration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        
        for (Map.Entry<O, String> value : lang.getMessages().entrySet()) {
            final Object message = configuration.get(prefixPath + value.getKey().getPath());
            if (!(message instanceof String)) {
                fileMessages.put(value.getKey(), ChatColor.translateAlternateColorCodes('&', value.getValue()));
                String translated = StringEscapeUtils.unescapeJava(value.getValue());
                configuration.set(prefixPath + value.getKey().getPath(), translated);
                continue;
            }
            fileMessages.put(value.getKey(), ChatColor.translateAlternateColorCodes('&', String.valueOf(message)));
        }
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.put(lang, fileMessages);
    }
    
    @Override
    public void onDisable() {
    
    }
    
    @Override
    public void onReload() {
    
    }
}
