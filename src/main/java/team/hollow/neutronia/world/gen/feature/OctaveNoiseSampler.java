package team.hollow.neutronia.world.gen.feature;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class OctaveNoiseSampler implements INoiseSampler {
    private final INoiseSampler[] layers;
    private final int octaveCount;

    private double amplitude = 1.0;
    private double frequency = 1.0;
    private double persistence = 0.51;
    private double lacunarity = 2.01;

    public OctaveNoiseSampler(INoiseSampler[] samplers) {
        this.layers = samplers;
        this.octaveCount = samplers.length;
    }

    public static OctaveNoiseSampler count(int count, Supplier<INoiseSampler> supplier) {
        INoiseSampler[] samplers = new INoiseSampler[count];
        for (int i = 0; i < count; i++) {
            samplers[i] = supplier.get();
        }
        return new OctaveNoiseSampler(samplers);
    }

    public static OctaveNoiseSampler perlin(Random random, int count) {
        return count(count, () -> new PerlinNoiseSampler(random));
    }

    public static OctaveNoiseSampler ridged(Random random, int count, double exponent) {
        return count(count, () -> new RidgeNoiseSampler(random, exponent));
    }

    @Override
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public void setPersistence(double persistence) {
        this.persistence = persistence;
    }

    public void setLacunarity(double lacunarity) {
        this.lacunarity = lacunarity;
    }

    @Override
    public void sample2D(double[] buffer, double originX, double originY, int sizeX, int sizeY) {
        Arrays.fill(buffer, 0.0);

        double currentAmplitude = this.amplitude;
        double currentFrequency = this.frequency;

        for (int octave = 0; octave < this.octaveCount; octave++) {
            INoiseSampler sampler = this.layers[octave];

            sampler.setAmplitude(currentAmplitude);
            sampler.setFrequency(currentFrequency);
            sampler.sample2D(buffer, originX, originY, sizeX, sizeY);

            currentAmplitude *= this.persistence;
            currentFrequency *= this.lacunarity;
        }
    }

    @Override
    public void sample3D(double[] buffer, double originX, double originY, double originZ, int sizeX, int sizeY, int sizeZ) {
        Arrays.fill(buffer, 0.0);

        double currentAmplitude = this.amplitude;
        double currentFrequency = this.frequency;

        for (int octave = 0; octave < this.octaveCount; octave++) {
            INoiseSampler sampler = this.layers[octave];

            sampler.setAmplitude(currentAmplitude);
            sampler.setFrequency(currentFrequency);
            sampler.sample3D(buffer, originX, originY, originZ, sizeX, sizeY, sizeZ);

            currentAmplitude *= this.persistence;
            currentFrequency *= this.lacunarity;
        }
    }
}