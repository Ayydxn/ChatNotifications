package com.ayydxn.chatnotifications.mixin;

import com.ayydxn.chatnotifications.gui.screens.ChatNotificationsOptionsScreen;
import com.google.common.base.Preconditions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ChatOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatOptionsScreen.class)
public abstract class ChatOptionsScreenMixin extends GameOptionsScreen
{
    public ChatOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title)
    {
        super(parent, gameOptions, title);
    }

    @Inject(method = "addOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/OptionListWidget;addAll([Lnet/minecraft/client/option/SimpleOption;)V", shift = At.Shift.AFTER))
    public void addChatNotificationsOptionsButton(CallbackInfo ci)
    {
        Preconditions.checkNotNull(this.body);

        this.body.addWidgetEntry(ButtonWidget.builder(Text.translatable("chat-notifications.options.gui_title"), this::onOptionsButtonPress).build(), null);
    }

    @Unique
    private void onOptionsButtonPress(ButtonWidget buttonWidget)
    {
        Preconditions.checkNotNull(this.client);

        this.client.setScreen(new ChatNotificationsOptionsScreen(this).getHandle());
    }
}
