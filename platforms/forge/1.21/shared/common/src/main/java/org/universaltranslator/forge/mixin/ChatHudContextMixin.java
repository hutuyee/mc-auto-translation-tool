package org.universaltranslator.forge.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.universaltranslator.core.TextKind;
import org.universaltranslator.forge.TranslationRenderContext;

@Mixin(value = ChatComponent.class, remap = false)
abstract class ChatHudContextMixin {
    @Group(name = "universalTranslator$enterChat", min = 1, max = 1)
    @Inject(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIIZ)V",
            at = @At("HEAD"),
            require = 0)
    private void universalTranslator$enterLegacyChat(
            GuiGraphics context,
            int currentTick,
            int mouseX,
            int mouseY,
            boolean chatFocused,
            CallbackInfo callback) {
        TranslationRenderContext.push(TextKind.CHAT);
    }

    @Group(name = "universalTranslator$enterChat", min = 1, max = 1)
    @Inject(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Font;IIIZZ)V",
            at = @At("HEAD"),
            require = 0)
    private void universalTranslator$enterModernChat(
            GuiGraphics context,
            Font font,
            int currentTick,
            int mouseX,
            int mouseY,
            boolean chatFocused,
            boolean hidden,
            CallbackInfo callback) {
        TranslationRenderContext.push(TextKind.CHAT);
    }

    @Group(name = "universalTranslator$leaveChat", min = 1, max = 1)
    @Inject(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIIZ)V",
            at = @At("RETURN"),
            require = 0)
    private void universalTranslator$leaveLegacyChat(
            GuiGraphics context,
            int currentTick,
            int mouseX,
            int mouseY,
            boolean chatFocused,
            CallbackInfo callback) {
        TranslationRenderContext.pop();
    }

    @Group(name = "universalTranslator$leaveChat", min = 1, max = 1)
    @Inject(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Font;IIIZZ)V",
            at = @At("RETURN"),
            require = 0)
    private void universalTranslator$leaveModernChat(
            GuiGraphics context,
            Font font,
            int currentTick,
            int mouseX,
            int mouseY,
            boolean chatFocused,
            boolean hidden,
            CallbackInfo callback) {
        TranslationRenderContext.pop();
    }
}
