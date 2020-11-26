package org.example;

import org.example.matrix.Matrix;
import org.example.matrix.MatrixUtil;
import org.example.network.NeuralNetwork;
import org.example.network.util.DataForNeuralNetwork;
import org.example.network.util.DataReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int inputNodes = 784;
        int hiddenNodes = 200;
        int outputNodes = 10;
        double learningRate = 0.1;

        int epochs = 5;

        // Создаем нейронную сеть
        NeuralNetwork n = new NeuralNetwork(inputNodes, hiddenNodes, outputNodes, learningRate);

        long startTime = System.currentTimeMillis();
        DataReader reader = new DataReader("src/main/resources/mnist_train.csv");
        List<DataForNeuralNetwork> inputData = reader.reader();

        int i = 0;
        while (i < epochs) {
            System.out.println("Поехали !");
            // Загружаем данные для обучения
            Matrix targetMatrix;
            // Начинаем обучение нейронки
            for (DataForNeuralNetwork data : inputData) {
                targetMatrix = new Matrix(new double[][]{{0.01}, {0.01}, {0.01}, {0.01}, {0.01}, {0.01}, {0.01}, {0.01}, {0.01}, {0.01}});
                Matrix inputMatrix = MatrixUtil.transpose(new Matrix(data.getLearningData()));
                targetMatrix.set(data.getNumber(), 0, 0.99);

                n.train(inputMatrix, targetMatrix);
            }
            System.out.println("Эпоха " + i++  + " Прошло времени: " + (System.currentTimeMillis() - startTime));
        }

        System.out.println("\n Тренировка завершина за: " + (System.currentTimeMillis() - startTime));


        // Загружаем тестовые данные
        DataReader reader2 = new DataReader("src/main/resources/mnist_test.csv");
        List<DataForNeuralNetwork> inputData2 = reader2.reader();

        List<Integer> result = new ArrayList<>();
        for (DataForNeuralNetwork data : inputData2) {
            Matrix inputMatrix = MatrixUtil.transpose(new Matrix(data.getLearningData()));

            if (MatrixUtil.maxValue(n.query(inputMatrix)) == data.getNumber()) {
                result.add(1);
            } else {
                result.add(0);
            }
        }

        double sum = 0;
        for (int item : result) {
            sum += item;
        }
        System.out.println("Эффективность: " + sum / result.size() * 100 + "%");
        // Проводим тестирование нейронки
    }
}
