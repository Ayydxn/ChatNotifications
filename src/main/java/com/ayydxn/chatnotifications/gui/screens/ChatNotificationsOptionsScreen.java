package com.ayydxn.chatnotifications.gui.screens;

import com.ayydxn.chatnotifications.ChatNotificationsMod;
import com.ayydxn.chatnotifications.options.ChatNotificationsOptions;
import com.google.common.collect.Lists;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChatNotificationsOptionsScreen
{
    private final ChatNotificationsOptions notificationsOptions;
    private final Screen previousScreen;

    public ChatNotificationsOptionsScreen(@Nullable Screen previousScreen)
    {
        this.notificationsOptions = ChatNotificationsMod.getInstance().getOptions();
        this.previousScreen = previousScreen;
    }

    public Screen getHandle()
    {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("chat-notifications.options.gui_title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("chat-notifications.options.category.chat_notifications"))
                        .options(this.getOptions())
                        .build())
                .save(() -> ChatNotificationsMod.getInstance().getOptions().write())
                .build()
                .generateScreen(this.previousScreen);
    }

    private List<Option<?>> getOptions()
    {
        Option<Boolean> enabledOption = Option.<Boolean>createBuilder()
                .name(Text.translatable("chat-notifications.options.chat_notifications.enabled"))
                .description(OptionDescription.of(Text.translatable("chat-notifications.options.chat_notifications.enabled.description")))
                .binding(ChatNotificationsOptions.defaults().isEnabled, () -> this.notificationsOptions.isEnabled, value -> this.notificationsOptions.isEnabled = value)
                .customController(BooleanController::new)
                .build();

        return Lists.newArrayList(enabledOption);
    }
}
