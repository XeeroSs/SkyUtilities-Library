package com.xeross.skyutilities.helpers.server;

import com.xeross.skyutilities.SkyUtilities;
import com.xeross.skyutilities.helpers.server.api.ServerAPI;

public class ServerHandler implements ServerAPI {

    private final SkyUtilities main;

    public ServerHandler(SkyUtilities main) {
        this.main = main;
    }

    @Override
    public void onReload() {
    }

    @Override
    public void onDisable() {
    }
}
