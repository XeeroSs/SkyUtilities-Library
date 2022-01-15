package com.xeross.skyutilities.helpers.messages.api;


import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.messages.LangLinesHandler;
import com.xeross.skyutilities.helpers.messages.LangLinesWithTitleHandler;
import com.xeross.skyutilities.helpers.messages.LangMessageHandler;
import com.xeross.skyutilities.helpers.messages.models.MessagesHolder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public interface MessageAPI extends MainAPI {
    
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                          O messages, Player player, T lang, @javax.annotation.Nullable String prefix);
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                          O messages, Player player, T lang,
                                                                          @javax.annotation.Nullable String prefix, String[] keys, String[] value);
    <T extends Enum<T>, O extends Enum<O> & MessageType> ArrayList<String> isMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                                     O messageType, String message,
                                                                                     T lang, String[] keys);
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessages(LangLinesHandler<T, O> langMessageHandler,
                                                                           O messages, T lang, Player player);
    <T extends Enum<T>, O extends Enum<O> & MessageType> String getMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                           O messages,
                                                                           T lang, String[] keys, String[] value);
    <T extends Enum<T>, O extends Enum<O> & MessageType> void sendMessages(LangLinesHandler<T, O> langMessageHandler,
                                                                           O messages, Player player, T langType,
                                                                           String[] keys, String[] values);
    <T extends Enum<T>, O extends Enum<O> & MessageType> String getMessage(LangMessageHandler<T, O> langMessageHandler,
                                                                           O messages, T lang);
    <T extends Enum<T>, O extends Enum<O> & MessageType> MessagesHolder getLinesWithTitle(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                          O messages, T lang);
    <T extends Enum<T>, O extends Enum<O> & MessageType> MessagesHolder getLinesWithTitle(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                          O messages, T lang,
                                                                                          String[] keys, String[] values);
    <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<T, HashMap<O, MessagesHolder>>
    getAllMessages(LangLinesWithTitleHandler<T, O> langMessageHandler);
    <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<T, HashMap<O, ArrayList<String>>>
    getAllMessages(LangLinesHandler<T, O> langMessageHandler);
    <T extends Enum<T>, O extends Enum<O> & MessageType> HashMap<T, HashMap<O, String>>
    getAllMessages(LangMessageHandler<T, O> langMessageHandler);
    <T extends Enum<T>, O extends Enum<O> & MessageType> ArrayList<String> isMessage(LangLinesWithTitleHandler<T, O> langMessageHandler,
                                                                                     O itemMessageType, String title,
                                                                                     List<String> lore, T lang,
                                                                                     String[] keys);
}
