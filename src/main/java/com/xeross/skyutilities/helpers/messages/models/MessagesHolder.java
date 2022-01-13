package com.xeross.skyutilities.helpers.messages.models;

import java.util.ArrayList;

public class MessagesHolder {

    private final ArrayList<String> lines;
    private final String title;

    public MessagesHolder(String title, ArrayList<String> lines) {
        this.lines = lines;
        this.title = title;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public String getTitle() {
        return title;
    }
}
