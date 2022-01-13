package com.xeross.skyutilities.helpers.messages;

import com.sun.istack.internal.Nullable;
import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.messages.api.Lang;
import com.xeross.skyutilities.helpers.messages.api.MessageAPI;
import com.xeross.skyutilities.helpers.messages.api.MessageType;
import com.xeross.skyutilities.helpers.messages.models.MessagesHolder;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageHandler implements MessageAPI {
    
    private final SkyUtilities main;
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public void onReload() {
    }
    
    public MessageHandler(SkyUtilities main) {
        this.main = main;
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                                 O messages, Player player, Lang<T, O, String> lang, @javax.annotation.Nullable String prefix) {
        String message_by_lang = langMessageHandler.getMessage(messages, lang);
        if (prefix != null) {
            player.sendMessage(prefix + message_by_lang);
            return;
        }
        player.sendMessage(message_by_lang);
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                                 O messages, Player player, Lang<T, O, String> lang,
                                                                                 @javax.annotation.Nullable String prefix, String[] keys, String[] value) {
        String message_by_lang = langMessageHandler.getMessage(messages, lang);
        String message_has_send = prefix != null ? prefix + message_by_lang : message_by_lang;
        if (keys.length == value.length) for (int i = 0; i < keys.length; i++) {
            message_has_send = message_has_send.replace(keys[i], value[i]);
        }
        player.sendMessage(message_has_send);
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessages(LangLinesHandler<T, O> langMessageHandler,
                                                                                  O messages, @NonNull Lang<T, O, ArrayList<String>> lang, Player player) {
        final ArrayList<String> message_by_lang = langMessageHandler.getMessage(messages, lang);
        message_by_lang.forEach(player::sendMessage);
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> String getMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                                  O messages,
                                                                                  @NonNull Lang<T, O, String> lang, String[] keys, String[] value) {
        String message_by_lang = langMessageHandler.getMessage(messages, lang);
        if (keys.length == value.length) for (int i = 0; i < keys.length; i++) {
            message_by_lang = message_by_lang.replace(keys[i], value[i]);
        }
        return message_by_lang;
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessages(LangLinesHandler<T, O> langMessageHandler,
                                                                                  O messages, Player player, @NonNull Lang<T, O, ArrayList<String>> lang,
                                                                                  String[] keys, String[] values) {
        final ArrayList<String> message_by_lang = langMessageHandler.getMessage(messages, lang);
        ArrayList<String> lore = new ArrayList<>(message_by_lang);
        final int size = lore.size();
        if (keys.length == values.length) for (int i = 0; i < keys.length; i++) {
            final String key = keys[i];
            final String value = values[i];
            for (int j = 0; j < size; j++) {
                String line = lore.get(j);
                if (line.contains(key)) {
                    line = line.replace(key, value);
                    lore.set(j, line);
                }
            }
        }
        lore.forEach(player::sendMessage);
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> String getMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                                  O messages, @NonNull Lang<T, O, String> lang) {
        return langMessageHandler.getMessage(messages, lang);
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> MessagesHolder getLinesWithTitle(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                                 O messages, @NonNull Lang<T, O, MessagesHolder> lang) {
        return langMessageHandler.getMessage(messages, lang);
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> MessagesHolder getLinesWithTitle(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                                 O messages, @NonNull Lang<T, O, MessagesHolder> lang,
                                                                                                 String[] keys, String[] values) {
        final MessagesHolder message_by_lang = langMessageHandler.getMessage(messages, lang);
        String title = message_by_lang.getTitle();
        ArrayList<String> lore = new ArrayList<>(message_by_lang.getLines());
        final int size = lore.size();
        if (keys.length == values.length) for (int i = 0; i < keys.length; i++) {
            final String key = keys[i];
            final String value = values[i];
            if (title.contains(key)) title = title.replace(key, value);
            for (int j = 0; j < size; j++) {
                String line = lore.get(j);
                if (line.contains(key)) {
                    line = line.replace(key, value);
                    lore.set(j, line);
                }
            }
        }
        return new MessagesHolder(title, lore);
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<Lang<T, O, MessagesHolder>, HashMap<O, MessagesHolder>>
    getAllMessages(LangLinesWithTitleHandler<T, O> langMessageHandler) {
        return langMessageHandler.getLines();
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<Lang<T, O, ArrayList<String>>, HashMap<O, ArrayList<String>>>
    getAllMessages(LangLinesHandler<T, O> langMessageHandler) {
        return langMessageHandler.getLines();
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<Lang<T, O, String>, HashMap<O, String>>
    getAllMessages(LangMessageHandler<T, O> langMessageHandler) {
        return langMessageHandler.getMessage();
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> ArrayList<String> isMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                                            @NonNull O messageType, @NonNull String message,
                                                                                            @NonNull Lang<T, O, String> lang, @Nullable String[] keys) {
        String messageDefault = getMessage(langMessageHandler, messageType, lang);
        if (keys == null) {
            if (messageDefault.equals(message)) return new ArrayList<>();
            return null;
        }
        final ArrayList<String> results = new ArrayList<>();
        String patternFromTemplate = messageDefault.replaceAll("\\.", "\\\\.").replaceAll("\\(", "").replaceAll("\\)",
                "");
        int index = 1;
        for (String key : keys) {
            if (patternFromTemplate.contains(key)) {
                patternFromTemplate = patternFromTemplate.replace(key, "(.*)");
                index++;
            }
        }
        Pattern pattern = Pattern.compile(patternFromTemplate);
        Matcher matcher = pattern.matcher(message.replaceAll("\\(", "").replaceAll("\\)", ""));
        if (!matcher.matches()) return null;
        for (int i = 1; i < index; i++) {
            results.add(matcher.group(i));
        }
        return results;
    }
    
    @Override
    public <T extends Enum<T>, O extends Enum<O> & MessageType> ArrayList<String> isMessage(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                            @NonNull O itemMessageType, @NonNull String title,
                                                                                            @NonNull List<String> lore, @NonNull Lang<T, O, MessagesHolder> lang,
                                                                                            @Nullable String[] keys) {
        MessagesHolder messageDefault = getLinesWithTitle(langMessageHandler, itemMessageType, lang);
        if (keys == null) {
            if (messageDefault.getTitle().equals(title)) return new ArrayList<>();
            return null;
        }
        final ArrayList<String> results = new ArrayList<>();
        
        String defaultTitle = messageDefault.getTitle();
        String patternFromTemplateTitle = defaultTitle.replaceAll("\\.", "\\\\.").replaceAll("\\(",
                "").replaceAll("\\)", "");
        int indexTitle = 1;
        for (String key : keys) {
            if (patternFromTemplateTitle.contains(key)) {
                patternFromTemplateTitle = patternFromTemplateTitle.replace(key, "(.*)");
                indexTitle++;
            }
        }
        Pattern patternTitle = Pattern.compile(patternFromTemplateTitle);
        Matcher matcherTitle = patternTitle.matcher(title.replaceAll("\\(", "").replaceAll("\\)", ""));
        if (!matcherTitle.matches()) return null;
        for (int i = 1; i < indexTitle; i++) {
            results.add(matcherTitle.group(i));
        }
        
        final int size = lore.size();
        for (int i = 0; i < size; i++) {
            String lores = lore.get(i);
            if (i > messageDefault.getLines().size()) return null;
            String defaultLore = messageDefault.getLines().get(i);
            String patternFromTemplate = defaultLore.replaceAll("\\.", "\\\\.").replaceAll("\\(", "").replaceAll("\\)",
                    "");
            int index = 1;
            for (String key : keys) {
                if (patternFromTemplate.contains(key)) {
                    patternFromTemplate = patternFromTemplate.replace(key, "(.*)");
                    index++;
                }
            }
            Pattern pattern = Pattern.compile(patternFromTemplate);
            Matcher matcher = pattern.matcher(lores.replaceAll("\\(", "").replaceAll("\\)", ""));
            if (!matcher.matches()) return null;
            for (int j = 1; j < index; j++) {
                results.add(matcher.group(j));
            }
        }
        return results;
    }
}
