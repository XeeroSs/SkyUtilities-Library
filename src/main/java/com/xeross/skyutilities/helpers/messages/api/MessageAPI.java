package com.xeross.skyutilities.helpers.messages.api;

import com.sun.istack.internal.Nullable;
import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.messages.LangLinesHandler;
import com.xeross.skyutilities.helpers.messages.LangLinesWithTitleHandler;
import com.xeross.skyutilities.helpers.messages.LangMessageHandler;
import com.xeross.skyutilities.helpers.messages.models.MessagesHolder;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MessageAPI extends MainAPI {
    
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                          O messages, Player player, Lang<T, O, String> lang, @javax.annotation.Nullable String prefix);
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                          O messages, Player player, Lang<T, O, String> lang,
                                                                          @javax.annotation.Nullable String prefix, String[] keys, String[] value);
    <T extends Enum<T>, O extends Enum<O> & MessageType> ArrayList<String> isMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                                     @NonNull O messageType, @NonNull String message,
                                                                                     @NonNull Lang<T, O, String> lang, @Nullable String[] keys);
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessages(LangLinesHandler<T, O> langMessageHandler,
                                                                           O messages, @NonNull Lang<T, O, ArrayList<String>> lang, Player player);
    <T extends Enum<T>, O extends Enum<O> & MessageType> String getMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                           O messages,
                                                                           @NonNull Lang<T, O, String> lang, String[] keys, String[] value);
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessages(LangLinesHandler<T, O> langMessageHandler,
                                                                           O messages, Player player, @NonNull Lang<T, O, ArrayList<String>> langType,
                                                                           String[] keys, String[] values);
    <T extends Enum<T>, O extends Enum<O> & MessageType> String getMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                           O messages, @NonNull Lang<T, O, String> lang);
    <T extends Enum<T>, O extends Enum<O> & MessageType> MessagesHolder getLinesWithTitle(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                          O messages, @NonNull Lang<T, O, MessagesHolder> lang);
    <T extends Enum<T>, O extends Enum<O> & MessageType> MessagesHolder getLinesWithTitle(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                          O messages, @NonNull Lang<T, O, MessagesHolder> lang,
                                                                                          String[] keys, String[] values);
    <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<Lang<T, O, MessagesHolder>, HashMap<O, MessagesHolder>>
    getAllMessages(LangLinesWithTitleHandler<T, O> langMessageHandler);
    <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<Lang<T, O, ArrayList<String>>, HashMap<O, ArrayList<String>>>
    getAllMessages(LangLinesHandler<T, O> langMessageHandler);
    <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<Lang<T, O, String>, HashMap<O, String>>
    getAllMessages(LangMessageHandler<T, O> langMessageHandler);
    <T extends Enum<T>, O extends Enum<O> & MessageType> ArrayList<String> isMessage(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                     @NonNull O itemMessageType, @NonNull String title,
                                                                                     @NonNull List<String> lore, @NonNull Lang<T, O, MessagesHolder> lang,
                                                                                     @Nullable String[] keys);
}
