package com.ayydxn.chatnotifications;

import com.ayydxn.chatnotifications.options.ChatNotificationsOptions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ChatNotificationsMod implements ModInitializer
{
    private static ChatNotificationsMod INSTANCE;

    private static final Logger LOGGER = (Logger) LogManager.getLogger("Chat Notifications");
    private static final String MOD_ID = "chat-notifications";

    private ChatNotificationsOptions chatNotificationsOptions;

    @Override
    public void onInitialize()
    {
        INSTANCE = this;

        LOGGER.info("Initializing Chat Notifications... (Version: {})", FabricLoader.getInstance().getModContainer(MOD_ID)
                .orElseThrow().getMetadata().getVersion().getFriendlyString());

        this.chatNotificationsOptions = ChatNotificationsOptions.load();
    }

    public static ChatNotificationsMod getInstance()
    {
        if (INSTANCE == null)
            throw new IllegalStateException("Tried to access an instance of Chat Notifications before one was available!");

        return INSTANCE;
    }

    public static Logger getLogger()
    {
        return LOGGER;
    }

    public ChatNotificationsOptions getOptions()
    {
        return this.chatNotificationsOptions;
    }
}
