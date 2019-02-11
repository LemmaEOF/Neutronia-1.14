package team.abnormals.neutronia.mixin.client.progress;

import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.abnormals.neutronia.LoadingProgressImpl;

@Mixin(SpriteAtlasTexture.class)
public abstract class MixinSpriteAtlasTexture {

    @Shadow
    protected abstract Identifier getTexturePath(Identifier identifier_1);

    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;)V", at = @At("HEAD"))
    private void startReload(ResourceManager resourceManager_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName("Building sprite atlas");
    }

    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;)V", at = @At("RETURN"))
    private void endReload(ResourceManager resourceManager_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.popTask();
    }

    /*@Inject(
        method = "load(Lnet/minecraft/resource/ResourceManager;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/SpriteAtlasTexture;getTexturePath(Lnet/minecraft/util/Identifier;)Lnet/minecraft/util/Identifier;"),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void startAddSprite(ResourceManager resourceManager_1, CallbackInfo ci, int int_1, TextureStitcher textureStitcher_1, int int_2, int int_3, Iterator var6, Identifier identifier_1) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName(String.format("Adding sprite '%s'", identifier_1));
    }

    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/TextureStitcher;add(Lnet/minecraft/client/texture/Sprite;)V", ordinal = 0, shift = Shift.AFTER))
    private void endAddSprite(ResourceManager resourceManager_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.popTask();
    }*/

    @Inject(method = "loadSprite(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/client/texture/Sprite;)Z", at = @At("HEAD"))
    private void startLoadSprite(ResourceManager resourceManager_1, Sprite sprite_1, CallbackInfoReturnable<Boolean> cir) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName(String.format("Loading sprite '%s'", sprite_1.getId()));
    }

    @Inject(method = "loadSprite(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/client/texture/Sprite;)Z", at = @At("RETURN"))
    private void endLoadSprite(ResourceManager resourceManager_1, Sprite sprite_1, CallbackInfoReturnable<Boolean> cir) {
        LoadingProgressImpl.INSTANCE.popTask();
    }

}