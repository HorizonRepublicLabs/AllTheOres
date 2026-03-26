package net.allthemods.alltheores.events;


import com.mojang.blaze3d.systems.RenderSystem;
import net.allthemods.alltheores.content.blocks.sets.ATOSetHelper;
import net.allthemods.alltheores.infos.Reference;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import net.neoforged.neoforge.client.model.item.DynamicFluidContainerModel;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;


public class ClientEventHandler {

    public static void registerItemColors(RegisterClientExtensionsEvent event) {
       /* ATOSetHelper.applyToFluid(set -> event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull Identifier getStillTexture() {
                return Identifier.fromNamespaceAndPath(Reference.MOD_ID, "block/fluid/molten_metal");
            }

            @Override
            public @NotNull Identifier getFlowingTexture() {
                return Identifier.fromNamespaceAndPath(Reference.MOD_ID, "block/fluid/molten_metal_flow");
            }

            @Override
            public Identifier getOverlayTexture() {
                return Identifier.fromNamespaceAndPath(Reference.MOD_ID, "block/fluid/molten_metal");
            }

            @Override
            public int getTintColor() {
                return set.fluidColor;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(@NotNull Camera camera, float partialTick, @NotNull ClientLevel level, int renderDistance, float darkenWorldAmount, @NotNull Vector3f fluidFogColor) {
                return new Vector3f(set.fluidColor >> 16 & 255, set.fluidColor >> 8 & 255, set.fluidColor & 255).div(255.0F).mul(0.2f);
            }

            @Override
            public void modifyFogRender(@NotNull Camera camera, FogRenderer.@NotNull FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, @NotNull FogShape shape) {
                nearDistance = -8.0F;
                farDistance = 16.0F;

                Entity entity = camera.getEntity();

                if (camera.getEntity() instanceof LocalPlayer) {
                    LocalPlayer localplayer = (LocalPlayer) entity;
                    farDistance *= Math.max(0.25F, localplayer.getWaterVision());
                }

                if (farDistance > renderDistance) {
                    farDistance = renderDistance;
                    shape = FogShape.CYLINDER;
                }

                RenderSystem.setShaderFogStart(nearDistance);
                RenderSystem.setShaderFogEnd(farDistance);
                RenderSystem.setShaderFogShape(shape);
            }

        }, set.MOLTEN_TYPE.get()));
   */ }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent event) {
        //ATOSetHelper.applyToFluid(set -> event.register(new DynamicFluidContainerModel.Colors(), set.MOLTEN_BUCKET.get()));
    }
}
