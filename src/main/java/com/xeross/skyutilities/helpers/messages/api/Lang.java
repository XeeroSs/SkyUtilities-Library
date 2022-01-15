package com.xeross.skyutilities.helpers.messages.api;

import java.util.LinkedHashMap;

public interface Lang<T extends Enum<T>, O extends Enum<O>, V> {
    
    String getName();
    LinkedHashMap<O, V> getMessages();
    T getType();
}
