package dev.isotopemc.spectatorrentner.mixin;

import dev.isotopemc.spectatorrentner.SpectatorRentner;
import net.minecraft.client.Minecraft;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow private ProfilerFiller profiler;

    @Inject(method = "tick()V", at = @At("RETURN"))
    private void rentner$onTick(CallbackInfo ci) {
        this.profiler.push("rentner_keybinds");

        SpectatorRentner.handleKeybinds((Minecraft) (Object) this);

        this.profiler.pop();
    }
}
