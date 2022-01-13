package com.xeross.skyutilities.helpers.server.models;

public class Server {
    private boolean isLoad;

    public Server(boolean isLoad) {
        this.isLoad = isLoad;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }
}
