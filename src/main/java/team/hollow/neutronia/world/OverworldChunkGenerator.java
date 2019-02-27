package team.hollow.neutronia.world;

import net.minecraft.entity.EntityCategory;
import net.minecraft.sortme.SpawnHelper;
import net.minecraft.util.SystemUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.PhantomSpawner;
import net.minecraft.world.gen.PillagerSpawner;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.level.LevelGeneratorType;

import java.util.List;

public class OverworldChunkGenerator extends SurfaceChunkGenerator<OverworldChunkGeneratorConfig> {
   private static final float[] BIOME_WEIGHT_TABLE = SystemUtil.consume(new float[25], (floats_1) -> {
      for(int int_1 = -2; int_1 <= 2; ++int_1) {
         for(int int_2 = -2; int_2 <= 2; ++int_2) {
            float float_1 = 10.0F / MathHelper.sqrt((float)(int_1 * int_1 + int_2 * int_2) + 0.2F);
            floats_1[int_1 + 2 + (int_2 + 2) * 5] = float_1;
         }
      }

   });
   private final OctavePerlinNoiseSampler noiseSampler;
   private final boolean amplified;
   private final PhantomSpawner phantomSpawner = new PhantomSpawner();
   private final PillagerSpawner pillagerSpawner = new PillagerSpawner();

   public OverworldChunkGenerator(IWorld iWorld_1, BiomeSource biomeSource_1, OverworldChunkGeneratorConfig overworldChunkGeneratorConfig_1) {
      super(iWorld_1, biomeSource_1, 16, 32, 256, overworldChunkGeneratorConfig_1, true);
      this.random.consume(2620);
      this.noiseSampler = new OctavePerlinNoiseSampler(this.random, 128);
      this.amplified = iWorld_1.getLevelProperties().getGeneratorType() == LevelGeneratorType.AMPLIFIED;
   }

   public void populateEntities(ChunkRegion chunkRegion_1) {
      int int_1 = chunkRegion_1.getCenterChunkX();
      int int_2 = chunkRegion_1.getCenterChunkZ();
      Biome biome_1 = chunkRegion_1.getChunk(int_1, int_2).getBiomeArray()[0];
      ChunkRandom chunkRandom_1 = new ChunkRandom();
      chunkRandom_1.setSeed(chunkRegion_1.getSeed(), int_1 << 4, int_2 << 4);
      SpawnHelper.populateEntities(chunkRegion_1, biome_1, int_1, int_2, chunkRandom_1);
   }

   protected void sampleNoiseColumn(double[] doubles_1, int int_1, int int_2) {
      double double_1 = 684.4119873046875D;
      double double_2 = 684.4119873046875D;
      double double_3 = 8.555149841308594D;
      double double_4 = 4.277574920654297D;
      this.sampleNoiseColumn(doubles_1, int_1, int_2, double_1, double_2, double_3, double_4, 3, -10);
   }

   protected double computeNoiseFalloff(double double_1, double double_2, int int_1) {
      double double_3 = 8.5D;
      double double_4 = ((double)int_1 - (8.5D + double_1 * double_3 / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / double_2;
      if (double_4 < 0.0D) {
         double_4 *= 4.0D;
      }

      return double_4;
   }

   protected double[] computeNoiseRange(int int_1, int int_2) {
      double[] doubles_1 = new double[2];
      float float_1 = 0.0F;
      float float_2 = 0.0F;
      float float_3 = 0.0F;
      float float_4 = this.biomeSource.getBiomeForNoiseGen(int_1, int_2).getDepth();

      for(int int_4 = -2; int_4 <= 2; ++int_4) {
         for(int int_5 = -2; int_5 <= 2; ++int_5) {
            Biome biome_1 = this.biomeSource.getBiomeForNoiseGen(int_1 + int_4, int_2 + int_5);
            float float_5 = biome_1.getDepth();
            float float_6 = biome_1.getScale();
            if (this.amplified && float_5 > 0.0F) {
               float_5 = 1.0F + float_5 * 2.0F;
               float_6 = 1.0F + float_6 * 4.0F;
            }

            float float_7 = BIOME_WEIGHT_TABLE[int_4 + 2 + (int_5 + 2) * 5] / (float_5 + 2.0F);
            if (biome_1.getDepth() > float_4) {
               float_7 /= 2.0F;
            }

            float_1 += float_6 * float_7;
            float_2 += float_5 * float_7;
            float_3 += float_7;
         }
      }

      float_1 /= float_3;
      float_2 /= float_3;
      float_1 = float_1 * 0.9F + 0.1F;
      float_2 = (float_2 * 4.0F - 1.0F) / 8.0F;
      doubles_1[0] = (double)float_2 + this.method_16414(int_1, int_2);
      doubles_1[1] = (double)float_1;
      return doubles_1;
   }

   private double method_16414(int int_1, int int_2) {
      double double_1 = this.noiseSampler.sample((double)(int_1 * 200), 10.0D, (double)(int_2 * 200), 1.0D, 0.0D, false) / 8000.0D;
      if (double_1 < 0.0D) {
         double_1 = -double_1 * 0.3D;
      }

      double_1 = double_1 * 3.0D - 2.0D;
      if (double_1 < 0.0D) {
         double_1 /= 28.0D;
      } else {
         if (double_1 > 1.0D) {
            double_1 = 1.0D;
         }

         double_1 /= 40.0D;
      }

      return double_1;
   }

   public List<Biome.SpawnEntry> getEntitySpawnList(EntityCategory entityCategory_1, BlockPos blockPos_1) {
      if (Feature.SWAMP_HUT.method_14029(this.world, blockPos_1)) {
         if (entityCategory_1 == EntityCategory.MONSTER) {
            return Feature.SWAMP_HUT.getMonsterSpawns();
         }

         if (entityCategory_1 == EntityCategory.CREATURE) {
            return Feature.SWAMP_HUT.getCreatureSpawns();
         }
      } else if (entityCategory_1 == EntityCategory.MONSTER) {
         if (Feature.PILLAGER_OUTPOST.isApproximatelyInsideStructure(this.world, blockPos_1)) {
            return Feature.PILLAGER_OUTPOST.getMonsterSpawns();
         }

         if (Feature.OCEAN_MONUMENT.isApproximatelyInsideStructure(this.world, blockPos_1)) {
            return Feature.OCEAN_MONUMENT.getMonsterSpawns();
         }
      }

      return super.getEntitySpawnList(entityCategory_1, blockPos_1);
   }

   public void spawnEntities(World world_1, boolean boolean_1, boolean boolean_2) {
      this.phantomSpawner.spawn(world_1, boolean_1, boolean_2);
      this.pillagerSpawner.spawn(world_1, boolean_1, boolean_2);
   }

   public int getSpawnHeight() {
      return this.world.getSeaLevel() + 1;
   }

   public int getSeaLevel() {
      return 63;
   }

}