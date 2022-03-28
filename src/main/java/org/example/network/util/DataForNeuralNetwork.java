package org.example.network.util;

import lombok.Getter;

import java.util.Arrays;

/**
 * Класс для загрузки данных в нейронную сеть
 */
public class DataForNeuralNetwork {
    @Getter
    private final int number;
    @Getter
    private final double[] learningData;

    public DataForNeuralNetwork(int number, double[] learningData) {
        this.number = number;
        this.learningData = learningData;
    }

    @Override
    public String toString() {
        return "DataForNeuralNetwork{" +
                "number=" + number +
                ", learningData=" + Arrays.toString(learningData) +
                '}';
    }
}
