package com.scripterblox.region_miner.render_box;

import com.scripterblox.region_miner.RegionMinerClient;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Map;

public class MainRenderer {
    public static void tick() {
        Vec3d corner1 = RegionMinerClient.perfectCorner1;
        Vec3d corner2 = RegionMinerClient.perfectCorner2;

        World world = MinecraftClient.getInstance().world;

        if (world != null) {
            // Restore original block states
            for (Map.Entry<BlockPos, BlockState> entry : RegionMinerClient.originalBlockStates.entrySet()) {
                world.setBlockState(entry.getKey(), entry.getValue());
            }

            // Clear the map to prepare for new region tracking
            RegionMinerClient.originalBlockStates.clear();

            if (corner1 != null && corner2 != null) {
                // Calculate region bounds
                int minX = (int) Math.min(corner1.x, corner2.x);
                int maxX = (int) Math.max(corner1.x, corner2.x);
                int minY = (int) Math.min(corner1.y, corner2.y);
                int maxY = (int) Math.max(corner1.y, corner2.y);
                int minZ = (int) Math.min(corner1.z, corner2.z);
                int maxZ = (int) Math.max(corner1.z, corner2.z);

                // Store original block states and replace them with visual blocks
                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        for (int z = minZ; z <= maxZ; z++) {
                            BlockPos pos = new BlockPos(x, y, z);
                            if (!RegionMinerClient.originalBlockStates.containsKey(pos)) {
                                RegionMinerClient.originalBlockStates.put(pos, world.getBlockState(pos));
                            }
                            world.setBlockState(pos, Blocks.GOLD_BLOCK.getDefaultState());
                        }
                    }
                }
            }

            // Place a corner block if `corner1` is set
            if (corner1 != null) {
                BlockPos pos1 = new BlockPos((int) corner1.x, (int) corner1.y, (int) corner1.z);
                if (!RegionMinerClient.originalBlockStates.containsKey(pos1)) {
                    RegionMinerClient.originalBlockStates.put(pos1, world.getBlockState(pos1));
                }
                world.setBlockState(pos1, Blocks.DIAMOND_BLOCK.getDefaultState());
            }

            // Place a corner block if `corner2` is set
            if (corner2 != null) {
                BlockPos pos2 = new BlockPos((int) corner2.x, (int) corner2.y, (int) corner2.z);
                if (!RegionMinerClient.originalBlockStates.containsKey(pos2)) {
                    RegionMinerClient.originalBlockStates.put(pos2, world.getBlockState(pos2));
                }
                world.setBlockState(pos2, Blocks.DIAMOND_BLOCK.getDefaultState());
            }
        }
    }
}