package com.scripterblox.region_miner;

import com.scripterblox.region_miner.render_box.MainRenderer;
import com.scripterblox.region_miner.screens.RegionMinerMenuScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class RegionMinerClient implements ClientModInitializer {
    public static Vec3d perfectCorner1;
    public static Vec3d perfectCorner2;

    public static final Map<BlockPos, BlockState> originalBlockStates = new HashMap<>();

    private static KeyBinding menuKeyBinding;
    private static KeyBinding pos1KeyBinding;
    private static KeyBinding pos2KeyBinding;
    private static KeyBinding startMiningBinding;
    private static KeyBinding stopMiningBinding;
    private static KeyBinding clearBinding;

    @Override
    public void onInitializeClient() {
        menuKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.region_miner.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_INSERT,
                "category.region_miner.region_miner"
        ));

        pos1KeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.region_miner.set_pos_1",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_END,
                "category.region_miner.region_miner"
        ));

        pos2KeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.region_miner.set_pos_2",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_PAGE_DOWN,
                "category.region_miner.region_miner"
        ));

        startMiningBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.region_miner.start_mining",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_HOME,
                "category.region_miner.region_miner"
        ));

        stopMiningBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.region_miner.stop_mining",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_PAGE_UP,
                "category.region_miner.region_miner"
        ));

        clearBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.region_miner.clear_region",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DELETE,
                "category.region_miner.region_miner"
        ));


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKeyBinding.wasPressed()) {
                client.setScreen(new RegionMinerMenuScreen());
            }
            while (pos1KeyBinding.wasPressed()) {
                if (client.player != null) {
                    HitResult hit = client.crosshairTarget;

                    switch(hit.getType()) {
                        case HitResult.Type.MISS, HitResult.Type.ENTITY: break;
                        case HitResult.Type.BLOCK:
                            BlockHitResult blockHit = (BlockHitResult) hit;
                            BlockPos blockPos = blockHit.getBlockPos();

                            perfectCorner1 = new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                            break;
                    }
                }
            }
            while (pos2KeyBinding.wasPressed()) {
                if (client.player != null) {
                    HitResult hit = client.crosshairTarget;

                    switch(hit.getType()) {
                        case HitResult.Type.MISS, HitResult.Type.ENTITY: break;
                        case HitResult.Type.BLOCK:
                            BlockHitResult blockHit = (BlockHitResult) hit;
                            BlockPos blockPos = blockHit.getBlockPos();

                            perfectCorner2 = new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                            break;
                    }
                }
            }
            while (startMiningBinding.wasPressed()) {
                if (client.player != null) {
                    int xPos = (int) client.player.getPos().getX();
                    int yPos = (int) client.player.getPos().getY();
                    int zPos = (int) client.player.getPos().getZ();

                    perfectCorner2 = new Vec3d(xPos, yPos, zPos);


                }
            }
            while (stopMiningBinding.wasPressed()) {
                client.player.sendMessage(Text.literal("Set pos 2"), false);
            }
            while (clearBinding.wasPressed()) {
                perfectCorner1 = null;
                perfectCorner2 = null;
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            MainRenderer.tick();
        });
    }
}