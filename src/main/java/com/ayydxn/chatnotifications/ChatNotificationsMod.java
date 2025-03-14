package com.ayydxn.chatnotifications;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ChatNotificationsMod implements ModInitializer
{
    private static final Logger LOGGER = (Logger) LogManager.getLogger("Chat Notifications");
    private static final String MOD_ID = "chat-notifications";

    @Override
    public void onInitialize()
    {
        LOGGER.info("Initializing Chat Notifications... (Version: {})", FabricLoader.getInstance().getModContainer(MOD_ID)
                .orElseThrow().getMetadata().getVersion().getFriendlyString());
    }
}
