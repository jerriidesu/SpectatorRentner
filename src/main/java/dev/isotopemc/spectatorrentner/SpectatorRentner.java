package dev.isotopemc.spectatorrentner;

import com.google.common.base.MoreObjects;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.GameType;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class SpectatorRentner implements ClientModInitializer {
    private static KeyMapping switchKeybind;

    @Override
    public void onInitializeClient() {
        switchKeybind = KeyBindingHelper.registerKeyBinding(new KeyMapping(new TranslatableComponent("rentner.keybind.switch").getString(), InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, new TranslatableComponent("rentner.keybinds").getString()));
    }

    public static void handleKeybinds(Minecraft minecraft) {
        if(switchKeybind.consumeClick()) {
            try {
                if (!minecraft.player.hasPermissions(2)) {
                    minecraft.player.displayClientMessage(new TranslatableComponent("debug.creative_spectator.error"),false);
                } else if (!minecraft.player.isSpectator()) {
                    minecraft.player.chat("/gamemode spectator");
                } else {
                    minecraft.player.chat("/gamemode " + MoreObjects.firstNonNull(minecraft.gameMode.getPreviousPlayerMode(), GameType.CREATIVE).getName());
                }
            } catch(Exception e) {
                System.out.println("OH NO!");
            }
        }
    }
}
