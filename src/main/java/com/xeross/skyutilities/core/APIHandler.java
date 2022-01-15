package com.xeross.skyutilities.core;

import com.xeross.skyutilities.GlobalAPI;
import com.xeross.skyutilities.GlobalHandler;
import com.xeross.skyutilities.core.api.MainAPI;
import com.xeross.skyutilities.helpers.blocks.BlockHandler;
import com.xeross.skyutilities.helpers.blocks.api.BlockAPI;
import com.xeross.skyutilities.helpers.gui.GUIHandler;
import com.xeross.skyutilities.helpers.gui.api.GUIAPI;
import com.xeross.skyutilities.helpers.items.ItemHandler;
import com.xeross.skyutilities.helpers.items.api.ItemAPI;
import com.xeross.skyutilities.helpers.messages.MessageHandler;
import com.xeross.skyutilities.helpers.messages.api.MessageAPI;
import com.xeross.skyutilities.helpers.nms.NMSHandler;
import com.xeross.skyutilities.helpers.nms.api.NMS;
import com.xeross.skyutilities.helpers.players.PlayerHandler;
import com.xeross.skyutilities.helpers.players.api.PlayerAPI;
import com.xeross.skyutilities.helpers.server.ServerHandler;
import com.xeross.skyutilities.helpers.server.api.ServerAPI;
import com.xeross.skyutilities.helpers.tasks.TaskHandler;
import com.xeross.skyutilities.helpers.tasks.api.TaskAPI;
import com.xeross.skyutilities.helpers.utils.UtilsHandler;
import com.xeross.skyutilities.helpers.utils.api.UtilsAPI;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class APIHandler<P extends Plugin> implements MainAPI {
    
    private final P plugin;
    private PlayerAPI playerHandler;
    private BlockAPI blockHandler;
    private UtilsAPI utilsHandler;
    private MessageAPI messageHandler;
    private GUIAPI<P> guiHandler;
    private ItemAPI itemHandler;
    private TaskAPI taskAPI;
    private NMS nmsAPI;
    private GlobalAPI globalAPI;
    private ServerAPI serverAPI;
    private final ArrayList<MainAPI> mains;
    
    public APIHandler(final P plugin) {
        this.plugin = plugin;
        this.mains = new ArrayList<>();
    }
    
    public void build() {
        taskAPI = addAPI(new TaskHandler<>(plugin));
        utilsHandler = addAPI(new UtilsHandler());
        playerHandler = addAPI(new PlayerHandler());
        blockHandler = addAPI(new BlockHandler());
        messageHandler = addAPI(new MessageHandler());
        itemHandler = addAPI(new ItemHandler());
        guiHandler = addAPI(new GUIHandler<>(plugin));
        serverAPI = addAPI(new ServerHandler());
        globalAPI = addAPI(new GlobalHandler());
        try {
            nmsAPI = addAPI(new NMSHandler().getNms());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private <I> I addAPI(final I api) {
        if (api instanceof MainAPI) mains.add(((MainAPI) api));
        return api;
    }
    
    public GlobalAPI getGlobalAPI() {
        return globalAPI;
    }
    
    @SuppressWarnings("unused")
    public ServerAPI getServerAPI() {
        return serverAPI;
    }
    
    @SuppressWarnings("unused")
    public TaskAPI getTaskAPI() {
        return taskAPI;
    }
    
    public UtilsAPI getUtilsAPI() {
        return utilsHandler;
    }
    
    @SuppressWarnings("unused")
    public BlockAPI getBlockAPI() {
        return blockHandler;
    }
    
    @SuppressWarnings("unused")
    public PlayerAPI getPlayerAPI() {
        return playerHandler;
    }
    
    public ItemAPI getItemAPI() {
        return itemHandler;
    }
    
    public GUIAPI<P> getGUIAPI() {
        return guiHandler;
    }
    
    public MessageAPI getMessageAPI() {
        return messageHandler;
    }
    
    @SuppressWarnings("unused")
    public NMS getNmsAPI() {
        return nmsAPI;
    }
    
    @Override
    public void onDisable() {
        mains.forEach(MainAPI::onDisable);
    }
    
    @Override
    public void onReload() {
        mains.forEach(MainAPI::onReload);
    }
}
