package com.ayydxn.chatnotifications.options;

import com.ayydxn.chatnotifications.ChatNotificationsMod;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChatNotificationsOptions
{
    private static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("chat-notification-settings.json");

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingStrategy(FieldNamingPolicy.IDENTITY)
            .create();

    @SerializedName("enabled")
    public boolean isEnabled = true;

    public static ChatNotificationsOptions defaults()
    {
        return new ChatNotificationsOptions();
    }

    public static ChatNotificationsOptions load()
    {
        if (Files.exists(CONFIG_FILE))
        {
            StringBuilder configFileContents = new StringBuilder();

            try
            {
                configFileContents.append(FileUtils.readFileToString(CONFIG_FILE.toFile(), StandardCharsets.UTF_8));
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
            }

            ChatNotificationsOptions options = null;

            try
            {
                options = GSON.fromJson(configFileContents.toString(), ChatNotificationsOptions.class);
            }
            catch (JsonSyntaxException exception) // If the config file is corrupted on disk.
            {
                exception.printStackTrace();
            }

            return options;
        }
        else
        {
            ChatNotificationsMod.getLogger().warn("Failed to load Iridium's options! Loading defaults...");

            ChatNotificationsOptions defaultOptions = ChatNotificationsOptions.defaults();
            defaultOptions.write();

            return defaultOptions;
        }
    }

    public void write()
    {
        try
        {
            FileUtils.writeStringToFile(CONFIG_FILE.toFile(), GSON.toJson(this), StandardCharsets.UTF_8);
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
